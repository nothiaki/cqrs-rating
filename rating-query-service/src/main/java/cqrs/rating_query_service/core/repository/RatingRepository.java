package cqrs.rating_query_service.core.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import cqrs.rating_query_service.core.domain.Rating;

public interface RatingRepository {

  public List<Rating> findAll();
  public Optional<Rating> findOneById(UUID id);

}
