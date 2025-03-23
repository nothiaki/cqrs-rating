package cqrs.rating_query_service.application.usecase;

import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.repository.RatingRepository;
import cqrs.rating_query_service.core.usecase.RatingUseCase;

@Service
public class RatingUseCaseImpl implements RatingUseCase {

  private final RatingRepository ratingRepository;

  public RatingUseCaseImpl(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  @Override
  public Rating create(Rating rating) {
    return ratingRepository.save(rating);
  }

}
