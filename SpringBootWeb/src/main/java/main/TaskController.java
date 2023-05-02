package main;

import main.entities.Task;
import main.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(
            value = "/tasks/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity addTask(@RequestBody Task task) {
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @GetMapping("/tasks/")
    public List<Task> getAllTasks() {
        Iterable<Task> taskIterableList = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        for (Task t : taskIterableList) {
            taskList.add(t);
        }
        return taskList;
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity changeTask (@PathVariable int id, @RequestBody Map<String, Object> fields) {
        Optional<Task> taskForUpdate = taskRepository.findById(id);
        if(!taskForUpdate.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Task.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, taskForUpdate.get(), value);
        });
        return new ResponseEntity(taskRepository.save(taskForUpdate.get()),HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        if (!taskRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return null;
    }
}
