package kr.or.connect.todo.persistence;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@RestController
@RequestMapping("/test")
public class TodoController
{

    private final Logger log = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private final TodoService service;

    public TodoController(TodoService service){
        this.service=service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Todo create(@RequestBody Todo book) {
        Todo newBook = service.create(book);
        log.info("book created : {}" , newBook);
        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable Integer id, @RequestBody Todo todo){
        todo.setId(id);
        System.out.println(service.update(todo));
        service.update(todo);
    }

    @GetMapping
    Collection<Todo> todoList(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    Todo read(@PathVariable Integer id){
        System.out.println(id);
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delte(@PathVariable Integer id){
        service.delete(id);
    }
}