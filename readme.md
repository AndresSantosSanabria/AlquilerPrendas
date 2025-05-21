# Sistema de Alquiler de Prendas

Este proyecto implementa un sistema de gestión para un negocio de alquiler de prendas de vestir, permitiendo registrar clientes, empleados, prendas y administrar los servicios de alquiler y lavandería.

## Descripción

El sistema de Alquiler de Prendas es una aplicación Java que utiliza Hibernate para la persistencia de datos en MySQL. La aplicación permite:

- Registrar prendas, clientes y empleados
- Gestionar servicios de alquiler de prendas
- Consultar disponibilidad de prendas por diferentes criterios
- Registrar prendas para envío a servicio de lavandería
- Administrar roles y usuarios del sistema

## Requisitos del Sistema

- Java JDK 11 o superior
- MySQL 8.0 o superior
- Maven 3.6 o superior
- IDE compatible con Java (Eclipse, IntelliJ IDEA, NetBeans, etc.)

## Estructura de Archivos

El proyecto tiene la siguiente estructura de archivos principales:
```
alquilerprendas/
├── .env                       # Archivo de variables de entorno para conexión a BD
├── .gitignore                 # Archivos y directorios ignorados por git
├── pom.xml                    # Configuración de Maven y dependencias
├── src/                       # Código fuente
└── target/                    # Archivos compilados y empaquetados
    ├── classes/               # Clases compiladas
       ├── META-INF/
       │   └── persistence.xml # Configuración de JPA
       └── humanizar/         # Paquetes de la aplicación
```

## Instalación

### Requisitos Previos
1. Asegúrate de tener instalado Java JDK 11 o superior
2. Instala Maven 3.6 o superior
3. Instala MySQL 8.0 o superior
4. Configura tu IDE preferido (Eclipse, IntelliJ IDEA, NetBeans)

### Pasos de Instalación

1. Clonar el repositorio

2. Configurar la base de datos:
   - Inicia MySQL Server
   - Crea una base de datos llamada `alquilerprendas`:
   ```sql
   CREATE DATABASE alquilerprendas;
   ```
   - Ejecuta el script SQL proporcionado para crear las tablas:
   ```bash
   mysql -u tu_usuario -p alquilerprendas < script_bd_alquilerprendas.sql
   ```

3. Configurar el archivo de variables de entorno:
   - Crea un archivo `.env` en la raíz del proyecto con la siguiente estructura:
   ```
   DB_USER=tu_usuario
   DB_PASSWORD=tu_contraseña
   DB_URL=jdbc:mysql://localhost:3306/alquilerprendas
   ```
   - Reemplaza `tu_usuario` y `tu_contraseña` con tus credenciales de MySQL

4. Compilar el proyecto:
```bash
mvn clean install
```

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- `humanizar.alquilerprendas.model`: Contiene las clases de entidad que mapean las tablas de la base de datos
- `humanizar.alquilerprendas.dao`: Implementa el patrón DAO (Data Access Object) para el acceso a datos
- `humanizar.alquilerprendas.dto`: Objetos de transferencia de datos
- `humanizar.alquilerprendas.service`: Contiene la lógica de negocio
- `humanizar.alquilerprendas.controller`: Controladores para la interacción con el usuario
- `humanizar.alquilerprendas.util`: Clases de utilidad, como el gestor de sesiones de Hibernate (HibernateSessionManager)
- `humanizar.alquilerprendas.facade`: Implementa el patrón Facade para simplificar interfaces complejas
- `humanizar.alquilerprendas.view`: Interfaces de usuario (si aplica)

### Configuración de Entorno y Conexión a Base de Datos

El proyecto utiliza un archivo `.env` en la raíz para gestionar las variables de entorno de conexión a la base de datos:

1. Crear el archivo `.env` en la raíz del proyecto con el siguiente formato:
   ```
   DB_USER=tu_usuario_mysql
   DB_PASSWORD=tu_contraseña_mysql
   DB_URL=jdbc:mysql://localhost:3306/alquilerprendas
   ```

2. La clase `HibernateSessionManager` en el paquete `humanizar.alquilerprendas.util` se encarga de cargar estas variables de entorno y configurar la conexión a la base de datos correctamente.

## Funcionalidades Principales

### 1. Registro de Prendas, Clientes y Empleados
- Registra todos los datos necesarios para cada entidad
- Validación de datos obligatorios
- Asignación de roles a usuarios

### 2. Registro de Servicio de Alquiler
- Verificación de cliente registrado previamente
- Validación de empleado existente
- Comprobación de disponibilidad de prendas para la fecha solicitada
- Generación automática de número consecutivo de servicio

### 3. Consultas
- Consulta de servicio de alquiler por número
- Consulta de servicios por cliente
- Consulta de servicios por fecha de alquiler
- Consulta de prendas por talla, separadas por tipo

### 4. Gestión de Lavandería
- Registro de prendas para envío a lavandería
- Asignación de prioridad para las prendas

## Diagrama de Base de Datos

El sistema utiliza una base de datos relacional con las siguientes tablas principales:

- `persona`: Almacena los datos comunes de clientes y empleados
- `cliente`: Extiende la entidad persona con datos específicos del cliente
- `empleado`: Extiende la entidad persona con datos específicos del empleado
- `prenda`: Almacena información de las prendas disponibles para alquiler
- `servicioalquiler`: Registra los servicios de alquiler
- `alquilerprenda`: Tabla de relación entre servicios y prendas
- `serviciolavanderia`: Gestiona el envío de prendas a lavandería

## Uso del Sistema

### Inicio de Sesión

1. Iniciar la aplicación
2. Ingresar email y contraseña
3. El sistema validará el rol del usuario y mostrará las opciones correspondientes

### Registro de Cliente

1. Acceder a la opción "Registrar Cliente"
2. Completar los datos personales (nombre, cédula, dirección, teléfono, email)
3. Establecer contraseña
4. Guardar los datos

### Registro de Prendas

1. Acceder a la opción "Registrar Prenda"
2. Completar los datos de la prenda (tipo, descripción, talla, color, material, precio, etc.)
3. Guardar los datos

### Registro de Servicio de Alquiler

1. Acceder a la opción "Registrar Servicio de Alquiler"
2. Ingresar la identificación del cliente
3. Ingresar la identificación del empleado
4. Seleccionar las prendas a alquilar
5. Establecer la fecha de alquiler
6. El sistema verificará la disponibilidad de las prendas
7. Guardar el servicio

### Consulta de Servicios

1. Acceder a las opciones de consulta según el criterio deseado
2. Ingresar los parámetros de búsqueda
3. Visualizar los resultados

