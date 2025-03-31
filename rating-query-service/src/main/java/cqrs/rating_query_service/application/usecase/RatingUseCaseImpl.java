package cqrs.rating_query_service.application.usecase;

import java.util.List;

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
  public List<Rating> findRating() {
    return ratingRepository.findAll();
  }

}
