package cqrs.rating_query_service.infrastructure.presistence.elasticsearch;

import java.util.UUID;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RatingElasticsearchRepository extends ElasticsearchRepository<RatingEntity, UUID> {}

