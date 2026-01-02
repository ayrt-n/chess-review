import { Link } from "react-router";
import GreatMove from "../annotations/GreatMove";

function Navbar() {
  return (
    <header className="py-4 px-5 border-b border-zinc-700 flex justify-between">
      <div>
        <Link to="/" className="text-zinc-50 flex items-center gap-2">
          <GreatMove size="sm" />
          <div className="text-xl font-brand font-bold">ChessMate</div>
        </Link>
      </div>
      <div>
        <Link
          to="/games/new"
          aria-label="Import Game"
          className="h-8 w-8 sm:w-auto px-0 sm:px-3 flex items-center justify-center sm:justify-start gap-1.5 rounded-md border border-zinc-600 text-zinc-400 hover:border-zinc-400 hover:text-zinc-200 transition-colors text-sm"
        >
          <span className="hidden sm:inline">Import Game</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 16 16"
            fill="currentColor"
            className="w-4 h-4"
          >
            <path d="M8 2a.75.75 0 0 1 .75.75v4.5h4.5a.75.75 0 0 1 0 1.5h-4.5v4.5a.75.75 0 0 1-1.5 0v-4.5h-4.5a.75.75 0 0 1 0-1.5h4.5v-4.5A.75.75 0 0 1 8 2Z" />
          </svg>
        </Link>
      </div>
    </header>
  );
}

export default Navbar;