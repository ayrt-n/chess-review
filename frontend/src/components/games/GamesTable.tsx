import GamesTableRow from "./GamesTableRow";

interface GameSummary {
  id: number;
  white: string;
  black: string;
  whiteElo: number;
  blackElo: number;
  analysisStatus: 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'FAILED';
}

function GamesTable({ games }: { games: GameSummary[] }) {
  return (
    <div className="w-full">
      <div className="flex bg-zinc-900 rounded-t-lg px-6 py-4">
        <div className="flex-1 font-bold text-white">Players</div>
        <div className="flex-1 font-bold text-white text-right">Status</div>
      </div>

      {games.map(game => (<GamesTableRow game={game} />))}
    </div>
  );
}

export default GamesTable;