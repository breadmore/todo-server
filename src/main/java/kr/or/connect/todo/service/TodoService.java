package kr.or.connect.todo.service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TodoService {

    private TodoDao dao;
    private AtomicInteger maxId=new AtomicInteger(0);
    public TodoService(TodoDao dao) {
        this.dao = dao;
    }

    public Todo findById(Integer id){
        System.out.println(id);
        if (dao.selectById(id) != null) {
            return dao.selectById(id);
        }
        else {
            Todo todo = new Todo();
            todo.setId(99999);
            todo.setTodo("tototototo");
            return todo;
        }
    }

    public Todo create(Todo todo){
        Integer id = dao.insert(todo);
        todo.setId(id);
        return todo;
    }

    public boolean update(Todo todo){
        int affected = dao.update(todo);
        return affected ==1;
    }

    public boolean delete(Integer id){
        int affected = dao.deleteById(id);
        return affected==1;
    }
    public Collection<Todo> findAll(){
        return dao.selectAll();
    }
    public int countTodo()
    {
        int count=dao.countTodo();
        return count;
    }
}
