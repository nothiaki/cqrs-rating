package cqrs.rating_query_service.infrastructure.persistence.es;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import cqrs.rating_query_service.core.domain.Rating;

@Document(indexName = "rating")
public class RatingEntity {

  @Id
  private UUID id;

  private UUID ownerId;
  private UUID productId;
  private Integer rate;
  private String feedback;
  private LocalDateTime created;

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
