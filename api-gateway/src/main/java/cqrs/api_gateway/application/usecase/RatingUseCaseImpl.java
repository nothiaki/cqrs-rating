package cqrs.api_gateway.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import cqrs.api_gateway.core.domain.Rating;
import cqrs.api_gateway.core.shared.StringHandler;
import cqrs.api_gateway.core.usecase.RatingUseCase;
import cqrs.api_gateway.core.usecase.messaging.MessagingTopics;
import cqrs.api_gateway.core.usecase.messaging.ProducerUseCase;

@Service
public class RatingUseCaseImpl implements RatingUseCase {

  private final ProducerUseCase producerUseCase;
  private final StringHandler<Rating> stringHandler;

  public RatingUseCaseImpl(
    ProducerUseCase producerUseCase,
    StringHandler<Rating> stringHandler
  ) {
    this.producerUseCase = producerUseCase;
    this.stringHandler = stringHandler;
  }

  @Override
  public void create(Rating rating) {
    String payload = stringHandler.serialize(rating);

    producerUseCase.send(MessagingTopics.RATING_CREATE, payload);
  }

  @Override
  public List<Rating> findRating() {
    //send to message broker and catch the response to send for client
    return null;
  }

}
