# Proyecto de Gestión de Precios

Este proyecto es una API REST desarrollada en **Java** utilizando el framework **Spring Boot**. Su objetivo es gestionar y consultar precios aplicables a productos en función de la fecha de consulta, el identificador del producto y el identificador de la marca.

## Tecnologías utilizadas

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.x**: Framework para el desarrollo de aplicaciones basadas en Java.
  - **Spring Web**: Para la creación de la API REST.
  - **Spring Data JPA**: Para la interacción con la base de datos.
- **H2 Database**: Base de datos en memoria utilizada para pruebas.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **JUnit 5**: Framework para pruebas unitarias.
- **MockMvc**: Para pruebas de integración de controladores.
- **Jakarta Servlet**: Para el manejo de excepciones y procesamiento de solicitudes.

## Arquitectura

El proyecto sigue una arquitectura **multicapa** que separa las responsabilidades en diferentes niveles:

1. **Controladores (Controller)**: Gestionan las solicitudes HTTP y delegan la lógica al servicio correspondiente.
2. **Servicios (Service)**: Contienen la lógica de negocio y se encargan de interactuar con los repositorios.
3. **Repositorios (Repository)**: Gestionan la interacción con la base de datos utilizando Spring Data JPA.
4. **Entidades (Entity)**: Representan las tablas de la base de datos en forma de clases Java.

## Patrones de diseño utilizados

- **Repository Pattern**: Para la abstracción de la capa de acceso a datos.
- **DTO (Data Transfer Object)**: Para transferir datos entre las capas de la aplicación.
- **Optional**: Para manejar valores nulos de manera segura en las respuestas del servicio.
- **Builder Pattern**: Utilizado en algunos casos para construir objetos de manera fluida y legible.

## Endpoints

### `GET /api/prices`
Consulta el precio aplicable a un producto en función de la fecha, el identificador del producto y el identificador de la marca.

**Parámetros de entrada:**
- `date` (String): Fecha de consulta en formato `yyyy-MM-dd-HH.mm.ss`.
- `productId` (Long): Identificador del producto.
- `brandId` (Long): Identificador de la marca.

**Ejemplo de respuesta:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "curr": "EUR"
}
```

## Pruebas

El proyecto incluye pruebas unitarias e integrales para garantizar el correcto funcionamiento de la API:

- **Pruebas unitarias**: Validan la lógica de negocio en los servicios.
- **Pruebas de integración**: Verifican el comportamiento de los controladores utilizando `MockMvc`.

Para ejecutar las pruebas, utiliza el siguiente comando:
```bash
mvn clean test
```

## Configuración

### Requisitos previos
- **Java 17** o superior.
- **Maven 3.8** o superior.

### Ejecución
1. Clona el repositorio:
   ```bash
   git clone https://github.com/vecasas/precios.git
   ```
2. Navega al directorio del proyecto:
   ```bash
   cd precios
   ```
3. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

La API estará disponible en `http://localhost:8080/api/prices`.

## Base de datos

El proyecto utiliza una base de datos en memoria **H2** para pruebas. Puedes acceder a la consola de H2 en `http://localhost:8080/h2-console` con las siguientes credenciales:

- **URL JDBC**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: *(vacío)*

## Estructura del proyecto

```
src/
├── main/
│   ├── java/com/inditex/prices/
│   │   ├── controller/    # Controladores REST
│   │   ├── service/       # Lógica de negocio
│   │   ├── repository/    # Acceso a datos
│   │   ├── entity/        # Entidades JPA
│   │   └── dto/           # Objetos de transferencia de datos
│   └── resources/
│       ├── application.yml # Configuración de Spring Boot
│       └── data.sql        # Datos iniciales para pruebas
└── test/
    └── java/com/inditex/prices/ # Pruebas unitarias e integrales
```

## Autor

Desarrollado por **vecasas**. Para más información, visita mi perfil de GitHub: [vecasas](https://github.com/vecasas).

## Licencia

Este proyecto está licenciado bajo la **MIT License**. Consulta el archivo `LICENSE` para más detalles.