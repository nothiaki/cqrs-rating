package cqrs.rating_query_service.infrastructure.persistence.es;

import java.util.UUID;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingEsRepository extends ElasticsearchRepository<RatingEntity, UUID> {}

