package cqrs.rating_command_service.core.usecase.messaging;

public interface ConsumerUseCase {

  public void ratingCreate(String payload);

}
