import type { Side } from "../types/api";

export interface MoveData {
  piece: string;
  square: string;
}

export function parseSanMove(san: string): MoveData {
  if (san.includes('O-O')) {
    return { piece: '', square: san };
  }

  const match = san.match(/([KQRBN]?)(?:.*)([a-h][1-8])/);
  if (!match) return { piece: '', square: san };

  const [, piece, square] = match;
  return { piece, square };
}

export function getPieceSymbol(piece: string, side: Side | null): string {
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
}

