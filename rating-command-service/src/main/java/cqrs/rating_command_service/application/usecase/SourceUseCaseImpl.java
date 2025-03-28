package cqrs.rating_command_service.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import cqrs.rating_command_service.core.domain.EventType;
import cqrs.rating_command_service.core.domain.Rating;
import cqrs.rating_command_service.core.domain.Source;
import cqrs.rating_command_service.core.repository.SourceRepository;
import cqrs.rating_command_service.core.shared.StringHandler;
import cqrs.rating_command_service.core.usecase.SourceUseCase;

@Service
public class SourceUseCaseImpl implements SourceUseCase{

  private final SourceRepository sourceRepository;
  private final StringHandler<Rating> stringHandler;

  public SourceUseCaseImpl(
    SourceRepository sourceRepository,
    StringHandler<Rating> stringHandler
  ) {
    this.sourceRepository = sourceRepository;
    this.stringHandler = stringHandler;
  }

  @Override
  public void createRating(Rating rating) {
    Source source = new Source();

    String stringfiedRating = stringHandler.serialize(rating);

    source.setRating(stringfiedRating);
    source.setEventType(EventType.CREATE_RATING);
    source.setCreated(LocalDateTime.now());

    sourceRepository.save(source);
  }

}
