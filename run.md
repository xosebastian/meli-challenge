# 🚀 MercadoLibre Challenge - Guía de Ejecución Completa

## 📋 Descripción del Proyecto

Este proyecto implementa una aplicación web completa que simula la página de detalle de producto de MercadoLibre, con:

- **Backend**: API REST con Spring Boot (Java)
- **Frontend**: Aplicación web con Next.js (React/TypeScript)
- **Cache**: Redis para optimización de performance
- **Testing**: Tests unitarios y de integración

## 🏗️ Arquitectura del Proyecto

```
meli-challenge/
├── api/                    # Backend Spring Boot
│   ├── src/main/java/     # Código Java
│   ├── src/test/          # Tests del backend
│   └── docker-compose.yml # Configuración Docker
├── web/                   # Frontend Next.js
│   ├── app/              # Páginas Next.js
│   ├── components/       # Componentes React
│   └── lib/             # Utilidades y API client
└── docker-compose.yml    # Orquestación completa
```

## 🚀 Ejecución Rápida (Recomendado)

### Opción 1: Docker Compose Completo

```bash
# Desde la raíz del proyecto
cd meli-challenge

# Levantar todo el stack (Backend + Frontend + Redis)
docker compose up --build

# O en modo detached
docker compose up -d --build
```

**Acceso a la aplicación:**
- 🌐 **Frontend**: http://localhost:3000
- 🔧 **Backend API**: http://localhost:8080
- 📚 **Documentación API**: http://localhost:8080/swagger-ui.html

### Opción 2: Desarrollo Local

#### 1. Backend (API)

```bash
# Terminal 1 - Backend
cd api

# Con Docker (recomendado)
docker compose up api redis --build

# O desarrollo local
./mvnw spring-boot:run
```

#### 2. Frontend

```bash
# Terminal 2 - Frontend
cd web

# Instalar dependencias (solo la primera vez)
npm install

# Ejecutar en modo desarrollo
npm run dev
```

## 📱 Uso de la Aplicación

### 1. Acceder a la aplicación
- Abrir http://localhost:3000 en el navegador

### 2. Ver productos disponibles
- Ir a http://localhost:3000/products/MLA123456
- Este es el producto de ejemplo incluido

### 3. Funcionalidades disponibles
- ✅ Visualización de detalles del producto
- ✅ Información de precios y descuentos
- ✅ Galería de imágenes
- ✅ Información del vendedor
- ✅ Ratings y reviews
- ✅ Metadata del producto
- ✅ Diseño responsive

## 🛠️ Comandos Útiles

### Gestión de Servicios

```bash
# Ver estado de todos los servicios
docker compose ps

# Ver logs en tiempo real
docker compose logs -f

# Parar todos los servicios
docker compose down

# Reiniciar servicios específicos
docker compose restart api
docker compose restart web
```

### Desarrollo Frontend

```bash
cd web

# Ejecutar tests
npm test

# Ejecutar tests con cobertura
npm run test:coverage

# Formatear código
npm run format

# Linting
npm run lint
```

### Desarrollo Backend

```bash
cd api

# Ejecutar tests
./mvnw test

# Tests con cobertura
./mvnw clean test jacoco:report

# Ver reporte de cobertura
open target/site/jacoco/index.html
```

## 🧪 Testing

### Frontend Tests
```bash
cd web
npm test                    # Ejecutar tests
npm run test:watch         # Modo watch
npm run test:coverage      # Con cobertura
```

### Backend Tests
```bash
cd api
./mvnw test               # Todos los tests
./mvnw test -Dtest="*IT"  # Solo tests de integración
```

## 📊 Cobertura de Tests

- **Backend**: >80% cobertura con JaCoCo
- **Frontend**: Tests para API client y utilidades
- **Integración**: Tests end-to-end disponibles

## 🔧 Configuración Avanzada

### Variables de Entorno

#### Frontend (.env.local)
```bash
# En web/.env.local
NEXT_PUBLIC_API_URL=http://localhost:8080
```

#### Backend (application.properties)
```bash
# En api/src/main/resources/application.properties
server.port=8080
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### Puertos Utilizados

| Servicio | Puerto | Descripción |
|----------|--------|-------------|
| Frontend | 3000 | Next.js development server |
| Backend | 8080 | Spring Boot API |
| Redis | 6379 | Cache database |

## 🐛 Solución de Problemas

### Problemas Comunes

#### Puerto ocupado
```bash
# Verificar puertos en uso
sudo lsof -i :3000
sudo lsof -i :8080

# Cambiar puertos si es necesario
# Frontend: cambiar en package.json scripts
# Backend: cambiar en application.properties
```

#### Redis no disponible
```bash
# Verificar Redis
docker compose ps redis
docker compose logs redis

# Reiniciar Redis
docker compose restart redis
```

#### Errores de dependencias
```bash
# Frontend
cd web
rm -rf node_modules package-lock.json
npm install

# Backend
cd api
./mvnw clean install
```

#### Problemas de caché
```bash
# Limpiar caché Redis
docker compose exec redis redis-cli FLUSHALL

# Limpiar caché Next.js
cd web
rm -rf .next
npm run dev
```

### Debug Avanzado

```bash
# Ver logs de todos los servicios
docker compose logs -f

# Acceder a contenedores
docker compose exec api bash
docker compose exec web sh
docker compose exec redis redis-cli

# Verificar conectividad
curl http://localhost:8080/health
curl http://localhost:3000
```

## 📚 Documentación Adicional

- **Backend**: Ver `api/run.md` para detalles específicos del backend
- **Frontend**: Ver `web/README.md` para detalles del frontend
- **API**: http://localhost:8080/swagger-ui.html para documentación interactiva

## ✅ Checklist de Verificación

- [ ] Docker y Docker Compose instalados
- [ ] Puertos 3000, 8080, 6379 disponibles
- [ ] Backend ejecutándose en http://localhost:8080
- [ ] Frontend ejecutándose en http://localhost:3000
- [ ] Redis ejecutándose en puerto 6379
- [ ] Tests pasando en backend y frontend
- [ ] Aplicación accesible en http://localhost:3000/products/MLA123456