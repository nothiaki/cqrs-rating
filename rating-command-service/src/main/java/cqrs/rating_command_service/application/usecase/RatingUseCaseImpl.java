package cqrs.rating_command_service.application.usecase;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.repository.RatingRepository;
import cqrs.rating_command_service.core.shared.StringHandler;
import cqrs.rating_command_service.core.usecase.messaging.ProducerUseCase;

public class RatingUseCaseImpl {

  private final ProducerUseCase producerUseCase;
  private final StringHandler<Rating> stringHandler;
  private final RatingRepository ratingRepository;

  public RatingUseCaseImpl(
    ProducerUseCase producerUseCase,
    StringHandler<Rating> stringHandler,
    RatingRepository ratingRepository
  ) {
    this.producerUseCase = producerUseCase;
    this.stringHandler = stringHandler;
    this.ratingRepository = ratingRepository;
  }

  public void create(Rating rating) {
    ratingRepository.save(rating);

    //create a event source

    String stringfiedRating = stringHandler.serialize(rating);

    producerUseCase.send("", stringfiedRating);
  }

}
