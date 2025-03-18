package cqrs.rating_command_service.core.repository;

import cqrs.rating_command_service.core.domain.Rating;

public interface RatingRepository {

  public Rating save(Rating rating);

}

