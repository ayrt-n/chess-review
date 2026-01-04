package com.chess.api.dto;

import java.time.OffsetDateTime;

public record Pgn(
    String white,
    String black,
    int whiteElo,
    int blackElo,
    String moveText,
    String timeControl,
    String result,
    OffsetDateTime gameDate
) {
  public String toString() {
    return "White: " + white + " (" + whiteElo + "), Black: " + black +  " (" + whiteElo + ")" + "Moves: " + moveText;
  }
}
