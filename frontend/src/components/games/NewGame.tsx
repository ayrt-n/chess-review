import { useState } from "react";
import { useNavigate } from "react-router";

function NewGame() {
  const [pgn, setPgn] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    setIsSubmitting(true);

    try {
      const response = await fetch(import.meta.env.VITE_API_URL + "/api/games", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ pgn }),
      });

      console.log(response);

      if (!response.ok) {
        throw new Error("Failed to import game");
      }

      const game = await response.json();
      navigate(`/games/${game.id}`);
    } catch (err) {
      setError(err instanceof Error ? err.message : "An error occurred");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="p-7 w-full max-w-[900px]">
      <h1 className="text-2xl font-bold mb-4">
        Import Game
      </h1>
      
      <form onSubmit={handleSubmit}>
        <div className="mb-2">
          <label htmlFor="pgn-input">
            Paste PGN text here
          </label>
        </div>
        <div className="mb-4">
          <textarea
            id="pgn-input"
            className="bg-zinc-850 rounded-md border-zinc-700 border-1 px-3 py-2 w-full focus:border-zinc-600 focus:ring-1 focus:ring-zinc-600 focus:outline-none"
            value={pgn}
            onChange={(e) => setPgn(e.target.value)}
            placeholder="[Event &quot;Casual Game&quot;]&#10;[White &quot;Player1&quot;]&#10;[Black &quot;Player2&quot;]&#10;&#10;1. e4 e5 2. Nf3 Nc6..."
            rows={16}
            required
          />
        </div>
        
        {error && <p>{error}</p>}

        <div>
            <button
              type="submit"
              className="bg-zinc-600 border-b-4 border-zinc-700 p-2 rounded-sm hover:bg-zinc-550 hover:border-zinc-650 active:bg-zinc-600 active:border-zinc-700 cursor-pointer disabled:opacity-50 disabled:cursor-not-allowed disabled:hover:brightness-100"
              disabled={isSubmitting || !pgn.trim()}
            >
              {isSubmitting ? "Importing..." : "Import Game"}
            </button>
        </div>
      </form>
    </div>
  );
}

export default NewGame;
