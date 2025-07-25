CREATE DATABASE  IF NOT EXISTS `db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK91fcfd99kg1af67rjv08heu1q` (`nome`),
  CONSTRAINT `cargo_chk_1` CHECK (((`nome` >= 3) and (`nome` <= 200)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_usuario`
--

DROP TABLE IF EXISTS `cargo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo_usuario` (
  `usuario_id` bigint NOT NULL,
  `cargo_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`cargo_id`),
  KEY `FKe669ha112wxflq61riow1am1h` (`cargo_id`),
  CONSTRAINT `FKayg825sj3mdrue66govnipinl` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKe669ha112wxflq61riow1am1h` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_usuario`
--

LOCK TABLES `cargo_usuario` WRITE;
/*!40000 ALTER TABLE `cargo_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarefa`
--

DROP TABLE IF EXISTS `tarefa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarefa` (
  `id` bigint NOT NULL,
  `vencimento` datetime(6) NOT NULL,
  `descricao` varchar(8000) NOT NULL,
  `status` tinyint NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh1rhpa56mvm9tur1y66hfrvhn` (`usuario_id`),
  CONSTRAINT `FKh1rhpa56mvm9tur1y66hfrvhn` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarefa`
--

LOCK TABLES `tarefa` WRITE;
/*!40000 ALTER TABLE `tarefa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarefa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarefa_seq`
--

DROP TABLE IF EXISTS `tarefa_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarefa_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarefa_seq`
--

LOCK TABLES `tarefa_seq` WRITE;
/*!40000 ALTER TABLE `tarefa_seq` DISABLE KEYS */;
INSERT INTO `tarefa_seq` VALUES (1);
/*!40000 ALTER TABLE `tarefa_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `hash` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'rode@email.com','$2a$10$35BccWQ.9lYCKueLQkAA.etImvbnivy42McSUud5Jb55hjdrjz8VO'),(2,'teste@teste.teste','$2a$10$K.94LdY.VFTJxikxh30VHupMB2y7NIEmCotm1KqeBSrH./F/bXokS'),(3,'teste2@teste.teste','$2a$10$rU2SCpl9PYI9U5Wnuqey2ejm.3p89mWCFwSCDRhLf3Q4vU98PUqy6');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-24 12:22:28
