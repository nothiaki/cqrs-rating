package cqrs.rating_command_service.infrastructure.persistence.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceJpaRepository extends JpaRepository<SourceEntity, UUID> {}

