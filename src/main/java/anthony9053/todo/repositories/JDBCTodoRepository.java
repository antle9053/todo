package anthony9053.todo.repositories;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import anthony9053.todo.entities.TodoEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class JDBCTodoRepository implements TodoRepository {
  private final JdbcClient jdbcClient;

  public JDBCTodoRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  @Override
  @NonNull
  public List<TodoEntity> findAll() {
    return jdbcClient.sql("SELECT * FROM todo;").query(TodoEntity.class).list();
  }

  @Override
  @NonNull
  public Optional<TodoEntity> findById(@NonNull Long id) {
    return jdbcClient.sql("SELECT * FROM todo WHERE id = :id;").param("id", id).query(TodoEntity.class)
        .optional();
  }

  @Override
  public void create(TodoEntity todoEntity) {
    jdbcClient.sql("INSERT INTO todo(title,status,content) values(?,?,?)")
        .params(List.of(todoEntity.title(), todoEntity.status(), todoEntity.content()))
        .update();
  }
}
