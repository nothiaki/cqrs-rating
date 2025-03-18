package cqrs.rating_command_service.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.repository.RatingRepository;
import cqrs.rating_command_service.infrastructure.persistence.jpa.RatingEntity;
import cqrs.rating_command_service.infrastructure.persistence.jpa.RatingJpaRepository;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

  private final RatingJpaRepository jpaRepository;

  public RatingRepositoryImpl(RatingJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public Rating save(Rating rating) {
    RatingEntity ratingEntity = RatingEntity.fromDomainToEntity(rating);
    RatingEntity savedEntity = jpaRepository.save(ratingEntity);

    return savedEntity.fromEntityToDomain();
  }

}

