-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: alquilerprendas
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alquilerprenda`
--

DROP TABLE IF EXISTS `alquilerprenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alquilerprenda` (
  `id_servicio` int NOT NULL,
  `id_prenda` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK8wn7iqiwgwoolr9s6hl950561` (`id_prenda`),
  KEY `FKkq6m59aa2k9hk3j4l30586gm3` (`id_servicio`),
  CONSTRAINT `alquilerprenda_ibfk_1` FOREIGN KEY (`id_servicio`) REFERENCES `servicioalquiler` (`id_servicio`),
  CONSTRAINT `FK8wn7iqiwgwoolr9s6hl950561` FOREIGN KEY (`id_prenda`) REFERENCES `prenda` (`id_prenda`),
  CONSTRAINT `fk_alquilerprenda_servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicioalquiler` (`id_servicio`),
  CONSTRAINT `fk_alquilerprenda_servicioalquiler` FOREIGN KEY (`id_servicio`) REFERENCES `servicioalquiler` (`id_servicio`),
  CONSTRAINT `FKkq6m59aa2k9hk3j4l30586gm3` FOREIGN KEY (`id_servicio`) REFERENCES `servicioalquiler` (`id_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alquilerprenda`
--

LOCK TABLES `alquilerprenda` WRITE;
/*!40000 ALTER TABLE `alquilerprenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `alquilerprenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `categoria_prenda` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (14,'Vestido'),(15,'Camisa'),(16,'Pantalón'),(17,'Chaqueta'),(18,'Falda'),(19,'Blusa'),(20,'Short'),(21,'Abrigo'),(22,'Sudadera'),(23,'Traje de baño'),(24,'Overol'),(25,'Camiseta'),(26,'Chaleco');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `estado_cliente` tinyint(1) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  `id_persona` int NOT NULL,
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `fk_cliente_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`),
  CONSTRAINT `FKlbs69o9qkvv7lgn06idak3crb` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'2025-05-19',22);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `id_persona` int NOT NULL AUTO_INCREMENT,
  `salario` double DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `FK3yo5m2sf91t2spkatlwxagm5x` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (23,2500000,'Auxiliar'),(24,4500000,'Supervisor');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `id_genero` int NOT NULL AUTO_INCREMENT,
  `genero_prenda` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_genero`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (12,'Masculino'),(13,'Femenino'),(14,'Unisex'),(15,'Niño'),(16,'Niña'),(17,'Adolescente'),(18,'Maternidad');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id_material` int NOT NULL AUTO_INCREMENT,
  `material_prenda` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_material`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (24,'Algodón'),(25,'Seda'),(26,'Lino'),(27,'Poliéster'),(28,'Cuero'),(29,'Denim'),(30,'Lana'),(31,'Encaje'),(32,'Nylon'),(33,'Terciopelo'),(34,'Franela'),(35,'Rayón'),(36,'Spandex');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id_persona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `contrasenia_hash` varchar(255) NOT NULL,
  `cedula` varchar(11) NOT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (22,'1','1','1','1','1','1'),(23,'Juan Pérez','Cra 10 #20-30','3101234567','juan.perez@example.com','hash1','100200300'),(24,'María Gómez','Cl 45 #12-67','3107654321','maria.gomez@example.com','hash2','200300400');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenda`
--

DROP TABLE IF EXISTS `prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenda` (
  `id_prenda` int NOT NULL AUTO_INCREMENT,
  `tipo_prenda` int DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `talla` int DEFAULT NULL,
  `color` varchar(30) DEFAULT NULL,
  `id_material` int DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  `pedreria` tinyint(1) DEFAULT NULL,
  `id_genero` int DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `para_lavanderia` tinyint(1) DEFAULT NULL,
  `esta_alquilada` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_prenda`),
  KEY `prenda_ibfk_1` (`id_genero`),
  KEY `prenda_ibfk_2` (`id_material`),
  KEY `prenda_ibfk_3` (`tipo_prenda`),
  KEY `prenda_ibfk_4` (`id_categoria`),
  KEY `prenda_ibfk_5` (`talla`),
  CONSTRAINT `prenda_ibfk_1` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id_genero`),
  CONSTRAINT `prenda_ibfk_2` FOREIGN KEY (`id_material`) REFERENCES `material` (`id_material`),
  CONSTRAINT `prenda_ibfk_3` FOREIGN KEY (`tipo_prenda`) REFERENCES `tipo` (`id_tipo`),
  CONSTRAINT `prenda_ibfk_4` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`),
  CONSTRAINT `prenda_ibfk_5` FOREIGN KEY (`talla`) REFERENCES `talla` (`id_talla`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenda`
--

LOCK TABLES `prenda` WRITE;
/*!40000 ALTER TABLE `prenda` DISABLE KEYS */;
INSERT INTO `prenda` VALUES (4,14,'Vestido largo de noche con detalles en pedrería',26,'Negro',25,150,17,1,13,'2025-05-19',0,0),(5,15,'Traje formal de dos piezas',27,'Azul marino',26,200,14,0,12,'2025-05-19',0,0),(6,16,'Camisa casual de manga corta',25,'Blanco',24,45,15,0,14,'2025-05-19',0,0),(7,17,'Pantalón deportivo ligero',26,'Gris',27,60,16,0,14,'2025-05-19',0,0),(8,18,'Falda ejecutiva a la rodilla',25,'Negro',27,75,14,0,13,'2025-05-19',0,0);
/*!40000 ALTER TABLE `prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `nombre_rol` (`nombre_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (3,'Admin'),(1,'Cliente'),(2,'Empleado');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicioalquiler`
--

DROP TABLE IF EXISTS `servicioalquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicioalquiler` (
  `id_servicio` int NOT NULL AUTO_INCREMENT,
  `fecha_devolucion` datetime(6) DEFAULT NULL,
  `fecha_retiro` datetime(6) DEFAULT NULL,
  `fecha_solicitud` datetime(6) DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  `id_empleado` int DEFAULT NULL,
  PRIMARY KEY (`id_servicio`),
  KEY `FKdf2bj6d1ijvoqnreyxqnrx4bn` (`id_cliente`),
  KEY `FKbqwsqcvk43oos3bi7pnf609is` (`id_empleado`),
  CONSTRAINT `FKbqwsqcvk43oos3bi7pnf609is` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_persona`),
  CONSTRAINT `FKdf2bj6d1ijvoqnreyxqnrx4bn` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicioalquiler`
--

LOCK TABLES `servicioalquiler` WRITE;
/*!40000 ALTER TABLE `servicioalquiler` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicioalquiler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviciolavanderia`
--

DROP TABLE IF EXISTS `serviciolavanderia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serviciolavanderia` (
  `id_envio` int NOT NULL AUTO_INCREMENT,
  `id_prenda` int DEFAULT NULL,
  `prioridad` tinyint(1) DEFAULT NULL,
  `fecha_envio` date DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  PRIMARY KEY (`id_envio`),
  KEY `serviciolavanderia_ibfk_1` (`id_prenda`),
  CONSTRAINT `serviciolavanderia_ibfk_1` FOREIGN KEY (`id_prenda`) REFERENCES `prenda` (`id_prenda`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviciolavanderia`
--

LOCK TABLES `serviciolavanderia` WRITE;
/*!40000 ALTER TABLE `serviciolavanderia` DISABLE KEYS */;
INSERT INTO `serviciolavanderia` VALUES (1,8,0,'2025-05-21','2025-05-23'),(2,7,0,'2025-05-21','2025-05-23');
/*!40000 ALTER TABLE `serviciolavanderia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talla`
--

DROP TABLE IF EXISTS `talla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `talla` (
  `id_talla` int NOT NULL AUTO_INCREMENT,
  `talla_prenda` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id_talla`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talla`
--

LOCK TABLES `talla` WRITE;
/*!40000 ALTER TABLE `talla` DISABLE KEYS */;
INSERT INTO `talla` VALUES (24,'S'),(25,'M'),(26,'L'),(27,'XS'),(28,'XL'),(29,'XXL'),(30,'XXXL'),(31,'Única'),(32,'36'),(33,'38'),(34,'40'),(35,'42'),(36,'44');
/*!40000 ALTER TABLE `talla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `tipo_prenda` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (14,'Casual'),(15,'Formal'),(16,'Deportivo'),(17,'Fiesta'),(18,'Boda'),(19,'Trabajo'),(20,'Playa'),(21,'Invierno'),(22,'Verano'),(23,'Urbano'),(24,'Elegante'),(25,'Vintage'),(26,'Tradicional');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `id_rol` int DEFAULT NULL,
  `id_persona` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_n52uvlh33i2o7twdjcf48pui3` (`id_persona`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `FKgwt1471oadallc9rfria02hhw` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`),
  CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`),
  CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (2,1,20),(3,1,22),(4,2,23);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'alquilerprendas'
--

--
-- Dumping routines for database 'alquilerprendas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-21 12:48:55
