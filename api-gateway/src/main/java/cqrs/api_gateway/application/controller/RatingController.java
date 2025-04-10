package cqrs.api_gateway.application.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cqrs.api_gateway.core.domain.Rating;
import cqrs.api_gateway.core.usecase.RatingUseCase;

@RestController
@RequestMapping("/rating")
public class RatingController {

  private final RatingUseCase ratingUseCase;

  public RatingController(RatingUseCase ratingUseCase) {
    this.ratingUseCase = ratingUseCase;
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody Rating rating) {
    ratingUseCase.create(rating);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Rating> findRatingById(@PathVariable UUID id) {
    return ResponseEntity.ok(ratingUseCase.findRatingById(id));
  }

}
