import { Link } from "react-router";

interface GameSummary {
  id: number;
  white: string;
  black: string;
  whiteElo: number;
  blackElo: number;
  analysisStatus: 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'FAILED';
}

function GamesTableRow({ game }: { game: GameSummary }) {
  const reviewStatus = (() => {
    switch (game.analysisStatus) {
      case 'COMPLETED':
        return (<span className="text-green-400 bg-green-400/10 px-2 py-1 rounded text-sm">Ready</span>);
      case 'PROCESSING':
        return (<span className="text-yellow-400 bg-yellow-400/10 px-2 py-1 rounded text-sm">Processing</span>);
      case 'FAILED':
        return (<span className="text-red-400 bg-red-400/10 px-2 py-1 rounded text-sm">Failed</span>);
      default:
        return (<span className="text-zinc-400 bg-zinc-400/10 px-2 py-1 rounded text-sm">Unknown</span>);
    }
  })();

  return (
    <Link to={`games/${game.id}`} className="flex items-center bg-zinc-850 border-t border-zinc-700 px-6 py-4 hover:bg-white/5 cursor-pointer">
      <div className="flex-1">
        <div className="flex items-center gap-2">
          <div className="bg-white w-3 h-3" />
          <span>{game.white} ({game.whiteElo})</span>
        </div>
        <div className="flex items-center gap-2">
          <div className="bg-zinc-600 w-3 h-3" />
          <span>{game.black} ({game.blackElo})</span>
        </div>
      </div>
      <div className="text-right flex-1">
        {reviewStatus}
      </div>
    </Link>
  );
}

export default GamesTableRow;
