package com.chess.api.service.commentary;

import org.springframework.stereotype.Service;

import com.chess.api.model.AnalysisStatus;
import com.chess.api.model.Game;
import com.chess.api.respository.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MoveCommentaryService {
  private final GameRepository gameRepository;

  public MoveCommentaryService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public void generateCommentary(Long gameId) {
    Game game = gameRepository.findById(gameId)
      .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));

    gameRepository.updateStatus(gameId, AnalysisStatus.REVIEWING);
  }
}

