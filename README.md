# Task Management System (TMS)

Простая система управления задачами (Task Management System), разработанная с использованием Java и Spring Boot. Система позволяет создавать, редактировать, удалять и просматривать задачи, пользователей, которые могут быть создателями и исполнителями задач, и комментарии к задачам. Каждая задача содержит заголовок, описание, статус, приоритет, комментарии, автора и исполнителя.

## Технологии

- **Язык программирования**: Java 21
- **Фреймворк**: Spring Boot 3.4.3
- **База данных**: PostgreSQL
- **ORM**: Spring Data JPA
- **Утилиты**: Lombok
- **Контейнеризация**: Docker Compose

## Требования

Система должна поддерживать следующие функции:
1. **Управление задачами**:
   - Создание задачи (Create).
   - Редактирование задачи (Update).
   - Удаление задачи (Delete).
   - Просмотр задачи (Read).

2. **Управление пользователями**:
   - Создание пользователя (Create).
   - Редактирование пользователя (Update).
   - Удаление пользователя (Delete).
   - Просмотр пользователя (Read).

3. **Управление комментариями**:
   - Создание комментария (Create).
   - Редактирование комментария (Update).
   - Удаление комментария (Delete).
   - Просмотр комментария (Read).

### Сущности

#### Задача (Task)
- `id` (уникальный идентификатор)
- `title` (заголовок)
- `description` (описание)
- `status` (статус: "в ожидании", "в процессе", "завершено")
- `priority` (приоритет: "высокий", "средний", "низкий")
- `comments` (список комментариев)
- `author` (автор задачи, связь с сущностью User)
- `performer` (исполнитель задачи, связь с сущностью User)

#### Пользователь (User)
- `id` (уникальный идентификатор)
- `name` (имя пользователя)
- `сreatedTasks` (список задач, созданных пользователем)
- `performedTasks` (список задач, назначенных пользователю)

#### Комментарий (Comment)
- `id` (уникальный идентификатор)
- `description` (текст комментария)
- `author` (автор комментария, связь с сущностью User)
- `task` (задача, к которой относится комментарий, связь с сущностью Task)

## Запуск приложения

### Локальный запуск

1. **Установите зависимости**:
   - Убедитесь, что у вас установлены:
     - Java 21
     - PostgreSQL
     - Maven/Gradle

2. **Настройте базу данных**:
   - Создайте базу данных в PostgreSQL:
     ```sql
     CREATE DATABASE task_management;
     ```
   - Обновите настройки подключения к базе данных в `application.yml`:
     ```yaml
     spring:
       datasource:
         url: jdbc:postgresql://localhost:5432/task_management
         username: your_username
         password: your_password
       jpa:
         hibernate:
           ddl-auto: update
     ```

3. **Запустите приложение**:
   - Используйте Maven:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Или Gradle:
     ```bash
     ./gradlew bootRun
     ```

4. **Проверьте работу приложения**:
   - Откройте браузер и перейдите по адресу: `http://localhost:8080`.
   
### Запуск через Docker Compose

1. **Убедитесь, что Docker установлен**:
   - Установите Docker и Docker Compose, если они ещё не установлены.

2. **Соберите и запустите приложение**:
   - Перейдите в директорию проекта docker.
   - Запустите команду:
     ```bash
     docker-compose up
     ```

3. **Проверьте работу приложения**:
   - Откройте браузер и перейдите по адресу: `http://localhost:8080`.

## API Endpoints

### Задачи (Tasks)

| Метод  | Путь               | Описание                     |
|--------|--------------------|------------------------------|
| GET    | `/api/tasks`       | Получить список всех задач    |
| GET    | `/api/tasks/{id}`  | Получить задачу по ID         |
| POST   | `/api/tasks`       | Создать новую задачу          |
| PUT    | `/api/tasks/{id}`  | Обновить задачу по ID         |
| DELETE | `/api/tasks/{id}`  | Удалить задачу по ID          |

### Пользователи (Users)

| Метод  | Путь               | Описание                     |
|--------|--------------------|------------------------------|
| GET    | `/api/users`       | Получить список всех пользователей |
| GET    | `/api/users/{id}`  | Получить пользователя по ID   |
| POST   | `/api/users`       | Создать нового пользователя   |
| PUT    | `/api/users/{id}`  | Обновить пользователя по ID   |
| DELETE | `/api/users/{id}`  | Удалить пользователя по ID    |

### Комментарии (Comments)

| Метод  | Путь               | Описание                     |
|--------|--------------------|------------------------------|
| GET    | `/api/comments`    | Получить список всех комментариев |
| GET    | `/api/comments/{id}` | Получить комментарий по ID    |
| POST   | `/api/comments`    | Создать новый комментарий     |
| PUT    | `/api/comments/{id}` | Обновить комментарий по ID    |
| DELETE | `/api/comments/{id}` | Удалить комментарий по ID     |
