package cqrs.rating_query_service.core.repository;

import cqrs.rating_query_service.core.domain.Rating;

public interface RatingRepository {

  public Rating save(Rating rating);

}

