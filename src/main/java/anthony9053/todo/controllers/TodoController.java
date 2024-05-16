package anthony9053.todo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anthony9053.todo.entities.TodoEntity;
import anthony9053.todo.repositories.JDBCTodoRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
  private final JDBCTodoRepository todoRepository;

  TodoController(JDBCTodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping("")
  List<TodoEntity> findAll() {
    return todoRepository.findAll();
  }

  @GetMapping("/{id}")
  Optional<TodoEntity> getMethodName(@PathVariable Long id) {
    return todoRepository.findById(id);
  }

  @PostMapping("/create")
  public void postMethodName(@RequestBody TodoEntity todoEntity) {

    todoRepository.create(todoEntity);
  }

}
