package cqrs.rating_query_service.infrastructure.presistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.repository.RatingRepository;
import cqrs.rating_query_service.infrastructure.presistence.elasticsearch.RatingElasticsearchRepository;
import cqrs.rating_query_service.infrastructure.presistence.elasticsearch.RatingEntity;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

  private final RatingElasticsearchRepository ratingElasticsearchRepository;

  public RatingRepositoryImpl(RatingElasticsearchRepository ratingElasticsearchRepository) {
    this.ratingElasticsearchRepository = ratingElasticsearchRepository;
  }

  @Override
  public List<Rating> findAll() {
    Iterable<RatingEntity> entitiesIterable = ratingElasticsearchRepository.findAll();

    List<RatingEntity> entities = new ArrayList<>();

    entitiesIterable.forEach(entities::add);

    return entities.stream()
      .map(entity -> entity.fromEntityToDomain())
      .collect(Collectors.toList());
  }

}

