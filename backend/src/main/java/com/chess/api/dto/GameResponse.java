package com.chess.api.dto;

import java.util.List;

import com.chess.api.model.AnalysisStatus;
import com.chess.api.model.Game;
import com.chess.api.model.MoveAnalysis;

public record GameResponse(
  Long id,
  String white,
  String black,
  int whiteElo,
  int blackElo,
  List<MoveAnalysis> analysis,
  AnalysisStatus analysisStatus,
  String analysisVersion,
  String engineVersion
) {
  public GameResponse(Game game) {
    this(
      game.getId(),
      game.getWhite(),
      game.getBlack(),
      game.getWhiteElo(),
      game.getBlackElo(),
      game.getAnalysis(),
      game.getAnalysisStatus(),
      game.getAnalysisVersion(),
      game.getEngineVersion()
    );
  }
}
