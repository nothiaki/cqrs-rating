package cqrs.api_gateway.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import cqrs.api_gateway.core.domain.Rating;
import cqrs.api_gateway.core.usecase.RatingUseCase;

@Service
public class RatingUseCaseImpl implements RatingUseCase {

  @Override
  public void create(Rating rating) {
    //send to message broker
  }

  @Override
  public List<Rating> findRating() {
    //send to message broker and catch the response to send for client
    return null;
  }

}
