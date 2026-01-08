package com.chess.api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  @Value("${chess.rabbitmq.queue.analysis}")
  private String analysisQueueName;

  @Value("${chess.rabbitmq.exchange.analysis}")
  private String analysisExchangeName;

  @Value("${chess.rabbitmq.routing-key.analysis}")
  private String analysisRoutingKey;

  @Value("${chess.rabbitmq.queue.commentary}")
  private String commentaryQueueName;

  @Value("${chess.rabbitmq.exchange.commentary}")
  private String commentaryExchangeName;

  @Value("${chess.rabbitmq.routing-key.commentary}")
  private String commentaryRoutingKey;

  @Bean
  public Queue analysisQueue() {
    return QueueBuilder.durable(analysisQueueName).quorum().build();
  }

  @Bean
  public DirectExchange analysisExchange() {
    return new DirectExchange(analysisExchangeName);
  }

  @Bean
  public Binding analysisBinding(Queue analysisQueue, DirectExchange analysisExchange) {
    return BindingBuilder
      .bind(analysisQueue)
      .to(analysisExchange)
      .with(analysisRoutingKey);
  }

  @Bean
  public Queue commentaryQueue() {
    return QueueBuilder.durable(commentaryQueueName).quorum().build();
  }

  @Bean
  public DirectExchange commentaryExchange() {
    return new DirectExchange(commentaryExchangeName);
  }

  @Bean
  public Binding commentaryBinding(Queue commentaryQueue, DirectExchange commentaryExchange) {
    return BindingBuilder
      .bind(commentaryQueue)
      .to(commentaryExchange)
      .with(commentaryRoutingKey);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new JacksonJsonMessageConverter();
  }
}
