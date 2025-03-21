package cqrs.rating_query_service.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Rating {

  private UUID id;
  private UUID ownerId;
  private UUID productId;
  private Integer rate;
  private String feedback;
  private LocalDateTime created;

  public Rating() {}

  public Rating(
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

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getOwnerId() {
    return this.ownerId;
  }

  public void setOwnerId(UUID ownerId) {
    this.ownerId = ownerId;
  }

  public UUID getProductId() {
    return this.productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  public Integer getRate() {
    return this.rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public String getFeedback() {
    return this.feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

}
