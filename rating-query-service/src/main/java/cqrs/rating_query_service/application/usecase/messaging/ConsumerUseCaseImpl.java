package cqrs.rating_query_service.application.usecase.messaging;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.shared.StringHandler;
import cqrs.rating_query_service.core.usecase.RatingUseCase;
import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;

@Service
public class ConsumerUseCaseImpl implements ConsumerUseCase {

  private final RatingUseCase ratingUseCase;
  private final StringHandler<Rating> stringHandler;

  public ConsumerUseCaseImpl(
    RatingUseCase ratingUseCase,
    StringHandler<Rating> stringHandler
  ) {
    this.ratingUseCase = ratingUseCase;
    this.stringHandler = stringHandler;
  }

  @Override
  public String findOneRating(String id) {
    Rating rating = ratingUseCase.findOneRating(UUID.fromString(id));

    return stringHandler.serialize(rating);
  }

  @Override
  public String findAllRating() {
    ObjectMapper objectMapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    List<Rating> ratings = ratingUseCase.findAllRating();

    try {
      return objectMapper.writeValueAsString(ratings);
    } catch (Exception e) {
      throw new RuntimeException("Error converting object to json at ConsumerUseCase" + e);
    }
  }

}
