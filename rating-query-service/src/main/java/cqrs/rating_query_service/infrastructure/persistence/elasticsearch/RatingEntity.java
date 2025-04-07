package cqrs.rating_query_service.infrastructure.persistence.elasticsearch;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import cqrs.rating_query_service.core.domain.Rating;
@Document(indexName = "postgres.rating-command-service.public.rating")
public class RatingEntity {
    
  @Id
  private String id;

  @Field(name = "payload")
  private Payload payload;

  public RatingEntity() {}
    
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Payload getPayload() {
    return this.payload;
  }

  public void setPayload(Payload payload) {
    this.payload = payload;
  }

  public static class Payload {

    @Field(name = "after")
    private After after;

    public After getAfter() {
      return this.after;
    }

    public void setAfter(After after) {
      this.after = after;
    }

  }

  public static class After {

    @Field(name = "id")
    private String id;

    @Field(name = "owner_id")
    private String ownerId;

    @Field(name = "product_id")
    private String productId;

    @Field(name = "rate")
    private Integer rate;

    @Field(name = "feedback")
    private String feedback;

    @Field(name = "created")
    private Long created;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getOwnerId() {
      return ownerId;
    }

    public void setOwnerId(String ownerId) {
      this.ownerId = ownerId;
    }

    public String getProductId() {
      return productId;
    }

    public void setProductId(String productId) {
      this.productId = productId;
    }

    public Integer getRate() {
      return rate;
    }

    public void setRate(Integer rate) {
      this.rate = rate;
    }

    public String getFeedback() {
      return feedback;
    }

    public void setFeedback(String feedback) {
      this.feedback = feedback;
    }

    public Long getCreated() {
      return created;
    }

    public void setCreated(Long created) {
      this.created = created;
    }
      
  }

  public Rating fromEntityToDomain() {
    After data = this.getPayload().getAfter();
    LocalDateTime created = Instant.ofEpochMilli(data.getCreated())
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime();

    return new Rating(
      UUID.fromString(data.getId()),
      UUID.fromString(data.getOwnerId()),
      UUID.fromString(data.getProductId()),
      data.getRate(),
      data.getFeedback(),
      created
    );
  }

}
