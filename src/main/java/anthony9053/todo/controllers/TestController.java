package anthony9053.todo.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TestController {

  private List<String> myList = new ArrayList<>(List.of("Hello", "World"));

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public List<String> requestMethodName(
      @RequestParam(value = "param", required = false, defaultValue = "World") String param) {
    return myList;
  }

}
