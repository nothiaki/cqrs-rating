package cqrs.rating_query_service.core.repository;

import java.util.List;

import cqrs.rating_query_service.core.domain.Rating;

public interface RatingRepository {

  public List<Rating> findAll();

}
