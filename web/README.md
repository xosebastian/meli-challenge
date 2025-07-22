# Frontend - MercadoLibre Challenge

## 📋 Descripción

Frontend moderno desarrollado con **Next.js 15**, **TypeScript** y **Tailwind CSS** que se conecta a la API Spring Boot para mostrar detalles de productos de MercadoLibre.

## 🛠️ Stack Tecnológico

- **[Next.js 15](https://nextjs.org/)** - Framework React con App Router
- **[TypeScript](https://www.typescriptlang.org/)** - Tipado estático
- **[Tailwind CSS](https://tailwindcss.com/)** - Framework CSS utility-first
- **[React Query](https://tanstack.com/query)** - Gestión de estado del servidor
- **[Lucide React](https://lucide.dev/)** - Iconos SVG

## 🚀 Inicio Rápido

### Prerrequisitos

- Node.js 18+
- npm o yarn
- Backend API corriendo en puerto 8080

### Instalación

```bash
# Clonar e instalar dependencias
cd web
npm install

# Configurar variables de entorno
cp .env.example .env.local
# Editar .env.local con tus configuraciones

# Iniciar en desarrollo
npm run dev
```

La aplicación estará disponible en **http://localhost:3000**

### Con Docker

```bash
# Desde la raíz del proyecto
docker compose up web -d
```

## 📁 Estructura del Proyecto

```
web/
├── 📁 app/                    # App Router de Next.js 15
│   ├── 📄 layout.tsx         # Layout principal con providers
│   ├── 📄 page.tsx           # Redirect a producto por defecto
│   ├── 📄 globals.css        # Estilos globales con Tailwind
│   └── 📁 products/
│       └── 📁 [id]/
│           └── 📄 page.tsx   # Página de detalle de producto
├── 📁 components/            # Componentes React
│   ├── 📄 ProductPage.tsx   # Componente principal de producto
│   ├── 📄 ProductHeader.tsx # Header de MercadoLibre
│   ├── 📄 ProductPricing.tsx # Componente de precios
│   ├── 📄 ImageGallery.tsx  # Galería de imágenes
│   └── 📁 ui/               # Componentes base con shadcn/ui
├── 📁 hooks/                # Custom React hooks
│   ├── 📄 useProduct.ts     # Hook para obtener productos
│   └── 📄 useImageGallery.ts # Hook para galería de imágenes
├── 📁 lib/                  # Librerías y utilidades
│   ├── 📄 api.ts           # Cliente API para Spring Boot
│   ├── 📄 utils.ts         # Utilidades comunes
│   └── 📄 constants.ts     # Constantes de la aplicación
├── 📁 providers/           # Providers de React
│   └── 📄 QueryProvider.tsx # Provider de React Query
├── 📁 types/               # Definiciones TypeScript
│   └── 📄 product.ts       # Tipos del producto y API
└── 📋 package.json         # Dependencias y scripts
```

## 🔧 Scripts Disponibles

```bash
# Desarrollo
npm run dev          # Servidor de desarrollo con hot reload
npm run build        # Build optimizado para producción
npm run start        # Servidor de producción
npm run lint         # Análisis de código con ESLint
npm run lint:fix     # Corregir errores automáticamente
```

## 🌐 Integración con API

### Configuración

```env
# .env.local
NEXT_PUBLIC_API_URL=http://localhost:8080
NEXT_PUBLIC_APP_NAME="MercadoLibre Challenge"
```

### Endpoints Utilizados

| Método | Endpoint         | Descripción             |
| ------ | ---------------- | ----------------------- |
| `GET`  | `/products/{id}` | Obtener producto por ID |

### Estructura de Respuesta

```typescript
interface Product {
  id: string;
  title: string;
  description: string;
  price: number;
  currency: string;
  stock: number;
  images: string[];
  variants: Variant[];
  paymentOptions: PaymentOptions;
  delivery: Delivery;
  seller: Seller;
  ratings: Ratings;
  features: Feature[];
  reviews: Review[];
  metadata: Metadata;
}
```

## ✅ Funcionalidades Implementadas

### Core Features

- ✅ **Detalle de producto** completo con toda la información de la API
- ✅ **Galería de imágenes** interactiva con navegación
- ✅ **Información del vendedor** con reputación y calificaciones
- ✅ **Precios y métodos de pago** con cuotas
- ✅ **Stock y disponibilidad** en tiempo real
- ✅ **Características del producto** organizadas
- ✅ **Reseñas de usuarios** incluidas en la respuesta de la API
- ✅ **Información de envío** con costos y tiempos

### Arquitectura y Calidad

- ✅ **TypeScript** con tipado estricto
- ✅ **React Query** para manejo de estado del servidor
- ✅ **Components pattern** reutilizables
- ✅ **Custom hooks** para lógica compartida
- ✅ **Error boundaries** y manejo de errores
- ✅ **Loading states** y skeleton screens

### UI/UX

- ✅ **Responsive design** mobile-first
- ✅ **shadcn/ui** components para consistencia
- ✅ **Tailwind CSS** para styling optimizado
- ✅ **Accesibilidad** con ARIA labels
- ✅ **Animaciones** suaves y performantes

## 🏗️ Arquitectura

### Patrón de Componentes

```typescript
// Hook personalizado para lógica
const { data, isLoading, error } = useProduct(id)

// Componente de presentación
<ProductPage productId={id} />
```

### Gestión de Estado

- **React Query**: Para datos del servidor y caché
- **React State**: Para estado local de UI
- **Context API**: Para providers globales

### Patrones Implementados

- **Container/Presenter**: Separación de lógica y presentación
- **Custom Hooks**: Reutilización de lógica
- **Composition**: Componentes compuestos
- **Error Boundaries**: Manejo robusto de errores

## 🎨 Diseño

### Principios de Diseño

- **Mobile-first**: Optimizado para dispositivos móviles
- **Content-first**: El contenido es lo más importante
- **Performance**: Carga rápida y experiencia fluida
- **Accessibility**: Accesible para todos los usuarios

### Colores y Branding

- **Amarillo ML**: `#FFF159` (header)
- **Azul ML**: `#3483FA` (acciones principales)
- **Verde**: Para stock, envío gratis, etc.
- **Grises**: Para texto secundario y bordes

## 📱 Responsive Design

```css
/* Breakpoints de Tailwind */
sm: 640px    /* Tablets pequeñas */
md: 768px    /* Tablets */
lg: 1024px   /* Desktop */
xl: 1280px   /* Desktop grande */
2xl: 1536px  /* Desktop XL */
```

## ⚡ Optimizaciones

### Performance

- **Next.js Image**: Optimización automática de imágenes
- **Code Splitting**: División automática del código
- **Prefetching**: Precarga de rutas críticas
- **React Query**: Caché inteligente de datos

### SEO

- **Metadata**: Meta tags dinámicos por producto
- **Structured Data**: JSON-LD para productos
- **Open Graph**: Para compartir en redes sociales

## 🔧 Desarrollo

### Comandos Útiles

```bash
# Limpiar caché de Next.js
rm -rf .next

# Reinstalar dependencias
rm -rf node_modules package-lock.json && npm install

# Verificar tipos de TypeScript
npx tsc --noEmit

# Analizar bundle
npx @next/bundle-analyzer
```

### Variables de Entorno

```env
# Requeridas
NEXT_PUBLIC_API_URL=http://localhost:8080

# Opcionales
NEXT_PUBLIC_APP_NAME="MercadoLibre Challenge"
NEXT_PUBLIC_APP_VERSION="1.0.0"
NODE_ENV=development
```

## 🐳 Docker

### Dockerfile Incluido

El proyecto incluye un `Dockerfile` optimizado para producción:

```bash
# Build de la imagen
docker build -t meli-frontend .

# Ejecutar contenedor
docker run -p 3000:3000 meli-frontend
```

## 🐛 Solución de Problemas

### Problemas Comunes

#### Error de conexión con API

```bash
# Verificar que el backend esté corriendo
curl http://localhost:8080/products/MLA123456

# Verificar variables de entorno
echo $NEXT_PUBLIC_API_URL
```

#### Problemas de CORS

```javascript
// next.config.ts ya está configurado para manejar CORS en desarrollo
// Para producción, configurar CORS en el backend Spring Boot
```

#### Hot reload no funciona

```bash
# Limpiar caché
rm -rf .next
npm run dev
```

## 📈 Monitoreo

### Herramientas de Análisis

- **React Query Devtools**: Incluido en desarrollo
- **Next.js Analytics**: Para métricas de performance
- **TypeScript**: Para prevenir errores en tiempo de compilación

