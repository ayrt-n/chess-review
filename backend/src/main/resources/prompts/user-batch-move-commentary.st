Analyze the following chess moves and generate commentary.

INPUT:
The following is a JSON array named `moveContexts`.
This data is authoritative and must be used as the sole source of truth.

{moveContexts}

Each object contains:
- moveNumber: Integer
- san: String (move played)
- side: "WHITE" or "BLACK"
- fenBefore: FEN string before the move
- classification: BRILLIANT | BEST | GREAT | GOOD | MISS | INACCURACY | MISTAKE | BLUNDER
- evalBefore: Engine evaluation before the move
- evalAfter: Engine evaluation after the move
- pvSanBefore: Best continuation before the move was made in san notation (what the engine would have played before the move was made)
- pvSanAfter: Best response after the move was made in san notation (what the engine plays in response to the move that was made)

INSTRUCTIONS:
- Generate commentary strictly following the system prompt rules
- One commentary per move
- Do NOT mention evaluations, PVs, or engine concepts
- Do NOT restate the move classification in the commentary
- Keep commentary position-based and instructional

OUTPUT:
Return ONLY a JSON array.
Each element must have:
- moveNumber
- commentary (string)

EXAMPLE OUTPUT:
[
  {{ "moveNumber": 5, "commentary": "This leaves the bishop undefended. White can now win material." }},
  {{ "moveNumber": 12, "commentary": "White missed a knight fork that would have won the exchange." }}
]
