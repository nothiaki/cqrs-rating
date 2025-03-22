package cqrs.rating_query_service.infrastructure.messaging.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;
import cqrs.rating_query_service.core.usecase.messaging.MessagingTopics;

@Service
public class KafkaConsumerAdapter {

  private final ConsumerUseCase consumerUseCase;

  public KafkaConsumerAdapter(ConsumerUseCase consumerUseCase) {
    this.consumerUseCase = consumerUseCase;
  }

  @KafkaListener(
    topics = MessagingTopics.RATING_CREATE,
    groupId = MessagingTopics.RATING_QUERY_SERVICE_GROUP
  )
  public void ratingCreate(String payload) {
    consumerUseCase.ratingCreate(payload);
  }
}
