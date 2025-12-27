package com.chess.api.service;

import org.springframework.stereotype.Service;

import com.chess.api.model.Game;
import com.chess.api.respository.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameAnalysisService {
  private final GameRepository gameRepository;

  public GameAnalysisService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public void analyzeGame(Long gameId) {
    Game game = gameRepository.findById(gameId)
      .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));    
  }
}
