package com.chess.api.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.chess.api.dto.GameAnalysisMessage;

@Service
public class GameAnalysisConsumer {
  @RabbitListener(queues = "${chess.rabbitmq.queue.analysis}")
  
  public void handleAnalysisRequest(GameAnalysisMessage message) {
    System.out.println("Received analysis request for game: " + message.gameId());
  }
}

