package cqrs.rating_query_service.application.usecase.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;

@Service
public class ConsumerUseCaseImpl implements ConsumerUseCase {

  @KafkaListener(
    topics = "rating-command-service.rating.create",
    groupId = "rating-query-service-group"
  )
  @Override
  public void ratingCreate(String payload) {
    System.out.println("message: " + payload);
  }
}
