package cqrs.rating_command_service.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Source {

  private UUID id;
  private Rating rating;
  private EventType eventType;
  private LocalDateTime created;

  public Source() {}

  public Source(
    UUID id,
    Rating rating,
    EventType eventType,
    LocalDateTime created
  ) {
    this.id = id;
    this.rating = rating;
    this.eventType = eventType;
    this.created = created;
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Rating getRating() {
    return this.rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public EventType getEventType() {
    return this.eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

}
