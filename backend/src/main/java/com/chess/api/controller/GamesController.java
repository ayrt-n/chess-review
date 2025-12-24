package com.chess.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.chess.api.dto.GameRequest;
import com.chess.api.dto.GameSummary;
import com.chess.api.model.Game;
import com.chess.api.service.GameService;

@RestController
@RequestMapping("/api/games")
public class GamesController {
  private final GameService gameService;

  public GamesController(GameService gameService) {
    this.gameService = gameService;
  }

  @PostMapping
  public GameSummary create(@Valid @RequestBody GameRequest request) {
    Game game = gameService.create(request);
    return new GameSummary(game);
  }
}
