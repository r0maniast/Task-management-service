## Task Management Service (TMS)

REST-приложение на Spring Boot для управления сотрудниками, департаментами и задачами с возможностью отслеживания статусов задач.

### Основные возможности
- **Сотрудники**: создание, обновление, просмотр, удаление; просмотр задач сотрудника
- **Задачи**: CRUD-операции, назначение исполнителя, смена статуса (`NEW`, `IN_PROGRESS`, `DONE`, и т.д.)
- **Департаменты**: CRUD-операции, просмотр сотрудников департамента
- **DTO-слой**: раздельные DTO для запросов и ответов
- **Сервисы**: разделение на доменные и сервисы-оркестраторы

### Технологии
- Java 17+
- Spring Boot (Web)
- Spring Data JPA (репозитории)
- Maven

### Требования
- JDK 17+
- Maven 3.9+ (или используйте скрипты `mvnw`/`mvnw.cmd`)

### Быстрый старт
1. Клонировать репозиторий
2. Настроить `src/main/resources/application.properties` (при необходимости)
3. Запуск:
   - Windows: `mvnw.cmd spring-boot:run`
   - Linux/macOS: `./mvnw spring-boot:run`
4. Приложение по умолчанию стартует на `http://localhost:8080`

### Конфигурация
Файл: `src/main/resources/application.properties`
- `server.port=8080` — порт приложения
- Настройки БД — добавьте URL/логин/пароль

### Структура проекта
```
src/main/java/com/romankrivtsov/tms/
├── TmsApplication.java
├── controller/
│   ├── EmployeeRestController.java
│   ├── TaskRestController.java
│   └── DepartmentRestController.java
├── application/
│   ├── EmployeeAppService.java
│   ├── TaskAppService.java
│   └── DepartmentAppService.java
├── service/
│   ├── employeeService/
│   │   ├── EmployeeService.java
│   │   └── EmployeeServiceImp.java
│   ├── taskService/
│   │   ├── TaskService.java
│   │   └── TaskServiceImp.java
│   └── departmentService/
│       ├── DepartmentService.java
│       └── DepartmentServiceImp.java
├── dao/
│   ├── EmployeeRepository.java
│   ├── TaskRepository.java
│   └── DepartmentRepository.java
├── dto/
│   ├── request/
│   │   ├── employee/
│   │   │   ├── EmployeeRequest.java
│   │   │   └── EmployeeChangeTaskRequest.java
│   │   ├── task/
│   │   │   ├── TaskRequest.java
│   │   │   └── TaskChangePerformerRequest.java
│   │   └── department/
│   │       └── DepartmentRequest.java
│   └── response/
│       ├── employee/
│       │   ├── EmployeeDetailDto.java
│       │   ├── EmployeeSummaryDto.java
│       │   └── EmployeeTasksDto.java
│       ├── task/
│       │   ├── TaskDetailDto.java
│       │   └── TaskSummaryDto.java
│       └── department/
│           ├── DepartmentDetailDto.java
│           └── DepartmentSummaryDto.java
├── entity/
│   ├── Employee.java
│   ├── Task.java
│   ├── Department.java
│   └── enums/TaskStatus.java
└── util/validate/
    ├── CreateValidate.java
    └── UpdateValidate.java
```

### Модель данных
- `Employee` (сотрудник) принадлежит `Department`
- `Task` (задача) может иметь исполнителя `Employee` и статус из `TaskStatus`

### Примеры API
Базовый URL: `http://localhost:8080`

- **Сотрудники** (`/employees`)
  - GET `/employees` — список сотрудников
  - GET `/employees/{id}` — детали сотрудника
  - POST `/employees` — создание сотрудника
  - PUT `/employees/{id}` — обновление сотрудника
  - DELETE `/employees/{id}` — удаление сотрудника
  - GET `/employees/{id}/tasks` — задачи сотрудника

- **Задачи** (`/tasks`)
  - GET `/tasks` — список задач
  - GET `/tasks/{id}` — детали задачи
  - POST `/tasks` — создание задачи
  - PUT `/tasks/{id}` — обновление задачи
  - PUT `/tasks/{id}/status` — смена статуса
  - DELETE `/tasks/{id}` — удаление задачи
  - PUT `/tasks/{id}/performer` — добавление исполнителя

- **Департаменты** (`/departments`)
  - GET `/departments` — список департаментов
  - GET `/departments/{id}` — детали департамента
  - POST `/departments` — создание департамента
  - PUT `/departments/{id}` — обновление департамента
  - DELETE `/departments/{id}` — удаление департамента

Примеры тел запросов:

```json
// POST /employees
{
  "firstName": "Ivan",
  "lastName": "Ivanov",
  "departmentId": 1
}
```

```json
// POST /tasks
{
  "title": "Подготовить отчёт",
  "description": "Q3 performance",
  "status": "NEW",
  "performerId": 2
}
```

```json
// PUT /tasks/{id}/status
{
  "status": "IN_PROGRESS"
}
```

### Сборка и тесты
- Сборка: `mvnw.cmd clean package` (Windows) или `./mvnw clean package`

### Автор
Кривцов Роман

### Контакты

- Email: romankrivtsov7@gmail.com
- Telegram: [@romYUkd](https://t.me/romYUkd)