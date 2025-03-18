package cqrs.rating_command_service.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import cqrs.rating_command_service.core.domain.Source;
import cqrs.rating_command_service.core.repository.SourceRepository;
import cqrs.rating_command_service.infrastructure.persistence.jpa.SourceEntity;
import cqrs.rating_command_service.infrastructure.persistence.jpa.SourceJpaRepository;

@Repository
public class SourceRepositoryImpl implements SourceRepository {

  private final SourceJpaRepository sourceJpaRepository;

  public SourceRepositoryImpl(SourceJpaRepository sourceJpaRepository) {
    this.sourceJpaRepository = sourceJpaRepository;
  }

  @Override
  public Source save(Source source) {
    SourceEntity sourceEntity = SourceEntity.fromDomainToEntity(source);
    SourceEntity savedEntity = sourceJpaRepository.save(sourceEntity);

    return savedEntity.fromEntityToDomain();
  }

}

