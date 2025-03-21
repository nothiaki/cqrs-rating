package cqrs.rating_query_service.application.usecase.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.shared.StringHandler;
import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;

@Service
public class ConsumerUseCaseImpl implements ConsumerUseCase {

  private final StringHandler<Rating> stringHandler;

  public ConsumerUseCaseImpl(StringHandler<Rating> stringHandler) {
    this.stringHandler = stringHandler;
  }

  @KafkaListener(
    topics = "rating-command-service.rating.create",
    groupId = "rating-query-service-group"
  )
  @Override
  public void ratingCreate(String payload) {
    Rating rating = stringHandler.deserialize(payload, Rating.class);
  }
}
