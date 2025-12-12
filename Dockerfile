# Usar imagen base de OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine AS build

# Instalar Maven
RUN apk add --no-cache maven

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargar dependencias (para cachear)
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# ========================================
# Etapa de runtime
# ========================================
FROM eclipse-temurin:21-jre-alpine

# Instalar curl para healthcheck
RUN apk add --no-cache curl

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8083

# Configurar healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Variables de entorno por defecto (se sobrescriben en Render)
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

