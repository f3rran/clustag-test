package com.ferran.Clustag.controllers;

import com.ferran.Clustag.models.State;
import com.ferran.Clustag.models.TaskModel;
import com.ferran.Clustag.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@EnableScheduling
@CrossOrigin(origins = "http://localhost:8081")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping()
    public ArrayList<TaskModel> getTasks(){
        return this.taskService.getTasks();
    }

    @PostMapping()
    public TaskModel saveTask(@RequestBody TaskModel task){
        return this.taskService.saveTask(task);
    }

    @GetMapping(path = "/{id}")
    public Optional<TaskModel> getTaskById(@PathVariable("id") Long id) {
        return this.taskService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.taskService.deleteTask(id);
        if (ok){
            return "Deleted task with ID: "+id;
        } else {
            return "The task with ID: "+id+" can't be deleted";
        }
    }

    @Scheduled(fixedRate = 60000)
    public void runningTask() {
        Date now = new Date();
        ArrayList<TaskModel> tasks = taskService.getLastTasks(3);

        for (TaskModel task:tasks) {
            if (now.getTime() - task.getCreatedAt().getTime() >= 2*60*1000 && task.getState() == State.CREATED){
                task.setState(State.RUNNING);
                this.taskService.saveTask(task);
                System.out.println("Task running: " + task.getName());
            }
        }
    }

    @Scheduled(fixedRate = 60000)
    public void doneTask() {
        Date now = new Date();
        ArrayList<TaskModel> tasks = this.taskService.getLastTasks(11);

        for (TaskModel task:tasks) {
            // 2 minutes from running task + 8 minutes from done task = 10
            if (now.getTime() - task.getCreatedAt().getTime() >= 10*60*1000 && task.getState() == State.RUNNING){
                task.setState(State.DONE);
                this.taskService.saveTask(task);
                System.out.println("Task done: " + task.getName());
            }
        }
    }
}
