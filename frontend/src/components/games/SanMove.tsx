import type { Side } from "../../types/chess";
import { parseSanMove, getPieceSymbol } from "../../util/san";

function SanMove({ san, player, onClick }: { san: string, player: Side, onClick: () => void }) {
  const moveData = parseSanMove(san)
  if (!moveData) return null;

  const { piece, square } = moveData

  return (
    <span className="cursor-pointer hover:text-lime-500 active:text-lime-600 p-2" onClick={onClick}>
      <span className="mr-1 text-xl">
        {getPieceSymbol(piece, player)}
      </span>
      <span>
        {square}
      </span>
    </span>
  );
}

export default SanMove;