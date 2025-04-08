package cqrs.rating_query_service.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cqrs.rating_query_service.core.domain.Rating;
import cqrs.rating_query_service.core.usecase.RatingUseCase;

@RestController
@RequestMapping("/rating")
public class RatingController {

  private final RatingUseCase ratingUseCase;

  public RatingController(RatingUseCase ratingUseCase) {
    this.ratingUseCase = ratingUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Rating>> findAllRating() {
    return ResponseEntity.ok(ratingUseCase.findAllRating());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Rating> findOneRating(@PathVariable UUID id) {
    return ResponseEntity.ok(ratingUseCase.findOneRating(id));
  }

}
