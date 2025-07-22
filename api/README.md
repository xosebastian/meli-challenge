# 🚀 Meli Challenge - Backend API

Backend REST API desarrollado en **Spring Boot 3.3.0** para el desafío técnico de MercadoLibre. Implementa una arquitectura hexagonal (Clean Architecture) con caché Redis para optimizar el rendimiento.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Arquitectura](#-arquitectura)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Uso](#-uso)
- [API Endpoints](#-api-endpoints)
- [Documentación](#-documentación)
- [Testing](#-testing)
- [Docker](#-docker)
- [Estructura del Proyecto](#-estructura-del-proyecto)

## ✨ Características

- **Arquitectura Hexagonal**: Separación clara de responsabilidades
- **Caché Redis**: Optimización de rendimiento con cache distribuido
- **Documentación OpenAPI**: Swagger UI integrado
- **Health Checks**: Monitoreo de salud de la aplicación
- **Validación de Datos**: Validación robusta de entrada
- **Manejo de Errores**: Gestión centralizada de excepciones
- **CORS Configurado**: Soporte para frontend React/Next.js
- **Logging Avanzado**: Trazabilidad completa de operaciones

## 🛠 Tecnologías

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 17 | Lenguaje principal |
| **Spring Boot** | 3.3.0 | Framework principal |
| **Spring Data Redis** | 3.3.0 | Caché distribuido |
| **SpringDoc OpenAPI** | 2.2.0 | Documentación API |
| **Lombok** | Latest | Reducción de boilerplate |
| **Maven** | Latest | Gestión de dependencias |
| **Redis** | 7.2-alpine | Base de datos en memoria |
| **Docker** | Latest | Containerización |

## 🏗 Arquitectura

```
┌─────────────────────────────────────────────────────────────┐
│                    Infrastructure Layer                     │
├─────────────────────────────────────────────────────────────┤
│  Controllers │  Repositories │  Exception Handlers │  DTOs │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                    Application Layer                       │
├─────────────────────────────────────────────────────────────┤
│                    Services                                │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                      Domain Layer                          │
├─────────────────────────────────────────────────────────────┤
│  Models │  Repositories (Interfaces) │  Business Logic   │
└─────────────────────────────────────────────────────────────┘
```

### Principios de Diseño

- **Clean Architecture**: Separación de capas
- **SOLID Principles**: Principios de diseño orientado a objetos
- **Dependency Injection**: Inyección de dependencias
- **Repository Pattern**: Abstracción de acceso a datos
- **DTO Pattern**: Transferencia de datos optimizada

## 🚀 Instalación

### Prerrequisitos

- **Java 17** o superior
- **Maven 3.6+**
- **Redis 7.2+** (opcional para desarrollo local)

### Instalación Local

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/xosebastian/meli-challenge.git
   cd meli-challenge/api
   ```

2. **Instalar dependencias**
   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

### Con Docker

```bash
# Construir imagen
docker build -t meli-api .

# Ejecutar contenedor
docker run -p 8080:8080 meli-api
```

### Con Docker Compose (Recomendado)

```bash
# Desde la raíz del proyecto
docker-compose up -d
```

## ⚙️ Configuración

### Variables de Entorno

| Variable | Valor por Defecto | Descripción |
|----------|-------------------|-------------|
| `SPRING_PROFILES_ACTIVE` | `default` | Perfil de Spring |
| `SERVER_PORT` | `8080` | Puerto del servidor |
| `SPRING_DATA_REDIS_HOST` | `localhost` | Host de Redis |
| `SPRING_DATA_REDIS_PORT` | `6379` | Puerto de Redis |

### Perfiles de Spring

- **default**: Configuración local
- **docker**: Configuración para contenedores

## 📖 Uso

### Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/products/{id}` | Obtener producto por ID |
| `GET` | `/actuator/health` | Health check |
| `GET` | `/swagger-ui.html` | Documentación API |

### Ejemplo de Uso

```bash
# Obtener producto
curl -X GET "http://localhost:8080/products/MLA123456"

# Health check
curl -X GET "http://localhost:8080/actuator/health"

# Documentación API
open http://localhost:8080/swagger-ui.html
```

## 🔌 API Endpoints

### GET /products/{id}

Obtiene información detallada de un producto por su ID de MercadoLibre.

**Parámetros:**
- `id` (path): ID del producto (ej: MLA123456)

**Respuesta Exitosa (200):**
```json
{
  "id": "MLA123456",
  "title": "Celular Samsung Galaxy A54",
  "description": "Pantalla Super AMOLED de 6.4'', 128 GB de almacenamiento",
  "price": 189999.99,
  "currency": "ARS",
  "stock": 15,
  "images": [
    "https://example.com/img1.jpg",
    "https://example.com/img2.jpg"
  ],
  "variants": [
    {
      "label": "Color",
      "options": ["Negro", "Blanco"]
    }
  ],
  "paymentOptions": {
    "methods": ["Tarjeta de crédito", "Transferencia bancaria", "Efectivo"],
    "installments": "6x $31.666 sin interés"
  },
  "delivery": {
    "type": "express",
    "estimatedDate": "2024-06-15",
    "cost": 0
  },
  "seller": {
    "name": "TechStore",
    "rating": 4.8,
    "reviews": 1024,
    "reputation": "gold"
  },
  "ratings": {
    "average": 4.5,
    "count": 235
  },
  "features": [
    {
      "name": "Pantalla",
      "value": "6.4'' Super AMOLED"
    }
  ],
  "reviews": [
    {
      "user": "Juan Pérez",
      "comment": "Excelente calidad de imagen",
      "rating": 5
    }
  ],
  "metadata": {
    "seoTitle": "Samsung Galaxy A54 al mejor precio | TechStore",
    "seoDescription": "Comprá el Samsung Galaxy A54 con pantalla Super AMOLED, 128 GB y 15 unidades disponibles. ¡Aprovechá esta oferta!",
    "category": "Celulares",
    "brand": "Samsung",
    "availability": "in_stock"
  }
}
```

**Códigos de Error:**
- `400`: ID de producto inválido
- `404`: Producto no encontrado
- `500`: Error interno del servidor

## 📚 Documentación

### Swagger UI
Accede a la documentación interactiva de la API:
```
http://localhost:8080/swagger-ui.html
```

### OpenAPI JSON
Especificación OpenAPI en formato JSON:
```
http://localhost:8080/api-docs
```

## 🧪 Testing

### Ejecutar Tests

```bash
# Todos los tests
mvn test

# Tests con coverage
mvn test jacoco:report

# Tests específicos
mvn test -Dtest=ProductControllerTest
```

### Cobertura de Tests

- **ProductControllerTest**: Tests de endpoints REST
- **ProductServiceTest**: Tests de lógica de negocio
- **JsonProductRepositoryTest**: Tests de repositorio
- **GlobalExceptionHandlerTest**: Tests de manejo de errores

## 🐳 Docker

### Construir Imagen

```bash
docker build -t meli-api .
```

### Ejecutar Contenedor

```bash
docker run -p 8080:8080 meli-api
```

### Docker Compose

```yaml
services:
  api:
    build: ./api
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - SPRING_DATA_REDIS_HOST=redis
    depends_on:
      redis:
        condition: service_healthy
```

## 📁 Estructura del Proyecto

```
api/
├── src/
│   ├── main/
│   │   ├── java/com/meli/challenge/
│   │   │   ├── application/
│   │   │   │   └── service/
│   │   │   │       └── ProductService.java
│   │   │   ├── config/
│   │   │   │   ├── OpenApiConfig.java
│   │   │   │   └── RedisConfig.java
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   └── Product.java
│   │   │   │   └── repository/
│   │   │   │       └── ProductRepository.java
│   │   │   └── infrastructure/
│   │   │       ├── adapter/
│   │   │       │   ├── input/rest/
│   │   │       │   │   ├── dto/
│   │   │       │   │   ├── mapper/
│   │   │       │   │   └── ProductController.java
│   │   │       │   └── output/json/
│   │   │       │       └── JsonProductRepository.java
│   │   │       └── exception/
│   │   │           ├── dto/
│   │   │           ├── GlobalExceptionHandler.java
│   │   │           └── ProductNotFoundException.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-docker.properties
│   │       └── products.json
│   └── test/
│       └── java/com/meli/challenge/
│           ├── application/service/
│           ├── infrastructure/adapter/input/rest/
│           ├── infrastructure/adapter/output/json/
│           └── infrastructure/exception/
├── Dockerfile
├── pom.xml
└── README.md
```

### Descripción de Capas

- **Application**: Servicios de aplicación y lógica de negocio
- **Domain**: Modelos de dominio y interfaces de repositorio
- **Infrastructure**: Implementaciones concretas (REST, JSON, Redis)
- **Config**: Configuraciones de Spring Boot
- **Exception**: Manejo centralizado de errores

## 🔧 Configuración Avanzada

### Redis

```properties
# Configuración de Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.timeout=2000ms
spring.cache.redis.time-to-live=3600000
```

### Logging

```properties
# Configuración de logging
logging.level.com.meli.challenge=INFO
logging.level.org.springframework.web=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```

### Performance

```properties
# Configuración de performance
server.tomcat.max-threads=200
server.tomcat.min-spare-threads=10
server.compression.enabled=true
spring.jpa.open-in-view=false
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

**Sebastian Cardozo**
- GitHub: [@xosebastian](https://github.com/xosebastian)

---

⭐ **¡No olvides dar una estrella al proyecto si te fue útil!** 