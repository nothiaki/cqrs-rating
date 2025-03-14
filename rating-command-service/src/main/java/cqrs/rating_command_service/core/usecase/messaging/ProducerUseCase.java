package cqrs.rating_command_service.core.usecase.messaging;

public interface ProducerUseCase {

  public void send(String topic, String payload);

}
