// Enums

export type AnalysisStatus = 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'FAILED';

export type MoveClassification =
  | 'BRILLIANT'
  | 'GREAT'
  | 'BEST'
  | 'GOOD'
  | 'INACCURACY'
  | 'MISTAKE'
  | 'BLUNDER'
  | 'MISS';

export type Side = 'WHITE' | 'BLACK';

// Models

export interface MoveAnalysis {
  san: string;
  uci: string;
  side: Side | null;
  bestUci: string | null;
  pvUci: string[] | null;
  evalCp: number | null;
  evalMate: number | null;
  commentary: string | null;
  classification: MoveClassification | null;
}

export interface StockfishEvaluation {
  depth: number;
  cp: number | null;
  mate: number | null;
  bestUci: string | null;
  pvUci: string[] | null;
}

// DTOs

export interface GameRequest {
  pgn: string;
}

export interface GameResponse {
  id: number;
  white: string;
  black: string;
  whiteElo: number;
  blackElo: number;
  analysis: MoveAnalysis[];
  analysisStatus: AnalysisStatus;
  analysisVersion: string | null;
  engineVersion: string | null;
  result: string | null;
  timeControl: string | null;
  gameDate: string | null;
}

export interface GameSummary {
  id: number;
  white: string | null;
  black: string | null;
  whiteElo: number | null;
  blackElo: number | null;
  analysisStatus: AnalysisStatus;
}

export interface Pgn {
  white: string;
  black: string;
  whiteElo: number;
  blackElo: number;
  moveText: string;
  timeControl: string;
  result: string;
  gameDate: string;
}

export interface GameAnalysisMessage {
  gameId: number;
}
