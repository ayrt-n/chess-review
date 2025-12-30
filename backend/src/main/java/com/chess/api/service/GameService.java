package com.chess.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chess.api.dto.GameRequest;
import com.chess.api.dto.Pgn;
import com.chess.api.model.Game;
import com.chess.api.model.MoveAnalysis;
import com.chess.api.respository.GameRepository;
import com.chess.api.service.analysis.GameAnalysisPublisher;
import com.github.bhlangonijr.chesslib.move.MoveList;

@Service
public class GameService {
  private final GameRepository gameRepository;
  private final GameAnalysisPublisher gameAnalysisPublisher;

  public GameService(GameRepository gameRepository, GameAnalysisPublisher gameAnalysisPublisher) {
    this.gameRepository = gameRepository;
    this.gameAnalysisPublisher = gameAnalysisPublisher;
  }

  public List<Game> findAll() {
    return gameRepository.findAll();
  }

  public Optional<Game> findById(Long id) {
    return gameRepository.findById(id);
  }

  @Transactional
  public Game create(GameRequest request) {
    Game game = convertPgnToGame(request.pgn());
    Game savedGame = gameRepository.save(game);

    gameAnalysisPublisher.publishAnalysisRequest(savedGame.getId());

    return savedGame;
  }

  private Game convertPgnToGame(String rawPgn) {
    Pgn pgn = PgnParser.parse(rawPgn);
    String hashedPgn = PgnHasher.hash(pgn);

    String moveString = MoveTextParser.parse(pgn.moveText());
    MoveList moveList = new MoveList();
    moveList.loadFromSan(moveString);

    List<MoveAnalysis> moveAnalysis = moveList.stream()
                                              .map(move -> new MoveAnalysis(move.getSan(), move.toString()))
                                              .collect(Collectors.toList());

    Game game = new Game(
      rawPgn,
      hashedPgn,
      pgn.white(),
      pgn.whiteElo(),
      pgn.black(),
      pgn.blackElo(),
      moveAnalysis
    );

    return game;
  }
}