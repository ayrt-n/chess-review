package com.chess.api.service.commentary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.chess.api.model.AnalysisStatus;
import com.chess.api.model.Game;
import com.chess.api.model.MoveAnalysis;
import com.chess.api.model.MoveClassification;
import com.chess.api.model.commentary.MoveContext;
import com.chess.api.respository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MoveCommentaryService {
  private static final Set<MoveClassification> COMMENTABLE_CLASSIFICATIONS = Set.of(
      MoveClassification.INACCURACY,
      MoveClassification.MISTAKE,
      MoveClassification.BLUNDER,
      MoveClassification.MISS
  );

  private final GameRepository gameRepository;
  private final ChatClient.Builder chatClientBuilder;
  private final ObjectMapper objectMapper;

  @Value("classpath:prompts/system-move-commentary.st")
  private Resource systemPrompt;

  @Value("classpath:prompts/user-batch-move-commentary.st")
  private Resource userPrompt;

  public MoveCommentaryService(GameRepository gameRepository, ChatClient.Builder chatClientBuilder, ObjectMapper objectMapper) {
    this.gameRepository = gameRepository;
    this.chatClientBuilder = chatClientBuilder;
    this.objectMapper = objectMapper;
  }

  public void generateCommentary(Long gameId) {
    Game game = gameRepository.findById(gameId)
      .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));

    gameRepository.updateStatus(gameId, AnalysisStatus.REVIEWING);

    try {
      List<MoveAnalysis> moves = game.getAnalysis();
      ChatClient chatClient = chatClientBuilder.defaultSystem(systemPrompt).build();

      List<MoveContext> movesToReview = new ArrayList<>();
      for (int i = 0; i < moves.size(); i++) {
        MoveAnalysis prevMove = (i == 0) ? null : moves.get(i - 1);
        MoveAnalysis currMove = moves.get(i);
        MoveAnalysis nextMove = (i == moves.size() - 1) ? null : moves.get(i + 1);

        if (shouldGenerateCommentary(currMove)) {
          MoveContext moveContext = new MoveContext(i + 1, prevMove, currMove, nextMove);
          movesToReview.add(moveContext);
        }
      }

      String movePayload = objectMapper.writeValueAsString(movesToReview);

      List<MoveCommentary> comments = chatClient.prompt()
        .user(u -> u.text(userPrompt).param("moveContexts", movePayload))
        .call()
        .entity(new ParameterizedTypeReference<List<MoveCommentary>>() {});

      for (MoveCommentary comment : comments) {
        MoveAnalysis move = moves.get(comment.moveNumber - 1);
        move.setCommentary(comment.commentary());
      }

      gameRepository.updateAnalysis(gameId, moves);
      gameRepository.updateStatus(gameId, AnalysisStatus.COMPLETED);
    } catch (Exception e) {
      e.printStackTrace();
      gameRepository.updateStatus(gameId, AnalysisStatus.FAILED);
    }
  }

  private boolean shouldGenerateCommentary(MoveAnalysis move) {
    return move.getClassification() != null && COMMENTABLE_CLASSIFICATIONS.contains(move.getClassification());
  }

  private record MoveCommentary(int moveNumber, String commentary) {}
}
