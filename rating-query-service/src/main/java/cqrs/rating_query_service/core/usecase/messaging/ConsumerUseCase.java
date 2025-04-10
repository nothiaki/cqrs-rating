package cqrs.rating_query_service.core.usecase.messaging;

public interface ConsumerUseCase {

  public String findOneRating(String id);
  public String findAllRating();

}
