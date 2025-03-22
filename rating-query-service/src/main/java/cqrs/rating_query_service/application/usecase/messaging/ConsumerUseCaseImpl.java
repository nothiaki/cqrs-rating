package cqrs.rating_query_service.application.usecase.messaging;

import java.util.UUID;

import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.shared.StringHandler;
import cqrs.rating_query_service.core.usecase.messaging.ConsumerUseCase;
import cqrs.rating_query_service.infrastructure.config.elasticsearch.ElasticSearchConfig;

@Service
public class ConsumerUseCaseImpl implements ConsumerUseCase {

  private final StringHandler<Rating> stringHandler;
  private final ElasticSearchConfig elasticSearchConfig;

  public ConsumerUseCaseImpl(
    StringHandler<Rating> stringHandler,
    ElasticSearchConfig elasticSearchConfig
  ) {
    this.stringHandler = stringHandler;
    this.elasticSearchConfig = elasticSearchConfig;
  }

  @Override
  public void ratingCreate(String payload) {
    Rating rating = stringHandler.deserialize(payload, Rating.class);

    ElasticsearchClient elasticsearchClient = elasticSearchConfig.elasticsearchClient();

    IndexRequest<Rating> indexRequest = new IndexRequest.Builder<Rating>()
      .index("rating")
      .id(UUID.randomUUID().toString())
      .document(rating)
      .build();

    try {
      elasticsearchClient.index(indexRequest);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
