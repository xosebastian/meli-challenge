# 🛒 MercadoLibre Challenge - Detalle de Producto

## 📋 Descripción

Este proyecto implementa una aplicación web completa que simula la página de detalle de producto de MercadoLibre, desarrollada como parte del challenge técnico. La aplicación incluye un backend robusto con Spring Boot y un frontend moderno con Next.js.

## 🏗️ Arquitectura

### Stack Tecnológico

**Backend:**
- **Java 17** con Spring Boot 3
- **Arquitectura hexagonal** (Ports & Adapters)
- **Redis** para caché distribuido
- **Maven** para gestión de dependencias
- **JUnit 5** + **Mockito** para testing

**Frontend:**
- **Next.js 14** con App Router
- **React 18** con TypeScript
- **Tailwind CSS** para estilos
- **React Query** para gestión de estado
- **Jest** + **Testing Library** para testing

**Infraestructura:**
- **Docker** y **Docker Compose** para containerización
- **Redis** para caché
- **Swagger/OpenAPI** para documentación

## 🚀 Inicio Rápido

### Opción 1: Docker Compose (Recomendado)

```bash
# Clonar el repositorio
git clone <repository-url>
cd meli-challenge

# Levantar toda la aplicación
docker compose up --build

# Acceder a la aplicación
open http://localhost:3000/products/MLA123456
```

### Opción 2: Desarrollo Local

```bash
# Backend
cd api
./mvnw spring-boot:run

# Frontend (en otra terminal)
cd web
npm install
npm run dev
```

## 📱 Funcionalidades

### ✅ Implementadas

- **Visualización de productos** con información completa
- **Galería de imágenes** con navegación
- **Información de precios** con descuentos
- **Datos del vendedor** y ratings
- **Metadata del producto** (categoría, marca, etc.)
- **Diseño responsive** para móviles y desktop
- **Caché Redis** para optimización de performance
- **Tests automatizados** en backend y frontend
- **Documentación API** con Swagger

### 🎨 Características de UI/UX

- **Diseño inspirado en MercadoLibre** con colores y tipografía similares
- **Componentes reutilizables** con Tailwind CSS
- **Animaciones suaves** y transiciones
- **Loading states** y manejo de errores
- **SEO optimizado** con metadata dinámica

## 🧪 Testing

### Backend
```bash
cd api
./mvnw test                    # Todos los tests
./mvnw test -Dtest="*IT"       # Tests de integración
./mvnw jacoco:report          # Reporte de cobertura
```

### Frontend
```bash
cd web
npm test                       # Ejecutar tests
npm run test:coverage         # Con cobertura
npm run test:watch           # Modo watch
```

**Cobertura:**
- Backend: >80% con JaCoCo
- Frontend: Tests para API client y utilidades

## 📊 Estructura del Proyecto

```
meli-challenge/
├── api/                          # Backend Spring Boot
│   ├── src/main/java/
│   │   ├── application/         # Casos de uso
│   │   ├── domain/             # Entidades y reglas de negocio
│   │   ├── infrastructure/     # Implementaciones externas
│   │   └── config/            # Configuraciones
│   ├── src/test/              # Tests
│   └── docker-compose.yml     # Configuración Docker
├── web/                        # Frontend Next.js
│   ├── app/                   # Páginas Next.js
│   ├── components/            # Componentes React
│   ├── hooks/                # Custom hooks
│   ├── lib/                  # Utilidades y API client
│   └── types/                # Definiciones TypeScript
├── docker-compose.yml         # Orquestación completa
├── run.md                     # Guía de ejecución detallada
└── README.md                  # Este archivo
```

## 🔧 Configuración

### Variables de Entorno

**Frontend (.env.local):**
```bash
NEXT_PUBLIC_API_URL=http://localhost:8080
```

**Backend (application.properties):**
```properties
server.port=8080
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### Puertos

| Servicio | Puerto | URL |
|----------|--------|-----|
| Frontend | 3000 | http://localhost:3000 |
| Backend | 8080 | http://localhost:8080 |
| Redis | 6379 | localhost:6379 |

## 📚 Documentación

- **Guía de ejecución**: Ver `run.md` para instrucciones detalladas
- **API Documentation**: http://localhost:8080/swagger-ui.html
- **Backend docs**: Ver `api/run.md`
- **Frontend docs**: Ver `web/README.md`

## 🛠️ Comandos Útiles

### Gestión de Servicios
```bash
# Ver estado
docker compose ps

# Logs en tiempo real
docker compose logs -f

# Parar servicios
docker compose down

# Reconstruir
docker compose up --build
```

### Desarrollo
```bash
# Frontend
cd web
npm run dev
npm run build
npm run lint
npm run format

# Backend
cd api
./mvnw spring-boot:run
./mvnw clean package
```

## 🐛 Solución de Problemas

### Problemas Comunes

**Puerto ocupado:**
```bash
sudo lsof -i :3000
sudo lsof -i :8080
```

**Redis no disponible:**
```bash
docker compose logs redis
docker compose restart redis
```

**Errores de dependencias:**
```bash
# Frontend
cd web && rm -rf node_modules package-lock.json && npm install

# Backend
cd api && ./mvnw clean install
```

## 🎯 Próximos Pasos

1. **Explorar la aplicación** en http://localhost:3000/products/MLA123456
2. **Revisar el código** en las carpetas `api/` y `web/`
3. **Ejecutar tests** para verificar funcionamiento
4. **Personalizar** estilos y funcionalidades
5. **Agregar más productos** en `api/src/main/resources/products.json`

## 📄 Licencia

Este proyecto fue desarrollado como parte del challenge técnico de MercadoLibre.

---

**¡Disfruta explorando la aplicación! 🚀**
