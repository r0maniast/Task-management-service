package com.romankrivtsov.tms.application.employee;

import com.romankrivtsov.tms.dto.request.employee.EmployeeChangeTaskRequest;
import com.romankrivtsov.tms.dto.request.employee.EmployeeRequest;
import com.romankrivtsov.tms.dto.response.employee.EmployeeDetailDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeSummaryDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeTasksDto;
import com.romankrivtsov.tms.entity.Employee;
import com.romankrivtsov.tms.entity.Task;
import com.romankrivtsov.tms.service.departmentService.DepartmentService;
import com.romankrivtsov.tms.service.employeeService.EmployeeService;
import com.romankrivtsov.tms.service.taskService.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeAppServiceImp implements EmployeeAppService{
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final TaskService taskService;

    @Autowired
    public EmployeeAppServiceImp(EmployeeService employeeService, DepartmentService departmentService, TaskService taskService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.taskService = taskService;
    }

    @Override
    public List<EmployeeSummaryDto> getAllEmployeesSummary() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.stream()
                .map(EmployeeSummaryDto::from)
                .toList();
    }

    @Override
    public EmployeeDetailDto getEmployee(int employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return EmployeeDetailDto.from(employee);
    }

    @Override
    public EmployeeDetailDto saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setSurname(employeeRequest.getSurname());
        employee.setPosition(employeeRequest.getPosition());
        employee.setDepartment(departmentService.getDepartment(employeeRequest.getDepartmentId()));

        String patronymic = employeeRequest.getPatronymic();
        if (patronymic != null) {
            employee.setPatronymic(patronymic);
        }

        Employee savedEmployee = employeeService.saveEmployee(employee);
        return EmployeeDetailDto.from(savedEmployee);
    }

    @Override
    public EmployeeDetailDto updateEmployee(int id, EmployeeRequest employeeRequest) {
        Employee employee = employeeService.getEmployee(id);
        String name = employeeRequest.getName();
        String patronymic = employeeRequest.getPatronymic();
        String surname = employeeRequest.getSurname();
        String position = employeeRequest.getPosition();
        int departmentId = employeeRequest.getDepartmentId();
        if (name != null)
            employee.setName(name);
        if (patronymic != null)
            employee.setPatronymic(patronymic);
        if (surname != null)
            employee.setSurname(surname);
        if (position != null)
            employee.setPosition(position);
        if (departmentId != 0)
            employee.setDepartment(departmentService.getDepartment(departmentId));
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return EmployeeDetailDto.from(updatedEmployee);
    }

    @Override
    public void deleteEmployee(int idEmployee){
        employeeService.deleteEmployee(idEmployee);
    }

    @Override
    public EmployeeTasksDto getTasks(int id) {
        Employee employee = employeeService.getEmployee(id);
        return EmployeeTasksDto.from(employee);
    }

    @Override
    public EmployeeTasksDto setTasks(int employeeId, EmployeeChangeTaskRequest employeeChangeTaskRequest) {
        Employee employee = employeeService.getEmployee(employeeId);
        List<Integer> oldTasksId = employee.getTasks().stream().map(Task::getId).toList();
        List<Integer> untreatedNewTasksId = employeeChangeTaskRequest.getTasksId();

        for(int taskId : oldTasksId) {
            if(!untreatedNewTasksId.contains(taskId)) {
                Task task = taskService.getTask(taskId);
                task.removePerformer(employee);
                taskService.updateTask(task);
            } else {
                untreatedNewTasksId.remove((Integer) taskId);
            }
        }

        for(int taskId : untreatedNewTasksId) {
            Task task = taskService.getTask(taskId);
            task.addPerformer(employee);
            taskService.updateTask(task);
        }
        return EmployeeTasksDto.from(employee);
    }

    @Override
    public EmployeeTasksDto addTask(int idEmployee, int idTask) {
        Employee employee = employeeService.getEmployee(idEmployee);
        Task task = taskService.getTask(idTask);
        task.addPerformer(employee);
        taskService.updateTask(task);
        return EmployeeTasksDto.from(employee);
    }

    @Override
    public EmployeeTasksDto removeTask(int idEmployee, int idTask) {
        Employee employee = employeeService.getEmployee(idEmployee);
        Task task = taskService.getTask(idTask);
        task.removePerformer(employee);
        taskService.updateTask(task);
        return EmployeeTasksDto.from(employee);
    }
}
