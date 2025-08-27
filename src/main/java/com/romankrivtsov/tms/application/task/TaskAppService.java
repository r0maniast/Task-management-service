package com.romankrivtsov.tms.application.task;

import com.romankrivtsov.tms.dto.request.task.TaskChangePerformerRequest;
import com.romankrivtsov.tms.dto.request.task.TaskRequest;
import com.romankrivtsov.tms.dto.response.task.TaskDetailDto;
import com.romankrivtsov.tms.dto.response.task.TaskSummaryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskAppService {

    List<TaskSummaryDto> getAllTasksSummary();

    TaskDetailDto getTaskWithPerformers(int taskId);

    TaskDetailDto saveTask(TaskRequest taskRequest);

    TaskDetailDto updateTask(int id, TaskRequest taskRequest);

    TaskDetailDto setPerformers(int taskId, TaskChangePerformerRequest taskChangePerformerRequest);

    TaskDetailDto addPerformer(int idTask, int idEmployee);

    TaskDetailDto removePerformers(int idTask, int idEmployee);

    void deleteTask(int idTask);
}
