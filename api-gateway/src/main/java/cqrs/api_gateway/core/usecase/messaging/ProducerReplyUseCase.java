package cqrs.api_gateway.core.usecase.messaging;

public interface ProducerReplyUseCase {

  public String send(String topic, String payload);

}
