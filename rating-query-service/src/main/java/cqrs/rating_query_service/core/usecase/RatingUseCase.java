package cqrs.rating_query_service.core.usecase;

import java.util.List;

import cqrs.rating_query_service.core.domain.Rating;

public interface RatingUseCase {

  public List<Rating> findRating();

}
