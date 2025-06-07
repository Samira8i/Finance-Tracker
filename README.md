```markdown
# FinanceTracker

## Описание
Spring Boot приложение для управления финансами с использованием PostgreSQL и JWT аутентификации.

## Авторы
- [Ишимбаева Самира](https://github.com/Samira8i)
- [Москаленко Дмитрий](https://github.com/kkruchock)
- [Поздняков Илья](https://github.com/a4ee)

## Технологии
- Java 17
- Spring Boot 3.1.0
- PostgreSQL 15
- JWT аутентификация

## Требования
- JDK 17+
- PostgreSQL 12+
- Maven 3.6.3+

## Настройка базы данных
1. Установите PostgreSQL
2. Создайте базу данных:
   ```sql
   CREATE DATABASE finance_tracker;
   ```

## Запуск приложения
1. Установите переменные окружения:
   ```bash
   export POSTGRES_PORT=5432
   export POSTGRES_USERNAME=ваш_пользователь
   export POSTGRES_PASSWORD=ваш_пароль
   export SECRET_KEY=ваш_секретный_ключ
   ```

2. Запустите приложение:
   ```bash
   mvn spring-boot:run
   ```

## Конфигурация (application.properties)
```properties
# Основные настройки
spring.application.name=FinanceTracker

# База данных
spring.datasource.url=jdbc:postgresql://localhost:${POSTGRES_PORT}/finance_tracker
spring.datasource.username=${POSTGRES_USERNAME}
spring.datasource.password=${POSTGRES_PASSWORD}

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# JWT
jwt.secret=${SECRET_KEY}
jwt.expiration=86400

# Security
spring.security.user.name=admin
spring.security.user.password=admin
```

## Доступ
Приложение доступно по адресу: `http://localhost:8080`

## Лицензия
MIT License. Подробнее см. в файле [LICENSE](LICENSE).
```