# Foro API REST

Una API REST desarrollada con **Spring Boot 3**, **Maven**, y **Flyway** para gestionar un foro con funcionalidades completas de CRUD (Crear, Leer, Actualizar, Eliminar). Este proyecto está diseñado para manejar tópicos dentro de un foro, implementando autenticación básica y asegurando un flujo seguro para las interacciones de los usuarios.

---

## 🚀 **Características principales**

1. **Funcionalidades de CRUD**:
   - Crear, leer, actualizar y eliminar tópicos.
   - Todas las operaciones verifican los endpoints mediante un `PathVariable` con el ID proporcionado.

2. **Autenticación**:
   - El usuario debe iniciar sesión antes de realizar cualquier solicitud.

3. **Base de datos**:
   - Motor de base de datos: **MySQL**.
   - Migraciones gestionadas con **Flyway** para un control robusto del esquema.

4. **Documentación interactiva**:
   - Integración de **Springdoc OpenAPI** para generar una interfaz interactiva y amigable para explorar los endpoints.

5. **Optimización de operatividad**:
   - Validaciones de endpoints para garantizar una experiencia confiable.
   - Gestión eficiente de errores para solicitudes inválidas o IDs inexistentes.

---

## 🛠️ **Requisitos previos**

Antes de comenzar, asegúrate de tener instalado:

- **Java 17**
- **Maven**
- **MySQL**
- **Postman** o herramienta similar para probar la API

---

## 📥 **Instalación**

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/foro-api.git
   cd foro-api
   ```

2. **Configurar la base de datos**:
   - Crear una base de datos en MySQL.
   - Actualizar el archivo `application.properties` con tus credenciales de MySQL:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/foro_db
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=none
     spring.flyway.enabled=true
     spring.flyway.locations=classpath:db/migration
     ```

3. **Compilar y ejecutar el proyecto**:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acceder a la documentación interactiva**:
   - La API genera automáticamente una interfaz Swagger disponible en:
     ```
     http://localhost:8080/swagger-ui.html
     ```

---

## 📌 **Endpoints principales**

| Método | Ruta                      | Descripción                          |
|--------|---------------------------|--------------------------------------|
| POST   | `/login`                  | Iniciar sesión.                      |
| GET    | `/topicos`                | Listar todos los tópicos.            |
| POST   | `/topicos`                | Crear un nuevo tópico.               |
| GET    | `/topicos/{id}`           | Obtener un tópico por su ID.         |
| PUT    | `/topicos/{id}`           | Actualizar un tópico por su ID.      |
| DELETE | `/topicos/{id}`           | Eliminar un tópico por su ID.        |

---

## 🐞 **Problemas comunes y soluciones**

1. **Error de conexión con la base de datos**:
   - Verifica que el servicio de MySQL esté en ejecución.
   - Revisa las credenciales en `application.properties`.

2. **Migraciones no aplicadas**:
   - Asegúrate de que la carpeta `db/migration` contenga los archivos de migración.
   - Verifica los logs de Flyway durante la ejecución.

3. **ID inexistente**:
   - Si intentas acceder o modificar un recurso con un ID inexistente, la API devolverá un error 404.

---

## ✨ **Contribuciones**

¡Las contribuciones son bienvenidas! Si encuentras un problema o tienes una mejora, no dudes en abrir un issue o enviar un pull request.

---

## 👩‍💻 **Autor**

Desarrollado por JGarcia-C.
