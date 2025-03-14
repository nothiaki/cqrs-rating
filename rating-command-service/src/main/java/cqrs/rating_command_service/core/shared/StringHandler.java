package cqrs.rating_command_service.core.shared;

public interface StringHandler<T> {

  public String serialize(T obj);
  public T deserialize(String str, Class<T> type);

}
