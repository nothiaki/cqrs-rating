package cqrs.rating_command_service.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.usecase.RatingUseCase;

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

}
