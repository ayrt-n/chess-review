package com.chess.api.dto;

import com.chess.api.model.AnalysisStatus;
import com.chess.api.model.Game;

public record GameSummary(
  Long id,
  String white,
  String black,
  Integer whiteElo,
  Integer blackElo,
  AnalysisStatus analysisStatus
) {
  public GameSummary(Game game) {
    this(game.getId(), game.getWhite(), game.getBlack(), game.getWhiteElo(), game.getBlackElo(), game.getAnalysisStatus());
  }
}
