# syntax=docker.io/docker/dockerfile:1

FROM node:18-alpine

# Crea el directorio de trabajo
WORKDIR /app

# Instala dependencias del sistema necesarias para paquetes nativos
RUN apk add --no-cache libc6-compat

COPY package.json ./
RUN npm install

# Expone el puerto para desarrollo
EXPOSE 3000

# Define variables de entorno para Next.js
ENV NODE_ENV=development
ENV HOSTNAME=0.0.0.0
ENV PORT=3000

# Comando por defecto: modo desarrollo
CMD ["npm", "run", "dev"]
