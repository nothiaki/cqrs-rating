package cqrs.rating_command_service.core.usecase;

import cqrs.rating_command_service.core.domain.Rating;

public interface RatingUseCase {

  public void create(Rating rating);

}
