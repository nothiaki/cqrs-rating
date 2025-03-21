package cqrs.rating_query_service.application.shared;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cqrs.rating_query_service.core.shared.StringHandler;

@Service
public class JsonStringHandlerImpl<T> implements StringHandler<T> {

  private final ObjectMapper objectMapper;

  public JsonStringHandlerImpl(ObjectMapper objectMapper) {
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
