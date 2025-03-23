package cqrs.rating_query_service.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.repository.RatingRepository;
import cqrs.rating_query_service.infrastructure.persistence.es.RatingEntity;
import cqrs.rating_query_service.infrastructure.persistence.es.RatingEsRepository;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

  private final RatingEsRepository ratingEsRepository;

  public RatingRepositoryImpl(RatingEsRepository ratingEsRepository) {
    this.ratingEsRepository = ratingEsRepository;
  }

  @Override
  public Rating save(Rating rating) {
    RatingEntity ratingEntity = new RatingEntity().fromDomainToEntity(rating);
    RatingEntity savedEntity = ratingEsRepository.save(ratingEntity);

    return savedEntity.fromEntityToDomain();
  }

}

