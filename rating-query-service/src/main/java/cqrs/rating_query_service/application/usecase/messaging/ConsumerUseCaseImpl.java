package cqrs.rating_query_service.application.usecase.messaging;

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

  @Override
  public void ratingCreate(String payload) {
    Rating rating = stringHandler.deserialize(payload, Rating.class);
  }
}
