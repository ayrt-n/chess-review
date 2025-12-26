package com.chess.api.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chess.api.dto.GameAnalysisMessage;

@Service
public class GameAnalysisPublisher {
  private final RabbitTemplate rabbitTemplate;

  @Value("${chess.rabbitmq.exchange.analysis}")
  private String exchangeName;

  @Value("${chess.rabbitmq.routing-key.analysis}")
  private String routingKey;
  
  public GameAnalysisPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publishAnalysisRequest(Long gameId) {
    GameAnalysisMessage message = new GameAnalysisMessage(gameId);
    rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
  }
}
