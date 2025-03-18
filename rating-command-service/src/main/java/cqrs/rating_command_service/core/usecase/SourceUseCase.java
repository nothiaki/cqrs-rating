package cqrs.rating_command_service.core.usecase;

import cqrs.rating_command_service.core.domain.Rating;

public interface SourceUseCase {

  public void createRating(Rating rating);

}
