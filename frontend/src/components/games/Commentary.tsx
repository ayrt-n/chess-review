import type { MoveAnalysis, MoveClassification } from "../../types/api";
import { getPieceSymbol, parseSanMove } from "../../util/san";
import ClassificationIcon from "./ClassificationIcon";

const CLASSIFICATION_TEXT: Record<MoveClassification, string> = {
  BRILLIANT: "brilliant",
  GREAT: "great",
  BEST: "best",
  GOOD: "good",
  INACCURACY: "an inaccuracy",
  MISTAKE: "a mistake",
  BLUNDER: "a blunder",
  MISS: "a miss",
};

function Commentary({ move }: { move: MoveAnalysis | null }) {
  const getEval = () => {
    if (!move) return null;
    if (move.evalMate !== null) return `M${Math.abs(move.evalMate)}`;
    if (move.evalCp === null || move.evalCp === undefined) return "0.0";

    const score = move.evalCp / 100;
    return score > 0 ? `+${score.toFixed(1)}` : score.toFixed(1);
  };

  if (!move) return (
    <div className="bg-white text-zinc-800 p-4 rounded-lg min-h-[60px] flex items-center">
      <span className="font-bold">Let's start reviewing!</span>
    </div>
  );

  const moveData = parseSanMove(move.san) ?? {};
  const moveSummary = <>
    <span className="mr-1 text-xl">
      {getPieceSymbol(moveData.piece, move.side)}
    </span>
    <span>
      {moveData.square}
    </span>
    <span>
      {` is ${move.classification ? CLASSIFICATION_TEXT[move.classification] : ""}`}
    </span>
  </>

  const hasCommentary = () => { return !!move?.commentary };

  return (
    <div className="bg-white text-zinc-800 p-4 rounded-lg">   
      <div className="flex items-center justify-between flex-wrap">
        <div className="flex flex-1 gap-1 items-center">
          <ClassificationIcon classification={move.classification} />
          <div className="font-bold break-words">
            {moveSummary}
          </div>
        </div>
        <div className="bg-zinc-200 py-1 px-2 rounded-md text-sm font-bold">
          {getEval()}
        </div>
      </div>
      { hasCommentary() &&
        <div className="mt-2">
          {move?.commentary}
        </div>
      }
    </div>
  );
}

export default Commentary;
