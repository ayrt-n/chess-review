package com.chess.api.service.analysis;

import org.springframework.stereotype.Service;

import com.chess.api.model.MoveClassification;
import com.chess.api.model.analysis.StockfishEvaluation;

@Service
public class MoveClassifier {
  public MoveClassifier() {}

  public MoveClassification classify(StockfishEvaluation actual, StockfishEvaluation best, boolean isWhitePov) {
    if (best.getMate() != null) {
      if (actual.getMate() == null) return MoveClassification.MISS;
      if (actual.getMate() == best.getMate()) return MoveClassification.BEST;
      return MoveClassification.GREAT;
    }

    Double bestWin = winProbability(best.getCp(), isWhitePov);
    Double actualWin = winProbability(actual.getCp(), isWhitePov);

    Double winDiff = bestWin - actualWin;
    if (winDiff < 0.01) return MoveClassification.BEST;
    if (winDiff < 0.02) return MoveClassification.GREAT;
    if (winDiff < 0.05) return MoveClassification.GOOD;
    if (winDiff < 0.12) return MoveClassification.INACCURACY;
    if (winDiff < 0.2) return MoveClassification.MISTAKE;
    return MoveClassification.BLUNDER;
  }

  private Double winProbability(int cp, boolean isWhitePov) {
    int whiteMultiplier = isWhitePov ? 1 : -1;
    double k = -0.00368208;
    return 1.0 / (1.0 + Math.exp(k * cp * whiteMultiplier));
  }
}
