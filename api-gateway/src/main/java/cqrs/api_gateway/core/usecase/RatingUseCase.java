package cqrs.api_gateway.core.usecase;

import java.util.UUID;

import cqrs.api_gateway.core.domain.Rating;

public interface RatingUseCase {

  public void create(Rating rating);
  public Rating findRatingById(UUID id);

}
