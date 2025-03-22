package cqrs.rating_command_service.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.repository.RatingRepository;
import cqrs.rating_command_service.core.shared.StringHandler;
import cqrs.rating_command_service.core.usecase.RatingUseCase;
import cqrs.rating_command_service.core.usecase.SourceUseCase;
import cqrs.rating_command_service.core.usecase.messaging.MessagingTopics;
import cqrs.rating_command_service.core.usecase.messaging.ProducerUseCase;

@Service
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
    rating.setCreated(LocalDateTime.now());

    Rating ratingSaved = ratingRepository.save(rating);

    sourceUseCase.createRating(ratingSaved);

    String stringfiedRating = stringHandler.serialize(ratingSaved);

    producerUseCase.send(MessagingTopics.RATING_CREATE, stringfiedRating);
  }

}
