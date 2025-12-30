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
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;

@Service
public class GameAnalysisService {
  String ANALYSIS_VERSION = "0.1.0";

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
      Board board = new Board();

      StockfishEvaluation lastEval = stockfishClient.evaluate(movesPlayed);

      for (MoveAnalysis move : moves) {
        List<String> bestMovePlayed = new ArrayList<>(movesPlayed);
        bestMovePlayed.add(lastEval.getBestUci());
        StockfishEvaluation bestEval = stockfishClient.evaluate(bestMovePlayed);

        movesPlayed.add(move.getUci());
        StockfishEvaluation currEval = move.getUci().equals(lastEval.getBestUci()) ? bestEval : stockfishClient.evaluate(movesPlayed);

        board.doMove(move.getSan());
        boolean isWhitePov = board.getSideToMove() == Side.WHITE;

        int whiteMultiplier = isWhitePov ? 1 : -1;
        move.setEvalCp(currEval.getCp() == null ? null : currEval.getCp() * whiteMultiplier);
        move.setEvalMate(currEval.getMate() == null ? null : currEval.getMate() * whiteMultiplier);
        move.setBestUci(lastEval.getBestUci());
        move.setPvUci(lastEval.getPvUci());
        move.setClassification(moveClassifier.classify(currEval, bestEval, isWhitePov));

        lastEval = currEval;
      }

      gameRepository.updateAnalysis(gameId, moves, stockfishClient.getEngineVersion(), ANALYSIS_VERSION);
    } catch (Exception e) {
      gameRepository.updateStatus(gameId, AnalysisStatus.FAILED);
    }
  }
}
