import type { MoveAnalysis } from "../../types/api";
import type { Side } from "../../types/chess";
import { getPieceSymbol, parseSanMove } from "../../util/san";
import BestMove from "../annotations/BestMove";
import BlunderMove from "../annotations/BlunderMove";
import BrilliantMove from "../annotations/BrilliantMove";
import GoodMove from "../annotations/GoodMove";
import GreatMove from "../annotations/GreatMove";
import InaccuracyMove from "../annotations/InaccuracyMove";
import MissMove from "../annotations/MissMove";
import MistakeMove from "../annotations/MistakeMove";

function Commentary({ move, side }: { move: MoveAnalysis | null, side: Side }) {
  const getClassificationComponent = () => {
    if (!move) return null;

    switch (move.classification) {
      case "BRILLIANT":
        return <BrilliantMove size="sm" />;
      case "GREAT":
        return <GreatMove size="sm" />;
      case "BEST":
        return <BestMove size="sm" />;
      case "GOOD":
        return <GoodMove size="sm" />;
      case "INACCURACY":
        return <InaccuracyMove size="sm" />;
      case "MISTAKE":
        return <MistakeMove size="sm" />;
      case "BLUNDER":
        return <BlunderMove size="sm" />;
      case "MISS":
        return <MissMove size="sm" />;
      default:
        return null;
    }
  };

  const getClassificationText = () => {
    if (!move) return "";

    switch (move.classification) {
      case "BRILLIANT":
        return "brilliant";
      case "GREAT":
        return "great";
      case "BEST":
        return "best";
      case "GOOD":
        return "good";
      case "INACCURACY":
        return "an inaccuracy";
      case "MISTAKE":
        return "a mistake";
      case "BLUNDER":
        return "a blunder";
      case "MISS":
        return "a miss";
      default:
        return "";
    }
  };

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
  const sanMove = <>
    <span className="mr-1 text-xl">
      {getPieceSymbol(moveData.piece, side)}
    </span>
    <span>
      {moveData.square}
    </span>
  </>

  return (
    <div className="bg-white text-zinc-800 p-4 rounded-lg flex items-center justify-between flex-wrap">
      <div className="flex flex-1 gap-1 items-center">
        {getClassificationComponent()}
        <div className="font-bold break-words">
          {sanMove}
          {` is ${getClassificationText()}`}
        </div>
      </div>
      <div className="bg-zinc-200 py-1 px-2 rounded-md text-sm font-bold">
        {getEval()}
      </div>
    </div>
  );
}

export default Commentary;
