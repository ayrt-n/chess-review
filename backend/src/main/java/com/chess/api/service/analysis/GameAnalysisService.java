package com.chess.api.service.analysis;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import com.chess.api.model.AnalysisStatus;
import com.chess.api.model.Game;
import com.chess.api.model.MoveAnalysis;
import com.chess.api.model.analysis.StockfishEvaluation;
import com.chess.api.respository.GameRepository;

@Service
public class GameAnalysisService {
  private final GameRepository gameRepository;
  private final StockfishClientFactory stockfishClientFactory;
  private final MoveClassifier moveClassifier;

  public GameAnalysisService(GameRepository gameRepository, StockfishClientFactory stockfishClientFactory, MoveClassifier moveClassifier) {
    this.gameRepository = gameRepository;
    this.stockfishClientFactory = stockfishClientFactory;
    this.moveClassifier = moveClassifier;
  }

  public void analyzeGame(Long gameId) {
    Game game = gameRepository.findById(gameId)
      .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));

    gameRepository.updateStatus(gameId, AnalysisStatus.PROCESSING);

    List<MoveAnalysis> moves = game.getAnalysis();

    try (StockfishClient stockfishClient = stockfishClientFactory.createClient()) {
      List<String> movesPlayed = new ArrayList<>();

      StockfishEvaluation lastEval = stockfishClient.evaluate(movesPlayed);

      for (MoveAnalysis move : moves) {
        movesPlayed.add(move.getUci());
        StockfishEvaluation currEval = stockfishClient.evaluate(movesPlayed);

        move.setEvalCp(currEval.getCp());
        move.setEvalMate(currEval.getMate());
        move.setBestUci(lastEval.getBestUci());
        move.setPvUci(lastEval.getPvUci());
        move.setClassification(
          moveClassifier.classify(currEval, lastEval)
        );

        lastEval = currEval;
      }

      gameRepository.updateAnalysis(gameId, moves);
      gameRepository.updateStatus(gameId, AnalysisStatus.COMPLETED);
    } catch (Exception e) {
      gameRepository.updateStatus(gameId, AnalysisStatus.FAILED);
    }
  }
}
