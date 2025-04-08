package cqrs.rating_query_service.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.repository.RatingRepository;
import cqrs.rating_query_service.infrastructure.persistence.elasticsearch.RatingElasticsearchRepository;
import cqrs.rating_query_service.infrastructure.persistence.elasticsearch.RatingEntity;

@Component
public class RatingRepositoryImpl implements RatingRepository {

  private final RatingElasticsearchRepository ratingElasticsearchRepository;

  public RatingRepositoryImpl(RatingElasticsearchRepository ratingElasticsearchRepository) {
    this.ratingElasticsearchRepository = ratingElasticsearchRepository;
  }

  @Override
  public List<Rating> findAll() {
    Iterable<RatingEntity> entities = ratingElasticsearchRepository.findAll();

    return StreamSupport
      .stream(entities.spliterator() ,false)
      .map(entity -> entity.fromEntityToDomain())
      .collect(Collectors.toList());
  }

  @Override
  public Optional<Rating> findOneById(UUID id) {
    Optional<RatingEntity> entity = ratingElasticsearchRepository.findByPayloadAfterId(id.toString());

    return entity.map(et -> et.fromEntityToDomain());
  }

}

