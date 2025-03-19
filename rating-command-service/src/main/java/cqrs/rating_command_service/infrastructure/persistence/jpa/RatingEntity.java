package cqrs.rating_command_service.infrastructure.persistence.jpa;

import java.time.LocalDateTime;
import java.util.UUID;

import cqrs.rating_command_service.core.domain.Rating;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class RatingEntity {

  @Id
  @GeneratedValue
  private UUID id;

  private UUID ownerId;
  private UUID productId;
  private Integer rate;
  private String feedback;
  private LocalDateTime created;

  @OneToOne(mappedBy = "ratingEntity", cascade = CascadeType.ALL)
  private SourceEntity sourceEntity;

  public RatingEntity() {}

  public RatingEntity(
    UUID id,
    UUID ownerId,
    UUID productId,
    Integer rate,
    String feedback,
    LocalDateTime created
  ) {
    this.id = id;
    this.ownerId = ownerId;
    this.productId = productId;
    this.rate = rate;
    this.feedback = feedback;
    this.created = created;
  }

  public RatingEntity fromDomainToEntity(Rating rating) {
    return new RatingEntity(
      rating.getId(),
      rating.getOwnerId(),
      rating.getProductId(),
      rating.getRate(),
      rating.getFeedback(),
      rating.getCreated()
    );
  }

  public Rating fromEntityToDomain() {
    return new Rating(
      id,
      ownerId,
      productId,
      rate,
      feedback,
      created
    );
  }

}
