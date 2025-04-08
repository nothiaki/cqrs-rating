package cqrs.rating_command_service.application.usecase.messaging;

import org.springframework.stereotype.Service;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.shared.StringHandler;
import cqrs.rating_command_service.core.usecase.RatingUseCase;
import cqrs.rating_command_service.core.usecase.messaging.ConsumerUseCase;

@Service
public class ConsumerUseCaseImpl implements ConsumerUseCase {

  private final RatingUseCase ratingUseCase;
  private final StringHandler<Rating> stringHandler;

  public ConsumerUseCaseImpl(
    StringHandler<Rating> stringHandler,
    RatingUseCase ratingUseCase
  ) {
    this.stringHandler = stringHandler;
    this.ratingUseCase = ratingUseCase;
  }

  @Override
  public void ratingCreate(String payload) {
    Rating rating = stringHandler.deserialize(payload, Rating.class);

    ratingUseCase.create(rating);
  }

}
