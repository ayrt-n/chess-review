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
import com.chess.api.service.commentary.CommentaryPublisher;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;

@Service
public class GameAnalysisService {
  String ANALYSIS_VERSION = "0.1.0";

  private final GameRepository gameRepository;
  private final StockfishClientFactory stockfishClientFactory;
  private final MoveClassifier moveClassifier;
  private final CommentaryPublisher commentaryPublisher;

  public GameAnalysisService(GameRepository gameRepository, StockfishClientFactory stockfishClientFactory, MoveClassifier moveClassifier, CommentaryPublisher commentaryPublisher) {
    this.gameRepository = gameRepository;
    this.stockfishClientFactory = stockfishClientFactory;
    this.moveClassifier = moveClassifier;
    this.commentaryPublisher = commentaryPublisher;
  }

  public void analyzeGame(Long gameId) {
    Game game = gameRepository.findById(gameId)
      .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));

    gameRepository.updateStatus(gameId, AnalysisStatus.PROCESSING);

    List<MoveAnalysis> moves = game.getAnalysis();

    try (StockfishClient stockfishClient = stockfishClientFactory.createClient()) {
      List<String> movesPlayed = new ArrayList<>();
      Board board = new Board();

      StockfishEvaluation lastEval = stockfishClient.evaluate(movesPlayed, board.getSideToMove());

      for (MoveAnalysis move : moves) {
        move.setSide(board.getSideToMove());
        move.setFen(board.getFen());

        board.doMove(move.getSan());
        Side sideToMove = board.getSideToMove();

        List<String> bestMovePlayed = new ArrayList<>(movesPlayed);
        bestMovePlayed.add(lastEval.getBestUci());
        StockfishEvaluation bestEval = stockfishClient.evaluate(bestMovePlayed, sideToMove);

        movesPlayed.add(move.getUci());
        StockfishEvaluation currEval = move.getUci().equals(lastEval.getBestUci()) ? bestEval : stockfishClient.evaluate(movesPlayed, sideToMove);

        move.setEvalCp(currEval.getCp());
        move.setEvalMate(currEval.getMate());
        move.setBestUci(lastEval.getBestUci());
        move.setPvUci(lastEval.getPvUci());
        move.setClassification(moveClassifier.classify(currEval, bestEval));

        lastEval = currEval;
      }

      gameRepository.updateAnalysis(gameId, moves, stockfishClient.getEngineVersion(), ANALYSIS_VERSION);
      
      commentaryPublisher.publishCommentaryRequest(gameId);
    } catch (Exception e) {
      e.printStackTrace();
      gameRepository.updateStatus(gameId, AnalysisStatus.FAILED);
    }
  }
}
