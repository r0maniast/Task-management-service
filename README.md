# Task Management Service 

Учебное rest-приложение для управления задачами и их отслеживанием.

## Автор

Кривцов Роман

## Будущий функционал

REST API для: 
- создания, редактирование, просмотр и удаление: 
    - задач, работников, департаментов
- управление статусами задач


## Структура проекта
````
src/main/java/com/romankrivtsov/tms/
├── TmsApplication.java                    ← Главный класс Spring Boot
├── entity/                               ← Сущности БД
│   ├── Employee.java
│   ├── Task.java
│   ├── Department.java
│   └── BaseEntity.java                   ← Базовый класс (опционально)
├── dao/                                  ← Репозитории (Data Access Objects)
│   ├── EmployeeRepository.java
│   ├── TaskRepository.java
│   └── DepartmentRepository.java
├── service/                              ← Доменные сервисы
│   ├── employeeService/
│   │   ├── EmployeeService.java
│   │   └── EmployeeServiceImp.java
│   ├── taskService/
│   │   ├── TaskService.java
│   │   └── TaskServiceImp.java
│   └── departmentService/
│       ├── DepartmentService.java
│       └── DepartmentServiceImp.java
├── application/                          ← Application Services (координаторы)
│   ├── EmployeeApplicationService.java
│   ├── TaskApplicationService.java
│   ├── DepartmentApplicationService.java
│   └── DashboardApplicationService.java
├── dto/                                  ← Data Transfer Objects
│   ├── request/                          ← DTO для входящих запросов
│   │   ├── CreateEmployeeRequest.java
│   │   ├── UpdateEmployeeRequest.java
│   │   ├── CreateTaskRequest.java
│   │   └── UpdateTaskRequest.java
│   ├── response/                         ← DTO для исходящих ответов
│   │   ├── EmployeeDto.java
│   │   ├── TaskDto.java
│   │   ├── DepartmentDto.java
│   │   └── EmployeeTasksDto.java
│   └── common/                           ← Общие DTO
│       ├── ApiResponse.java
│       └── ErrorResponse.java
├── controller/                           ← REST контроллеры
│   ├── EmployeeRestController.java
│   ├── TaskRestController.java
│   ├── DepartmentRestController.java
│   └── DashboardRestController.java
├── exception/                            ← Кастомные исключения
│   ├── EmployeeNotFoundException.java
│   ├── TaskNotFoundException.java
│   └── GlobalExceptionHandler.java
├── config/                               ← Конфигурации
│   ├── WebConfig.java
│   └── SecurityConfig.java
└── util/                                 ← Утилиты
    ├── DateUtils.java
    └── ValidationUtils.java

````