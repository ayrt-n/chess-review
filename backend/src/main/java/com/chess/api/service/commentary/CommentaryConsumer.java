package com.chess.api.service.commentary;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.chess.api.dto.CommentaryMessage;

@Service
public class CommentaryConsumer {
  private final MoveCommentaryService moveCommentaryService;

  public CommentaryConsumer(MoveCommentaryService moveCommentaryService) {
    this.moveCommentaryService = moveCommentaryService;
  }

  @RabbitListener(queues = "${chess.rabbitmq.queue.commentary}")
  public void handleCommentaryRequest(CommentaryMessage message) {
    moveCommentaryService.generateCommentary(message.gameId());
  }
}

