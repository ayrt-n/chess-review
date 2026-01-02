import { useMemo } from "react";
import { Chessboard } from "react-chessboard";
import { useParams } from "react-router";
import EvalBar from "./EvalBar";
import MoveHistory from "./MoveHistory";
import { useChessAnalysis } from "../../hooks/useChessAnalysis";
import type { MoveClassification } from "../../types/api";
import PlaybackButtons from "./PlaybackButtons";

const ARROW_COLORS: Record<MoveClassification, string> = {
  'BRILLIANT': 'oklch(71.5% 0.143 215.221 / 30%)',
  'GREAT': 'oklch(76.8% 0.233 130.85 / 30%)',
  'BEST': 'oklch(76.8% 0.233 130.85 / 30%)',
  'GOOD': 'oklch(76.8% 0.233 130.85 / 30%)',
  'INACCURACY': 'oklch(63.7% 0.237 25.331 / 30%)',
  'MISTAKE': 'oklch(63.7% 0.237 25.331 / 30%)',
  'BLUNDER': 'oklch(63.7% 0.237 25.331 / 30%)',
  'MISS': 'oklch(63.7% 0.237 25.331 / 30%)',
};

function Game() {
  const { gameId } = useParams();
  const { game, currentFen, currentAnalysis, navigation, loading, error } = useChessAnalysis(gameId);

  const arrows = useMemo(() => {
    if (!currentAnalysis?.bestUci) return [];

    return [{
      startSquare: currentAnalysis.bestUci.slice(0, 2),
      endSquare: currentAnalysis.bestUci.slice(2, 4),
      color: 'oklch(76.8% 0.233 130.85)'
    }];
  }, [currentAnalysis]);

  const squareStyles = useMemo(() => {
    if (!currentAnalysis?.uci || !currentAnalysis?.classification) {
      return {};
    }

    const { uci, classification } = currentAnalysis;
    const startSquare = uci.slice(0, 2);
    const endSquare = uci.slice(2, 4);
    const highlightColor = ARROW_COLORS[classification];

    return {
      [startSquare]: { backgroundColor: highlightColor },
      [endSquare]: { backgroundColor: highlightColor },
    };
  }, [currentAnalysis]);

  if (loading) return <div className="p-7">Loading...</div>;
  if (error) return <div className="p-7 text-red-500">Error: {error}</div>;
  if (!game) return <div className="p-7">Game not found</div>;

  const chessboardOptions = {
    position: currentFen,
    arrows: arrows,
    squareStyles: squareStyles,
    allowDragging: false,
    animationDurationInMs: 150
  };

  return (
    <div className="p-7 md:gap-6   h-screen md:flex overflow-hidden flex">
      <div className="grid grid-cols-[30px_auto] gap-4 mb-8 max-h-[600px] w-full flex-1 max-w-[80vh]">
        <div className="col-start-2">
          <div className="flex items-center gap-2">
            <div className="bg-zinc-600 w-3 h-3 border border-zinc-500" />
            {game.black} <span className="text-zinc-500">({game.blackElo})</span>
          </div>
        </div>

        <EvalBar cp={currentAnalysis?.evalCp} mate={currentAnalysis?.evalMate} />

        <div className="bg-green-500 aspect-square max-h-[80vh]">
          <Chessboard options={chessboardOptions} />
        </div>

        <div className="col-start-2">
          <div className="flex items-center gap-2">
            <div className="bg-white w-3 h-3 border border-zinc-500" />
            {game.white} <span className="text-zinc-500">({game.whiteElo})</span>
          </div>
        </div>
      </div>

      <div className="bg-zinc-900 max-h-[90vh] max-w-[400px] min-w-[300px] w-auto rounded-md overflow-hidden flex flex-col">
        <h2 className="bg-zinc-950 w-full px-4 py-2 font-bold shrink-0">
          Game History
        </h2>
        <div className="flex-1 overflow-y-auto min-h-0">
          <MoveHistory moves={game.analysis} onMoveClick={navigation.goToMove} />
        </div>
        <div className="shrink-0">
          <PlaybackButtons
            reset={navigation.reset}
            prev={navigation.prev}
            next={navigation.next}
            end={navigation.end}
          />
        </div>
      </div>
    </div>
  );
}

export default Game;
