# Evidencia 2 - Actividad 8: Módulo Create (CRUD)

Aplicación web desarrollada con Spring Boot que implementa el módulo **Create** de un CRUD completo.

## Tecnologías

- Java 21
- Spring Boot 4
- Spring Data JPA + Hibernate
- Thymeleaf
- Hibernate Validator
- H2 (base de datos en memoria)
- Maven

## Estructura del proyecto

```
src/main/java/com/example/demo/
├── controller/
│   ├── ItemController.java      # Maneja las peticiones HTTP (vistas)
│   └── ItemApiController.java   # API REST (JSON)
├── model/
│   └── Item.java                # Entidad JPA
├── repository/
│   └── ItemRepository.java      # Acceso a base de datos
└── DemoApplication.java

src/main/resources/
├── templates/
│   └── home.html                # Vista principal (Thymeleaf)
└── static/CSS/
    └── style.css
```

## Cómo ejecutar

```bash
./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.

## API REST

| Método | Ruta            | Descripción              |
|--------|-----------------|--------------------------|
| GET    | `/api/items`    | Lista todos los ítems    |
| GET    | `/api/items/{id}` | Obtiene un ítem por ID |
| POST   | `/api/items`    | Crea un nuevo ítem (JSON)|

## Funcionalidades

- Formulario con validación (campo obligatorio, mínimo 2 caracteres)
- Mensaje de confirmación tras guardar
- Listado de todos los registros
- API REST con respuestas JSON
