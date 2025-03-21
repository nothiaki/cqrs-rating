package cqrs.rating_query_service.infrastructure.config.kafka;

public class KafkaProperties {

  public static final String BOOTSTRAP_SERVERS = "${spring.kafka.bootstrap-servers}";
  public static final String GROUP_ID = "${spring.kafka.consumer.group-id}";
  public static final String AUTO_OFFSET_RESET = "${spring.kafka.consumer.auto-offset-reset}";

}
