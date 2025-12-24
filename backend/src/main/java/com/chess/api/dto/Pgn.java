package com.chess.api.dto;

public record Pgn(
    String white,
    String black,
    int whiteElo,
    int blackElo,
    String moveText
) {
  public String toString() {
    return "White: " + white + " (" + whiteElo + "), Black: " + black +  " (" + whiteElo + ")" + "Moves: " + moveText;
  }
}
