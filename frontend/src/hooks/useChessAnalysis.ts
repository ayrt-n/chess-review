import { Chess } from "chess.js";
import { useCallback, useEffect, useRef, useState } from "react";
import type { AnalysisStatus, GameResponse, MoveAnalysis } from "../types/api";

const POLL_INTERVAL_MS = 2000;
const POLLING_STATUSES: AnalysisStatus[] = ['PENDING', 'PROCESSING', 'REVIEWING'];

function isPollingStatus(status: AnalysisStatus): boolean {
  return POLLING_STATUSES.includes(status);
}

export function useChessAnalysis(gameId: string | undefined) {
  const [game, setGame] = useState<GameResponse | null>(null);
  const [currentMove, setCurrentMove] = useState(0);
  const [positions, setPositions] = useState<string[]>([""]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [analysisStatus, setAnalysisStatus] = useState<AnalysisStatus | null>(null);
  const pollTimeoutRef = useRef<ReturnType<typeof setTimeout> | null>(null);

  const processGameData = useCallback((data: GameResponse) => {
    setGame(data);
    setAnalysisStatus(data.analysisStatus);

    if (data.analysis && data.analysis.length > 0) {
      const chess = new Chess();
      const fens = [chess.fen()];
      data.analysis.forEach((move: MoveAnalysis) => {
        chess.move({ 
          from: move.uci.slice(0, 2),
          to: move.uci.slice(2, 4),
          promotion: move.uci[4]
        });
        fens.push(chess.fen());
      });
      setPositions(fens);
    }

    setLoading(false);
  }, []);

  const fetchGame = useCallback(async () => {
    if (!gameId) return null;

    const res = await fetch(`${import.meta.env.VITE_API_URL}/api/games/${gameId}`);
    if (!res.ok) throw new Error("Failed to fetch game");

    return res.json();
  }, [gameId]);

  const fetchStatus = useCallback(async () => {
    if (!gameId) return null;

    const res = await fetch(`${import.meta.env.VITE_API_URL}/api/games/${gameId}/status`);
    if (!res.ok) throw new Error("Failed to fetch status");
    
    return res.json()
  }, [gameId]);


  const stopPolling = useCallback(() => {
    if (pollTimeoutRef.current) {
      clearTimeout(pollTimeoutRef.current);
      pollTimeoutRef.current = null;
    }
  }, []);

  const poll = useCallback(async () => {
    async function executePoll() {
      try {
        const statusData = await fetchStatus();
        if (!statusData) return;
  
        setAnalysisStatus(statusData.analysisStatus);
  
        if (statusData.analysisStatus === 'COMPLETED') {
          const gameData = await fetchGame();
          if (gameData) processGameData(gameData);
          stopPolling();
        } else if (!isPollingStatus(statusData.analysisStatus)) {
          stopPolling();
        } else {
          pollTimeoutRef.current = setTimeout(executePoll, POLL_INTERVAL_MS);
        }
      } catch (err) {
        console.error("Polling failed:", err);
        stopPolling();
      }
    }
  
    await executePoll();
  }, [fetchStatus, fetchGame, processGameData, stopPolling]);

  useEffect(() => {
    if (!gameId) return;
    
    fetchGame()
      .then(data => {
        if (data) {
          processGameData(data);
          if (isPollingStatus(data.analysisStatus)) {
            poll();
          }
        }
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });

    return stopPolling;
  }, [gameId, fetchGame, processGameData, poll, stopPolling]);

  const goToMove = (index: number) => {
    const safeIndex = Math.max(0, Math.min(index, positions.length - 1));
    setCurrentMove(safeIndex);
  };

  const numberOfMoves = game?.analysis?.length || 0;
  const isPolling = analysisStatus ? isPollingStatus(analysisStatus) : false;

  return {
    game,
    currentMove,
    currentFen: positions[currentMove],
    currentAnalysis: game?.analysis?.[currentMove - 1] || null,
    loading,
    error,
    isPolling,
    analysisStatus,
    navigation: {
      next: () => goToMove(currentMove + 1),
      prev: () => goToMove(currentMove - 1),
      reset: () => goToMove(0),
      end: () => goToMove(numberOfMoves),
      goToMove
    }
  };
}