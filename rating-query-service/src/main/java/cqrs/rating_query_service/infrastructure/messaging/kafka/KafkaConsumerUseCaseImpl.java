package cqrs.rating_query_service.infrastructure.messaging.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;

@Service
public class KafkaConsumerUseCaseImpl implements ConsumerUseCase {

  @KafkaListener(
    topics = "rating-command-service.rating.create",
    groupId = "rating-query-service-group"
  )
  @Override
  public void RatingCreate(String payload) {
    System.out.println("chegou aqui");
  }
}
