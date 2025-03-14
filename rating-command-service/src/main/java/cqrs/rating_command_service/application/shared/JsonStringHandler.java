package cqrs.rating_command_service.application.shared;

import com.fasterxml.jackson.databind.ObjectMapper;

import cqrs.rating_command_service.core.shared.StringHandler;

public class JsonStringHandler<T> implements StringHandler<T> {

  private final ObjectMapper objectMapper;

  public JsonStringHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }
  
  @Override
  public String serialize(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException("Error while convert object to JSON", e);
    }
  }

  @Override
  public T deserialize(String str, Class<T> type) {
    try {
      return objectMapper.readValue(str, type);
    } catch (Exception e) {
      throw new RuntimeException("Error while convert JSON to object", e);
    }
  }

}
