package cqrs.rating_command_service.application.usecase;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.repository.RatingRepository;
import cqrs.rating_command_service.core.shared.StringHandler;
import cqrs.rating_command_service.core.usecase.RatingUseCase;
import cqrs.rating_command_service.core.usecase.SourceUseCase;
import cqrs.rating_command_service.core.usecase.messaging.ProducerUseCase;

public class RatingUseCaseImpl implements RatingUseCase{

  private final ProducerUseCase producerUseCase;
  private final StringHandler<Rating> stringHandler;
  private final RatingRepository ratingRepository;
  private final SourceUseCase sourceUseCase;

  public RatingUseCaseImpl(
    ProducerUseCase producerUseCase,
    StringHandler<Rating> stringHandler,
    RatingRepository ratingRepository,
    SourceUseCase sourceUseCase
  ) {
    this.producerUseCase = producerUseCase;
    this.stringHandler = stringHandler;
    this.ratingRepository = ratingRepository;
    this.sourceUseCase = sourceUseCase;
  }

  @Override
  public void create(Rating rating) {
    ratingRepository.save(rating);

    sourceUseCase.createRating(rating);

    String stringfiedRating = stringHandler.serialize(rating);

    producerUseCase.send("", stringfiedRating);
  }

}
