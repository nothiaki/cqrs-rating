package cqrs.api_gateway.core.usecase;

import java.util.List;

import cqrs.api_gateway.core.domain.Rating;

public interface RatingUseCase {

  public void create(Rating rating);
  public List<Rating> findRating();

}
