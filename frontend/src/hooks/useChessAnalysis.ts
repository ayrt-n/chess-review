import { Chess } from "chess.js";
import { useEffect, useState } from "react";
import type { GameResponse, MoveAnalysis } from "../types/api";

export function useChessAnalysis(gameId: string | undefined) {
  const [game, setGame] = useState<GameResponse | null>(null);
  const [currentMove, setCurrentMove] = useState(0);
  const [positions, setPositions] = useState<string[]>([""]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!gameId) return;
    
    fetch(`${import.meta.env.VITE_API_URL}/api/games/${gameId}`)
      .then(res => {
        if (!res.ok) throw new Error("Failed to fetch game");
        return res.json();
      })
      .then(data => {
        setGame(data);

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
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, [gameId]);

  const goToMove = (index: number) => {
    const safeIndex = Math.max(0, Math.min(index, positions.length - 1));
    setCurrentMove(safeIndex);
  };

  const numberOfMoves = game?.analysis?.length || 0;

  return {
    game,
    currentMove,
    currentFen: positions[currentMove],
    currentAnalysis: game?.analysis[currentMove - 1] || null,
    loading,
    error,
    navigation: {
      next: () => goToMove(currentMove + 1),
      prev: () => goToMove(currentMove - 1),
      reset: () => goToMove(0),
      end: () => goToMove(numberOfMoves),
      goToMove
    }
  };
}