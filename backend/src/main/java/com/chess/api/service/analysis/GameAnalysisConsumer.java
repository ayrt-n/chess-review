package com.chess.api.service.analysis;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.chess.api.dto.GameAnalysisMessage;

@Service
public class GameAnalysisConsumer {
  private final GameAnalysisService gameAnalysisService;

  public GameAnalysisConsumer(GameAnalysisService gameAnalysisService) {
    this.gameAnalysisService = gameAnalysisService;
  }

  @RabbitListener(queues = "${chess.rabbitmq.queue.analysis}")
  public void handleAnalysisRequest(GameAnalysisMessage message) {
    gameAnalysisService.analyzeGame(message.gameId());
  }
}
