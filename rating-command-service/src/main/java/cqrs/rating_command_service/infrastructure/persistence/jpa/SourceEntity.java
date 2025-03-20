package cqrs.rating_command_service.infrastructure.persistence.jpa;

import java.time.LocalDateTime;
import java.util.UUID;

import cqrs.rating_command_service.core.domain.EventType;
import cqrs.rating_command_service.core.domain.Source;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "source")
public class SourceEntity {

  @Id
  @GeneratedValue
  private UUID id;

  private String rating;
  private EventType eventType;
  private LocalDateTime created;

  public SourceEntity() {}

  public SourceEntity(
    UUID id,
    String rating,
    EventType eventType,
    LocalDateTime created
  ) {
    this.id = id;
    this.rating = rating;
    this.eventType = eventType;
    this.created = created;
  }

  public static SourceEntity fromDomainToEntity(Source source) {
    return new SourceEntity(
      source.getId(),
      source.getRating(),
      source.getEventType(),
      source.getCreated()
    );
  }

  public Source fromEntityToDomain() {
    return new Source(
      id,
      rating,
      eventType,
      created
    );
  }

}
