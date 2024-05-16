package anthony9053.todo.entities;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record TodoEntity(
    Integer id,
    @NotEmpty String title,
    String content,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

  public TodoEntity {

  }
}
