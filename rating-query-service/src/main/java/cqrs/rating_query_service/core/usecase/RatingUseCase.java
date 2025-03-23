package cqrs.rating_query_service.core.usecase;

import cqrs.rating_query_service.core.domain.Rating;

public interface RatingUseCase {

  public Rating create(Rating rating);

}
