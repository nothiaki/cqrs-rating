package cqrs.rating_command_service.core.repository;

import cqrs.rating_command_service.core.domain.Source;

public interface SourceRepository {

  public Source save(Source source);

}

