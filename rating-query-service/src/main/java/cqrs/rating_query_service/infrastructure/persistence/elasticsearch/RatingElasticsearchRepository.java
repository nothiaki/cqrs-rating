package cqrs.rating_query_service.infrastructure.persistence.elasticsearch;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingElasticsearchRepository extends ElasticsearchRepository<RatingEntity, String> {

  Optional<RatingEntity> findByPayloadAfterId(String id);

}

