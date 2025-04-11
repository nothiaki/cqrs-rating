package cqrs.rating_command_service.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.repository.RatingRepository;
import cqrs.rating_command_service.core.usecase.RatingUseCase;
import cqrs.rating_command_service.core.usecase.SourceUseCase;

@Service
public class RatingUseCaseImpl implements RatingUseCase{

  private final RatingRepository ratingRepository;
  private final SourceUseCase sourceUseCase;

  public RatingUseCaseImpl(
    RatingRepository ratingRepository,
    SourceUseCase sourceUseCase
  ) {
    this.ratingRepository = ratingRepository;
    this.sourceUseCase = sourceUseCase;
  }

  @Override
  public void create(Rating rating) {
    rating.setCreated(LocalDateTime.now());

    Rating ratingSaved = ratingRepository.save(rating);

    sourceUseCase.createRating(ratingSaved);
  }

}
