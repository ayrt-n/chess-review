import { useEffect, useState } from "react";
import GamesTable from "./GamesTable";
import type { GameSummary } from "../../types/api";

function Games() {
  const [games, setGames] = useState<GameSummary[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch(import.meta.env.VITE_API_URL + "/api/games")
      .then(res => {
        if (!res.ok) throw new Error("Failed to fetch games");
        return res.json();
      })
      .then(data => {
        setGames(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  const numGames = (games === null) ? 0 : games.length;

  return (
    <div className="p-7 w-full max-w-[900px]">
      <header className="mb-4">
        <h1 className="text-2xl font-bold">
          Games Reviewed <span className="text-zinc-500">({numGames})</span>
        </h1>
      </header>

      {!loading && !error && (
        <GamesTable games={games} />
      )}
    </div>
  );
}

export default Games;
