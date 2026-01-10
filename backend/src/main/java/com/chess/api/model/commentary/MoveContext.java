package com.chess.api.model.commentary;

import java.util.List;

import com.chess.api.model.MoveAnalysis;
import com.chess.api.model.MoveClassification;
import com.github.bhlangonijr.chesslib.Side;

public record MoveContext(
  int moveNumber,
  String uci,
  Side side,
  String fenBefore,
  MoveClassification classification,
  Evaluation evalBefore,
  Evaluation evalAfter,
  List<String> pvBefore,
  List<String> pvAfter
) {
  public MoveContext(int moveNumber, MoveAnalysis previousMove, MoveAnalysis currentMove, MoveAnalysis nextMove) {
    this(
      moveNumber,
      currentMove.getUci(),
      currentMove.getSide(),
      currentMove.getFen(),
      currentMove.getClassification(),
      (previousMove == null) ? null : new Evaluation(previousMove.getEvalCp(), previousMove.getEvalMate()),
      new Evaluation(currentMove.getEvalCp(), currentMove.getEvalMate()),
      currentMove.getPvUci(),
      (nextMove == null) ? null : nextMove.getPvUci()
    );
  }
}
