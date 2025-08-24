package com.romankrivtsov.tms.service.taskService;

import com.romankrivtsov.tms.entity.Task;

import java.util.List;

public interface TaskService {

    Task saveTask(Task task);

    Task updateTask(Task task);

    void deleteTask(int id);

    Task getTask(int id);

    List<Task> getAllTasks();


}
