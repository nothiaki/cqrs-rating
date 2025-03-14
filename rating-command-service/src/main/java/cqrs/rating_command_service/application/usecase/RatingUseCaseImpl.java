package cqrs.rating_command_service.application.usecase;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.shared.StringHandler;
import cqrs.rating_command_service.core.usecase.messaging.ProducerUseCase;

public class RatingUseCaseImpl {

  private final ProducerUseCase producerUseCase;
  private final StringHandler<Rating> stringHandler;

  public RatingUseCaseImpl(
    ProducerUseCase producerUseCase,
    StringHandler<Rating> stringHandler
  ) {
    this.producerUseCase = producerUseCase;
    this.stringHandler = stringHandler;
  }

  public void create(Rating rating) {
    //record on DB
    //create a event source

    String stringfiedRating = stringHandler.serialize(rating);

    producerUseCase.send("", stringfiedRating);
  }

}
