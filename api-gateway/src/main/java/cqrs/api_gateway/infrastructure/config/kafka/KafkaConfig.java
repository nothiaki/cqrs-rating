package cqrs.api_gateway.infrastructure.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;

  @Value("${spring.kafka.consumer.auto-offset-reset}")
  private String autoOffsetReset;

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> config = new HashMap<>();

    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaProducerFactory<>(config);
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> config = new HashMap<>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

    return new DefaultKafkaConsumerFactory<>(config);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> replyListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = 
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setReplyTemplate(kafkaTemplate());
    factory.getContainerProperties().setGroupId(groupId);
    return factory;
  }

  @Bean
  public ConcurrentMessageListenerContainer<String, String> repliesContainer(
    ConcurrentKafkaListenerContainerFactory<String, String> containerFactory
  ) {
    
    ConcurrentMessageListenerContainer<String, String> repliesContainer = containerFactory
      .createContainer("rating-query-service.rating.find-one.reply");
    repliesContainer.getContainerProperties().setGroupId(groupId + "-replies");
    repliesContainer.setAutoStartup(true);
    return repliesContainer;
  }

  @Bean
  public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
    ProducerFactory<String, String> producerFactory,
    ConcurrentMessageListenerContainer<String, String> concurrentMessageListenerContainer
  ) {
    return new ReplyingKafkaTemplate<>(producerFactory, concurrentMessageListenerContainer);
  }

}
