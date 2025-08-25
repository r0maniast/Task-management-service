package com.romankrivtsov.tms.application;

import com.romankrivtsov.tms.dto.request.employee.EmployeeChangeTaskRequest;
import com.romankrivtsov.tms.dto.request.employee.EmployeeRequest;
import com.romankrivtsov.tms.dto.response.employee.EmployeeDetailDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeSummaryDto;
import com.romankrivtsov.tms.dto.response.employee.EmployeeTasksDto;
import com.romankrivtsov.tms.entity.Employee;
import com.romankrivtsov.tms.entity.Task;
import com.romankrivtsov.tms.service.departmentService.DepartmentServiceImp;
import com.romankrivtsov.tms.service.employeeService.EmployeeServiceImp;
import com.romankrivtsov.tms.service.taskService.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeAppService {
    private final EmployeeServiceImp employeeServiceImp;
    private final DepartmentServiceImp departmentServiceImp;
    private final TaskServiceImp taskServiceImp;

    @Autowired
    public EmployeeAppService(EmployeeServiceImp employeeServiceImp, DepartmentServiceImp departmentServiceImp, TaskServiceImp taskServiceImp) {
        this.employeeServiceImp = employeeServiceImp;
        this.departmentServiceImp = departmentServiceImp;
        this.taskServiceImp = taskServiceImp;
    }

    public List<EmployeeSummaryDto> getAllEmployeesSummary() {
        List<Employee> allEmployees = employeeServiceImp.getAllEmployees();
        return allEmployees.stream()
                .map(EmployeeSummaryDto::from)
                .toList();
    }

    public EmployeeDetailDto getEmployee(int employeeId) {
        Employee employee = employeeServiceImp.getEmployee(employeeId);
        return EmployeeDetailDto.from(employee);
    }

    public EmployeeDetailDto saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setSurname(employeeRequest.getSurname());
        employee.setPosition(employeeRequest.getPosition());
        employee.setDepartment(departmentServiceImp.getDepartment(employeeRequest.getDepartmentId()));

        String patronymic = employeeRequest.getPatronymic();
        if (patronymic != null) {
            employee.setPatronymic(patronymic);
        }

        Employee savedEmployee = employeeServiceImp.saveEmployee(employee);
        return EmployeeDetailDto.from(savedEmployee);
    }

    public EmployeeDetailDto updateEmployee(int id, EmployeeRequest employeeRequest) {
        Employee employee = employeeServiceImp.getEmployee(id);
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
            employee.setDepartment(departmentServiceImp.getDepartment(departmentId));
        Employee updatedEmployee = employeeServiceImp.updateEmployee(employee);
        return EmployeeDetailDto.from(updatedEmployee);
    }

    public void deleteEmployee(int idEmployee){
        employeeServiceImp.deleteEmployee(idEmployee);
    }

    public EmployeeTasksDto getTasks(int id) {
        Employee employee = employeeServiceImp.getEmployee(id);
        return EmployeeTasksDto.from(employee);
    }

    public EmployeeTasksDto setTasks(int employeeId, EmployeeChangeTaskRequest employeeChangeTaskRequest) {
        Employee employee = employeeServiceImp.getEmployee(employeeId);
        List<Integer> oldTasksId = employee.getTasks().stream().map(Task::getId).toList();
        List<Integer> untreatedNewTasksId = employeeChangeTaskRequest.getTasksId();

        for(int taskId : oldTasksId) {
            if(!untreatedNewTasksId.contains(taskId)) {
                Task task = taskServiceImp.getTask(taskId);
                task.removePerformer(employee);
                taskServiceImp.updateTask(task);
            } else {
                untreatedNewTasksId.remove((Integer) taskId);
            }
        }

        for(int taskId : untreatedNewTasksId) {
            Task task = taskServiceImp.getTask(taskId);
            task.addPerformer(employee);
            taskServiceImp.updateTask(task);
        }
        return EmployeeTasksDto.from(employee);
    }

    public EmployeeTasksDto addTask(int idEmployee, int idTask) {
        Employee employee = employeeServiceImp.getEmployee(idEmployee);
        Task task = taskServiceImp.getTask(idTask);
        task.addPerformer(employee);
        taskServiceImp.updateTask(task);
        return EmployeeTasksDto.from(employee);
    }


    public EmployeeTasksDto removeTask(int idEmployee, int idTask) {
        Employee employee = employeeServiceImp.getEmployee(idEmployee);
        Task task = taskServiceImp.getTask(idTask);
        task.removePerformer(employee);
        taskServiceImp.updateTask(task);
        return EmployeeTasksDto.from(employee);
    }
}
