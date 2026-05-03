# 🛍️ Clothes Shop Backend

Backend-приложение интернет-магазина одежды, реализованное на **Spring Boot**.  
Проект демонстрирует базовую e-commerce архитектуру: аутентификация, каталог товаров, корзина и заказы.

---

## 🚀 Стек технологий

- Java 21  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Spring Security  
- PostgreSQL  
- Flyway (миграции БД)  
- JWT (аутентификация)  
- Docker / Docker Compose  
- Maven  

---

## 📦 Функционал

### 🔐 Аутентификация
- Регистрация пользователя  
- Логин  
- JWT-аутентификация через `HttpOnly` cookie  

### 👕 Товары
- Получение списка товаров  
- Категории товаров  
- (в процессе) фильтрация / поиск  

### 🛒 Корзина
- Добавление товара в корзину  
- Удаление товара  
- Просмотр корзины  

### 📦 Заказы
- Создание заказа  
- Получение заказов пользователя  

---

## API Notes

- `POST /api/auth/register` - регистрация пользователя
- `POST /api/auth/login` - логин пользователя
- JWT выставляется через `Set-Cookie` в cookie `access_token`
- `GET /api/cart` - получить корзину
- `PUT /api/cart` - изменить количество позиции в корзине
- `PUT /api/cart/{id}?size={SIZE}` - добавить товар в корзину
- `DELETE /api/cart/{id}` - удалить товар из корзины

---

## 🧱 Архитектура
controller → service → repository → database

Дополнительно:
- DTO  
- Mapper  
- Global Exception Handler  

---

## 🐳 Запуск проекта

### 1. Сборка
```bash
./mvnw clean package
```

### 2. Запуск
```bash
docker-compose up --build
```
Приложение будет доступно на:
http://localhost:8080

PostgreSQL будет доступен с хоста на:
`localhost:5433`
---

## 🗄️ База данных

Используется PostgreSQL.  
Миграции выполняются автоматически при старте приложения с помощью Flyway.

---

## ⚙️ Конфигурация

Основные переменные окружения:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

Локальный дефолтный URL приложения:
`jdbc:postgresql://localhost:5433/clothes`

---

## 📌 TODO
  
- [ ] Роли (USER / ADMIN)    
- [ ] Тесты  

---

## 📈 Цель проекта

Разработка backend-сервиса интернет-магазина, максимально приближенного к реальному production-приложению.

Проект создаётся не только как pet-project, но и как основа для:
- последующего деплоя и хостинга;
- интеграции с frontend;
- подключения платёжных систем;
- масштабирования и расширения функциональности.

---

## 👨‍💻 Автор

Александр Железнов  
Junior Java Backend Developer 🚀
