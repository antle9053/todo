package anthony9053.todo.repositories;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import anthony9053.todo.entities.TodoEntity;

import java.util.ArrayList;
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

  @Override
  public void delete(@NonNull Long id) {
    jdbcClient.sql("DELETE FROM todo WHERE id = :id").param("id", id).update();
  }

  @Override
  public void update(TodoEntity todoEntity, @NonNull Long id) {
    List<Object> params = new ArrayList<>();
    if (todoEntity.title() != null) {
      params.add(todoEntity.title());
    }
    if (todoEntity.content() != null) {
      params.add(todoEntity.content());
    }
    if (todoEntity.status() != null) {
      params.add(todoEntity.status());
    }

    params.add(id);

    String sql = "UPDATE todo SET " + (todoEntity.title() != null ? "title = ?, "
        : "")
        + (todoEntity.content() != null ? "content = ?, "
            : "")
        + (todoEntity.status() != null ? "status = ? " : "") + "WHERE id = ?";

    jdbcClient.sql(sql)
        .params(params)
        .update();
  }

  @Override
  public Integer count() {
    return jdbcClient.sql("SELECT * FROM todo").query().listOfRows().size();
  }
}
