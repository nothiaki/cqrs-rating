package cqrs.rating_query_service.application.usecase.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.shared.StringHandler;
import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;
import cqrs.rating_query_service.core.usecase.messaging.MessagingTopics;

@Service
public class ConsumerUseCaseImpl implements ConsumerUseCase {

  private final StringHandler<Rating> stringHandler;

  public ConsumerUseCaseImpl(StringHandler<Rating> stringHandler) {
    this.stringHandler = stringHandler;
  }

  @KafkaListener(
    topics = MessagingTopics.RATING_CREATE,
    groupId = MessagingTopics.RATING_QUERY_SERVICE_GROUP
  )
  @Override
  public void ratingCreate(String payload) {
    Rating rating = stringHandler.deserialize(payload, Rating.class);
  }
}
