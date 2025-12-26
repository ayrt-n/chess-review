package com.chess.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import com.chess.api.dto.GameRequest;
import com.chess.api.dto.GameResponse;
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

  @GetMapping
  public List<GameSummary> index() {
    List<Game> games = gameService.findAll();
    return games.stream().map(game -> new GameSummary(game)).toList();
  }

  @GetMapping("/{id}")
  public GameResponse show(@PathVariable Long id) {
    Game game = gameService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    return new GameResponse(game);
  }

  @PostMapping
  public GameSummary create(@Valid @RequestBody GameRequest request) {
    Game game = gameService.create(request);
    return new GameSummary(game);
  }
}
