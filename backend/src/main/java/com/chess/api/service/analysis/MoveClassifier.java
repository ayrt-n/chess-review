package com.chess.api.service.analysis;

import org.springframework.stereotype.Service;

import com.chess.api.model.MoveClassification;
import com.chess.api.model.analysis.StockfishEvaluation;

@Service
public class MoveClassifier {
  public MoveClassifier() {}

  public MoveClassification classify(StockfishEvaluation prevMove, StockfishEvaluation currMove) {
    if (prevMove.getMate() != null) {
      if (currMove.getMate() == null) return MoveClassification.MISS;
      if (currMove.getMate() == prevMove.getMate() - 1) return MoveClassification.BEST;
      return MoveClassification.GREAT;
    }

    if (currMove.getMate() != null) return MoveClassification.BLUNDER;

    int cpDelta = currMove.getCp() - prevMove.getCp();
    if (cpDelta <= 40) return MoveClassification.GREAT;
    if (cpDelta <= 100) return MoveClassification.GOOD;
    if (cpDelta <= 300) return MoveClassification.INACCURACY;
    return MoveClassification.BLUNDER;
  }
}
