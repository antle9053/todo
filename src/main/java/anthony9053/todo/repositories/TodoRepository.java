package anthony9053.todo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import anthony9053.todo.entities.TodoEntity;

public interface TodoRepository {

  @NonNull
  List<TodoEntity> findAll();

  @NonNull
  Optional<TodoEntity> findById(@NonNull Long id);

  void create(TodoEntity todoEntity);

  void delete(@NonNull Long id);

  void update(TodoEntity todoEntity, @NonNull Long id);

  Integer count();
}
