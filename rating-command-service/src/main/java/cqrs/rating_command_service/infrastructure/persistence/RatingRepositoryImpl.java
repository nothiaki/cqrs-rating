package cqrs.rating_command_service.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.repository.RatingRepository;
import cqrs.rating_command_service.infrastructure.persistence.jpa.RatingEntity;
import cqrs.rating_command_service.infrastructure.persistence.jpa.RatingJpaRepository;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

  private final RatingJpaRepository ratingJpaRepository;

  public RatingRepositoryImpl(RatingJpaRepository ratingJpaRepository) {
    this.ratingJpaRepository = ratingJpaRepository;
  }

  @Override
  public Rating save(Rating rating) {
    RatingEntity ratingEntity = new RatingEntity().fromDomainToEntity(rating);
    RatingEntity savedEntity = ratingJpaRepository.save(ratingEntity);

    return savedEntity.fromEntityToDomain();
  }

}

