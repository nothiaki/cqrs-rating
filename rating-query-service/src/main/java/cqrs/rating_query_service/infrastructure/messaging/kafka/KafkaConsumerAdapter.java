package cqrs.rating_query_service.infrastructure.messaging.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;

 @Service
 public class KafkaConsumerAdapter {

  private final ConsumerUseCase consumerUseCase;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaConsumerAdapter(
    ConsumerUseCase consumerUseCase,
    KafkaTemplate<String, String> kafkaTemplate
  ) {
    this.consumerUseCase = consumerUseCase;
    this.kafkaTemplate = kafkaTemplate;
  }
 
  @KafkaListener(
    topics = "rating-query-service.rating.find-one",
    groupId = "rating-query-service-group"
  )
  public void findOneRating(ConsumerRecord<String, String> consumerRecord) {
    String payload = consumerUseCase.findOneRating(consumerRecord.value());

    Headers headers = new RecordHeaders();
    headers.add(new RecordHeader(KafkaHeaders.CORRELATION_ID, 
        consumerRecord.headers().lastHeader(KafkaHeaders.CORRELATION_ID).value()));
    headers.add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, 
        consumerRecord.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value()));

    kafkaTemplate.send(new ProducerRecord<>(
        "rating-query-service.rating.find-one.reply",
        null,
        consumerRecord.key(),
        payload,
        headers
    ));
  }

  @KafkaListener(
    topics = "rating-query-service.rating.find-all",
    groupId = "rating-query-service-group"
  )
  public void findAllRating(ConsumerRecord<String, String> consumerRecord) {
    String payload = consumerUseCase.findAllRating();

    Headers headers = new RecordHeaders();
    headers.add(new RecordHeader(KafkaHeaders.CORRELATION_ID, 
        consumerRecord.headers().lastHeader(KafkaHeaders.CORRELATION_ID).value()));
    headers.add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, 
        consumerRecord.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value()));

    kafkaTemplate.send(new ProducerRecord<>(
        "rating-query-service.rating.find-all.reply",
        null,
        consumerRecord.key(),
        payload,
        headers
    ));
  }

 }
