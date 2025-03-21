package cqrs.rating_query_service.core.usecase.messaging;

public interface ConsumerUseCase {

  public void RatingCreate(String payload);

}
