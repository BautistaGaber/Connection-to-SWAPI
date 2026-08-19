# Connection-to-SWAPI

Backend de una API REST que consume SWAPI (Star Wars API) y expone sus datos mediante una interfaz segura, paginada y filtrable. Desarrollado con Java 21, Spring Boot 4.0.7 y Arquitectura Hexagonal.

**Produccion:** [https://connection-to-swapi.onrender.com/swagger-ui/index.html](https://connection-to-swapi.onrender.com/swagger-ui/index.html)

## Arquitectura

```mermaid
graph TB
    subgraph Presentation["Presentation Layer"]
        AC["AuthController"]
        PC["PeopleController"]
        FC["FilmController"]
        SC["StarshipController"]
        VC["VehicleController"]
        GH["GlobalExceptionHandler"]
        OM["ObjectMapper"]
    end

    subgraph Application["Application Layer"]
        AS["AuthService"]
        PS["PeopleService"]
        FS["FilmService"]
        SS["StarshipService"]
        VS["VehicleService"]
    end

    subgraph Domain["Domain Layer"]
        UM["User"]
        PM["People"]
        FM["Film"]
        SM["Starship"]
        VM["Vehicle"]
        PR["PageResult"]
    end

    subgraph Infrastructure["Infrastructure Layer"]
        subgraph Ports["Output Ports"]
            UPP["UserPersistencePort"]
            JPP["JwtPort"]
            PEP["PasswordEncoderPort"]
            SWP["SwapiPort"]
        end
        subgraph Adapters["Adapters"]
            PUA["UserRepositoryAdapter"]
            JA["JwtAdapter"]
            BA["BCryptEncoderAdapter"]
            SA["SwapiAdapter"]
            SC2["SwapiClient"]
            SM2["SwapiMapper"]
        end
        subgraph Security["Security"]
            JWTF["JwtAuthenticationFilter"]
            SEC["SecurityConfig"]
            SEH["SecurityErrorHandler"]
        end
        subgraph Config["Config"]
            JC["JacksonConfig"]
            WCC["WebClientConfig"]
        end
        subgraph Persistence["Persistence"]
            UE["UserEntity"]
            UR["UserRepository"]
        end
    end

    Client["Client"] -->|HTTP| Presentation
    Presentation --> Application
    Application --> Domain
    Application --> Ports
    Ports --> Adapters
    Adapters --> SWAPI["SWAPI (External)"]
    Adapters --> DB[("PostgreSQL")]
    Security --> Ports
```

## Stack

| Componente | Tecnologia |
|---|---|
| Lenguaje | Java 21 |
| Framework | Spring Boot 4.0.7 |
| Arquitectura | Hexagonal (Ports & Adapters) |
| Seguridad | Spring Security + JWT (jjwt 0.12.6) |
| Base de datos | PostgreSQL 16 |
| Cliente HTTP | Spring WebFlux WebClient |
| API Docs | SpringDoc OpenAPI 3.0.2 |
| Build | Maven |
| Tests | JUnit 5 + Mockito |
| Container | Docker + Docker Compose |

## Requisitos previos

- Java 21+
- Maven 3.9+
- Docker + Docker Compose (opcional)
- PostgreSQL (si no usas Docker)

## Inicio rapido

### Opcion 1: Docker (recomendado)

1. Crear el archivo `.env` en la raiz del proyecto:

```bash
cp .env.example .env
```

2. Levantar la app + PostgreSQL con un solo comando:

```bash
docker compose up --build
```

La API estara disponible en `http://localhost:8080`.

Para detener:

```bash
docker compose down
```

Para detener y eliminar los datos:

```bash
docker compose down -v
```

### Opcion 2: Manual

1. Crear la base de datos en PostgreSQL:

```sql
CREATE DATABASE connection_to_swapi;
```

2. Configurar la variable de entorno del JWT:

```bash
# Linux/macOS
export JWT_SECRET_KEY=tu-clave-secreta-aqui

# Windows
set JWT_SECRET_KEY=tu-clave-secreta-aqui
```

3. Compilar y ejecutar:

```bash
./mvnw spring-boot:run
```

### Verificar que funciona

```bash
curl http://localhost:8080/v3/api-docs
```

## Autenticacion

### Registrar usuario

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"miusuario","password":"mipassword"}'
```

Response `201 Created`:

```json
{
  "id": 1,
  "username": "miusuario"
}
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"miusuario","password":"mipassword"}'
```

Response `200 OK`:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Usar el token

Todos los endpoints de datos requieren el header `Authorization`:

```bash
curl http://localhost:8080/api/people \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

## Endpoints

### Publicos (no requieren token)

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| POST | `/api/auth/register` | Registrar usuario |
| POST | `/api/auth/login` | Login, devuelve JWT |
| GET | `/swagger-ui/**` | Swagger UI |
| GET | `/v3/api-docs/**` | OpenAPI docs |

### Protegidos (requieren JWT)

#### People

| Metodo | Ruta | Parametros | Descripcion |
|--------|------|------------|-------------|
| GET | `/api/people` | `page`, `size`, `name` | Lista paginada, filtro opcional por nombre |
| GET | `/api/people/{id}` | -- | Detalle de una persona por ID |

#### Films

| Metodo | Ruta | Parametros | Descripcion |
|--------|------|------------|-------------|
| GET | `/api/films` | `page`, `size`, `title` | Lista paginada, filtro opcional por titulo |
| GET | `/api/films/{id}` | -- | Detalle de una pelicula por ID |

