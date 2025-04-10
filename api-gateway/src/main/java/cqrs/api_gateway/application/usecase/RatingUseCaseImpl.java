package cqrs.api_gateway.application.usecase;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;

import cqrs.api_gateway.core.domain.Rating;
import cqrs.api_gateway.core.shared.StringHandler;
import cqrs.api_gateway.core.usecase.RatingUseCase;
import cqrs.api_gateway.core.usecase.messaging.MessagingTopics;
import cqrs.api_gateway.core.usecase.messaging.ProducerUseCase;

@Service
public class RatingUseCaseImpl implements RatingUseCase {

  private final ProducerUseCase producerUseCase;
  private final StringHandler<Rating> stringHandler;
  private final ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

  public RatingUseCaseImpl(
    ProducerUseCase producerUseCase,
    StringHandler<Rating> stringHandler,
    ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate
  ) {
    this.producerUseCase = producerUseCase;
    this.stringHandler = stringHandler;
    this.replyingKafkaTemplate = replyingKafkaTemplate;
  }

  @Override
  public void create(Rating rating) {
    String payload = stringHandler.serialize(rating);

    producerUseCase.send(MessagingTopics.RATING_CREATE, payload);
  }

  @Override
  public Rating findRatingById(UUID id) {
    String requestTopic = "rating-query-service.rating.find-one";
    String replyTopic = requestTopic + ".reply";

    ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, id.toString());

    replyingKafkaTemplate.setDefaultReplyTimeout(Duration.ofSeconds(10));
    replyingKafkaTemplate.setDefaultTopic(replyTopic);

    RequestReplyFuture<String, String, String> future = 
        replyingKafkaTemplate.sendAndReceive(record);

    try {
      ConsumerRecord<String, String> reply = future.get(10, TimeUnit.SECONDS);

      return stringHandler.deserialize(reply.value(), Rating.class);
    } catch (Exception e) {
      throw new RuntimeException("Error while getting rating by id " + e);
    }
  }

}
