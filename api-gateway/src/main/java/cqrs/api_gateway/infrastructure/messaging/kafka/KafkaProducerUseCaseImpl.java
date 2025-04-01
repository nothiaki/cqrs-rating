package cqrs.api_gateway.infrastructure.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cqrs.api_gateway.core.usecase.messaging.ProducerUseCase;

@Service
public class KafkaProducerUseCaseImpl implements ProducerUseCase {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaProducerUseCaseImpl(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void send(String topic, String payload) {
    kafkaTemplate.send(topic, payload);
  }
}
