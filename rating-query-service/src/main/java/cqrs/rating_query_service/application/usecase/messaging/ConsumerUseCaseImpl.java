package cqrs.rating_query_service.application.usecase.messaging;

import java.util.UUID;

import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.shared.StringHandler;
import cqrs.rating_query_service.core.usecase.RatingUseCase;
import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;

@Service
public class ConsumerUseCaseImpl implements ConsumerUseCase {

  private final RatingUseCase ratingUseCase;
  private final StringHandler<Rating> stringHandler;

  public ConsumerUseCaseImpl(
    RatingUseCase ratingUseCase,
    StringHandler<Rating> stringHandler
  ) {
    this.ratingUseCase = ratingUseCase;
    this.stringHandler = stringHandler;
  }

  @Override
  public String findOneRating(String id) {
    Rating rating = ratingUseCase.findOneRating(UUID.fromString(id));

    return stringHandler.serialize(rating);
  }

}