#### Starships

| Metodo | Ruta | Parametros | Descripcion |
|--------|------|------------|-------------|
| GET | `/api/starships` | `page`, `size`, `name` | Lista paginada, filtro opcional por nombre |
| GET | `/api/starships/{id}` | -- | Detalle de una nave por ID |

#### Vehicles

| Metodo | Ruta | Parametros | Descripcion |
|--------|------|------------|-------------|
| GET | `/api/vehicles` | `page`, `size`, `name` | Lista paginada, filtro opcional por nombre |
| GET | `/api/vehicles/{id}` | -- | Detalle de un vehiculo por ID |

### Parametros de paginacion

| Parametro | Default | Descripcion |
|-----------|---------|-------------|
| `page` | 0 | Numero de pagina (0-indexed) |
| `size` | 10 | Elementos por pagina (1-100) |

### Ejemplos

```bash
# Listar people (paginina 0, 10 resultados)
curl http://localhost:8080/api/people?page=0&size=10 \
  -H "Authorization: Bearer $TOKEN"

# Buscar people por nombre
curl "http://localhost:8080/api/people?name=Luke" \
  -H "Authorization: Bearer $TOKEN"

# Obtener pelicula por ID
curl http://localhost:8080/api/films/1 \
  -H "Authorization: Bearer $TOKEN"

# Listar starships pagina 2
curl "http://localhost:8080/api/starships?page=2&size=5" \
  -H "Authorization: Bearer $TOKEN"
```

## Estructura del proyecto

```
src/main/java/com/bautistagaber/connectiontoswapi/
├── application/          # Logica de negocio
│   ├── command/          # Commandos (RegisterCommand, LoginCommand)
│   ├── exception/        # Excepciones de negocio
│   ├── port/out/         # Puertos de salida (contratos)
│   └── service/          # Servicios de aplicacion
│       └── impl/         # Implementaciones
├── domain/               # Modelos de dominio
│   ├── model/            # People, Film, Starship, Vehicle, PageResult
│   ├── port/out/         # SwapiPort (puerto externo)
│   └── user/             # User, Role
├── infrastructure/       # Implementaciones
│   ├── adapter/
│   │   ├── postgress/    # Adaptador PostgreSQL (UserEntity, UserRepository)
│   │   └── swapi/        # Adaptador SWAPI (client, dto, mapper)
│   ├── config/           # Configuracion (Jackson, WebClient, OpenAPI)
│   └── security/         # JWT, BCrypt, SecurityConfig
└── presentation/         # Capa REST
    ├── controller/       # Controllers (Auth, People, Film, Starship, Vehicle)
    ├── dto/response/     # DTOs de request/response
    ├── exception/        # Manejador global de excepciones
    └── mapper/           # Mappers de dominio a response
```

## Tests

```bash
# Ejecutar todos los tests
./mvnw test

# Ejecutar tests unitarios
./mvnw test -Dtest="*Test"

# Ejecutar un test especifico
./mvnw test -Dtest=AuthServiceImplTest
```

Cobertura de tests:

| Test | Tipo | Cantidad |
|------|------|----------|
| AuthServiceImplTest | Unitario | 5 |
| PeopleServiceImplTest | Unitario | 6 |
| FilmServiceImplTest | Unitario | 5 |
| StarshipServiceImplTest | Unitario | 5 |
| VehicleServiceImplTest | Unitario | 5 |
| SwapiMapperTest | Unitario | 7 |
| AuthControllerTest | Integracion | 4 |
| PeopleControllerTest | Integracion | 5 |
| **Total** | | **43** |

## Configuracion

### Variables de entorno

Las variables de entorno se configuran en un archivo `.env` en la raiz del proyecto. Copia el ejemplo y modificalo:

```bash
cp .env.example .env
```

El archivo `.env` debe quedar asi:

```env
POSTGRES_DB=connection_to_swapi
POSTGRES_USER=postgres
POSTGRES_PASSWORD=admin
JWT_SECRET_KEY=tu-clave-secreta-en-base64-aqui
```

| Variable | Descripcion | Requerida |
|----------|-------------|-----------|
| `POSTGRES_DB` | Nombre de la base de datos | Si |
| `POSTGRES_USER` | Usuario de PostgreSQL | Si |
| `POSTGRES_PASSWORD` | Password de PostgreSQL | Si |
| `JWT_SECRET_KEY` | Clave secreta para firmar JWT (Base64) | Si |

> **Importante:** No commitees el archivo `.env` al repositorio. Ya esta incluido en `.gitignore`.

### application.properties

```properties
spring.application.name=Connection-to-SWAPI
spring.datasource.url=jdbc:postgresql://ep-tiny-thunder-acnjp0ah.sa-east-1.aws.neon.tech/neondb?sslmode=require
spring.datasource.username=postgres
spring.datasource.password=admin
jwt.secret-key=${JWT_SECRET_KEY}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Errores

| Status | Error | Descripcion |
|--------|-------|-------------|
| 400 | Bad Request | Parametros invalidos |
| 401 | Unauthorized | Token JWT invalido o ausente |
| 403 | Forbidden | Sin permisos |
| 404 | Not Found | Recurso no encontrado |
| 409 | Conflict | Username ya existe |
| 502 | Bad Gateway | Error al conectar con SWAPI |
| 500 | Internal Server Error | Error interno del servidor |
