import type { Side } from "../../types/chess";

function SanMove({ san, player, onClick }: { san: string, player: Side, onClick: () => void }) {
  const getMoveData = (san: string) => {
    if (san.includes('O-O')) {
      return { piece: '', square: san };
    }

    const match = san.match(/([KQRBN]?)(?:.*)([a-h][1-8])/);
    if (!match) return { piece: '', square: san };

    const [, piece, square] = match;
    return { piece, square };
  };

  const getPieceSymbol = (piece: string, side: Side) => {
    if (!piece) return "";

    const symbols: Record<string, { white: string; black: string }> = {
      'K': { white: '♔', black: '♚' },
      'Q': { white: '♕', black: '♛' },
      'R': { white: '♖', black: '♜' },
      'B': { white: '♗', black: '♝' },
      'N': { white: '♘', black: '♞' },
    };

    const iconSet = symbols[piece];
    return side === 'WHITE' ? iconSet.white : iconSet.black;
  };

  const moveData = getMoveData(san)
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