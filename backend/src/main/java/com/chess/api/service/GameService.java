package com.chess.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chess.api.dto.GameRequest;
import com.chess.api.dto.Pgn;
import com.chess.api.model.Game;
import com.chess.api.respository.GameRepository;

@Service
public class GameService {
  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Transactional
  public Game create(GameRequest request) {
    Game game = convertPgnToGame(request.pgn());
    return gameRepository.save(game);
  }

  private Game convertPgnToGame(String rawPgn) {
    Pgn pgn = PgnParser.parse(rawPgn);
    String hashedPgn = PgnHasher.hash(pgn);

    Game game = new Game(
      rawPgn,
      hashedPgn,
      pgn.white(),
      pgn.whiteElo(),
      pgn.black(),
      pgn.blackElo()
    );

    return game;
  }
}