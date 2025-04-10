package cqrs.api_gateway.infrastructure.messaging.kafka;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import cqrs.api_gateway.core.usecase.messaging.ProducerReplyUseCase;

@Service
public class KafkaProducerReplyUseCaseImpl implements ProducerReplyUseCase {

  ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

  public KafkaProducerReplyUseCaseImpl(
    ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate
  ) {
    this.replyingKafkaTemplate = replyingKafkaTemplate;
  }

  @Override
  public String send(String topic, String payload, String correlationId) {
    String replyTopic = topic + ".reply";

    ProducerRecord<String, String> record = new ProducerRecord<>(topic, payload);
    record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
    record.headers().add(new RecordHeader(KafkaHeaders.CORRELATION_ID, correlationId.getBytes()));


    replyingKafkaTemplate.setDefaultReplyTimeout(Duration.ofSeconds(10));
    replyingKafkaTemplate.setDefaultTopic(replyTopic);

    RequestReplyFuture<String, String, String> future = replyingKafkaTemplate
      .sendAndReceive(record);

    try {
      ConsumerRecord<String, String> reply = future.get(10, TimeUnit.SECONDS);

      return reply.value();
    } catch (Exception e) {
      throw new RuntimeException("Error while getting reply" + e);
    }
  }

}
