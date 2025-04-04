package cqrs.rating_query_service.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity<List<Rating>> findRating() {
    return ResponseEntity.ok(ratingUseCase.findRating());
  }

}
