package cqrs.api_gateway.core.usecase.messaging;

public interface ProducerUseCase {

  public void send(String topic, String payload);

}
