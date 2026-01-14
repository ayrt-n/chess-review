import type { AnnotationSize } from "../../types/annotations";
import type { MoveClassification } from "../../types/api";
import BestMove from "../annotations/BestMove";
import BlunderMove from "../annotations/BlunderMove";
import BrilliantMove from "../annotations/BrilliantMove";
import GoodMove from "../annotations/GoodMove";
import GreatMove from "../annotations/GreatMove";
import InaccuracyMove from "../annotations/InaccuracyMove";
import MissMove from "../annotations/MissMove";
import MistakeMove from "../annotations/MistakeMove";

interface ClassificationIconProps {
  classification: MoveClassification | null;
  size?: AnnotationSize;
}

function ClassificationIcon({ classification, size = "sm" }: ClassificationIconProps) {
  switch (classification) {
    case "BRILLIANT":
      return <BrilliantMove size={size} />;
    case "GREAT":
      return <GreatMove size={size} />;
    case "BEST":
      return <BestMove size={size} />;
    case "GOOD":
      return <GoodMove size={size} />;
    case "INACCURACY":
      return <InaccuracyMove size={size} />;
    case "MISTAKE":
      return <MistakeMove size={size} />;
    case "BLUNDER":
      return <BlunderMove size={size} />;
    case "MISS":
      return <MissMove size={size} />;
    default:
      return null;
  }
}

export default ClassificationIcon;
