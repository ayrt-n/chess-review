import type { MoveAnalysis } from "../../types/api";
import SanMove from "./SanMove";

function MoveHistory({ moves, onMoveClick }: { moves: MoveAnalysis[], onMoveClick: (moveIndex: number) => void }) {
  if (!moves) return null;

  const moveList = [];
  for (let i = 0; i < moves.length; i += 2) {
    const rowNum = (i / 2) + 1;
    const isEvenRow = ((rowNum % 2) === 0);

    const sanWhite = moves[i]?.san ?? "";
    const sanBlack = moves[i + 1]?.san ?? "";

    moveList.push(
      <li key={i} className={`px-4 py-2 ${isEvenRow && "bg-zinc-850"}`}>
        <span className="text-zinc-400 mr-4">{rowNum}.</span>
        <SanMove onClick={() => onMoveClick(i + 1)} san={sanWhite} player="WHITE" />
        {sanBlack && <SanMove onClick={() => onMoveClick(i + 2)} san={sanBlack} player="BLACK" />}
      </li>
    );
  }

  return (
    <ul>
      {moveList}
    </ul>
  );
}

export default MoveHistory;