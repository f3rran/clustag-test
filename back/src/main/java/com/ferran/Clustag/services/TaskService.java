package com.ferran.Clustag.services;

import com.ferran.Clustag.models.TaskModel;
import com.ferran.Clustag.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public ArrayList<TaskModel> getTasks(){
        return (ArrayList<TaskModel>) taskRepository.findAll();
    }

    public ArrayList<TaskModel> getLastTasks(int minutes){
        Date now = new Date();
        Date twoMinutes = new Date(now.getTime() - (minutes*60*1000));
        return (ArrayList<TaskModel>) taskRepository.getByDate(twoMinutes);
    }

    public TaskModel saveTask(TaskModel task){
        return taskRepository.save(task);
    }

    public Optional<TaskModel> getById(Long id){
        return taskRepository.findById(id);
    }

    public boolean deleteTask(Long id){
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
