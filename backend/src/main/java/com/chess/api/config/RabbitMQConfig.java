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
  private String queueName;

  @Value("${chess.rabbitmq.exchange.analysis}")
  private String exchangeName;

  @Value("${chess.rabbitmq.routing-key.analysis}")
  private String routingKey;

  @Bean
  public Queue analysisQueue() {
    return QueueBuilder.durable(queueName).quorum().build();
  }

  @Bean
  public DirectExchange analysisExchange() {
    return new DirectExchange(exchangeName);
  }

  @Bean
  public Binding analysisBinding(Queue analysisQueue, DirectExchange analysisExchange) {
    return BindingBuilder
      .bind(analysisQueue)
      .to(analysisExchange)
      .with(routingKey);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new JacksonJsonMessageConverter();
  }
}
