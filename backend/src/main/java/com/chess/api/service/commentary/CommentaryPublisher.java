package com.chess.api.service.commentary;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chess.api.dto.CommentaryMessage;

@Service
public class CommentaryPublisher {
  private final RabbitTemplate rabbitTemplate;

  @Value("${chess.rabbitmq.exchange.commentary}")
  private String exchangeName;

  @Value("${chess.rabbitmq.routing-key.commentary}")
  private String routingKey;

  public CommentaryPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publishCommentaryRequest(Long gameId) {
    CommentaryMessage message = new CommentaryMessage(gameId);
    rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
  }
}

