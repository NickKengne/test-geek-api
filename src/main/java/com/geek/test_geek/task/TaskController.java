package com.geek.test_geek.task;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task")
public class TaskController {


    private final TaskRepository taskRepository;
    HashMap<String,String> response = new HashMap<>();


    @PostMapping("/")
    public ResponseEntity<?> createTask (@RequestBody TaskDto taskDto){

        if (taskDto.getTask().isBlank()){
            response.put("message","Task data is empty !");
            response.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        }
        else {
            var task = Task.builder()
                    .task(taskDto.getTask())
                    .build();
            taskRepository.save(task);
            response.put("message","Task added !");
            response.put("status", String.valueOf(HttpStatus.CREATED));
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask(){
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") Integer taskId){
        var task = taskRepository.findById(taskId);
        if (task.isEmpty()){
            response.put("message","Task doesn't found !");
            response.put("status", String.valueOf(HttpStatus.NOT_FOUND));
        }
        else{
            response.put("message","Task delete successfully !");
            response.put("status", String.valueOf(HttpStatus.ACCEPTED));
            taskRepository.delete(task.get());
        }
        return ResponseEntity.ok(response);
    }

}
