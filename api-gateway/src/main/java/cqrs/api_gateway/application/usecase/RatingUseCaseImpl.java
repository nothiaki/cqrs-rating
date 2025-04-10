package cqrs.api_gateway.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import cqrs.api_gateway.core.domain.Rating;
import cqrs.api_gateway.core.shared.StringHandler;
import cqrs.api_gateway.core.usecase.RatingUseCase;
import cqrs.api_gateway.core.usecase.messaging.MessagingTopics;
import cqrs.api_gateway.core.usecase.messaging.ProducerReplyUseCase;
import cqrs.api_gateway.core.usecase.messaging.ProducerUseCase;

@Service
public class RatingUseCaseImpl implements RatingUseCase {

  private final ProducerUseCase producerUseCase;
  private final StringHandler<Rating> stringHandler;
  private final ProducerReplyUseCase producerReplyUseCase;

  public RatingUseCaseImpl(
    ProducerUseCase producerUseCase,
    StringHandler<Rating> stringHandler,
    ProducerReplyUseCase producerReplyUseCase
  ) {
    this.producerUseCase = producerUseCase;
    this.stringHandler = stringHandler;
    this.producerReplyUseCase = producerReplyUseCase;
  }

  @Override
  public void create(Rating rating) {
    String payload = stringHandler.serialize(rating);

    producerUseCase.send(MessagingTopics.RATING_CREATE, payload);
  }

  @Override
  public Rating findRatingById(UUID id) {
    String reply = producerReplyUseCase.send("rating-query-service.rating.find-one", id.toString());

    return stringHandler.deserialize(reply, Rating.class);
  }

}
