package cqrs.rating_query_service.core.usecase.messaging;

public interface ConsumerUseCase {

  public void ratingCreate(String payload);

}
