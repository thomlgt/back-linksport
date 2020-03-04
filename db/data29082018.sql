-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: linksport
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `followers`
--

LOCK TABLES `followers` WRITE;
/*!40000 ALTER TABLE `followers` DISABLE KEYS */;
/*!40000 ALTER TABLE `followers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'Homme'),(2,'femme');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,'Ligue 1 ',1),(2,'Ligue 2',1),(3,'National',1),(4,'National 2',1),(5,'National 3',1),(6,'Regional 1',1),(7,'Regional 2',1),(8,'Regional 3',1),(9,'Départemental 1',1),(10,'Départemental 2',1),(11,'Départemental 3',1),(12,'Départemental 4',1),(13,'Départemental 5',1),(14,'Pro A',2),(15,'Pro B',2),(16,'National 1',2),(17,'National 2',2),(18,'National 3',2),(19,'Pré-national',2),(20,'Régional 2',2),(21,'Régional 3',2),(22,'Pré-régional',2),(23,'Départemental 2',2),(24,'Départemental 3',2),(25,'Division 1 (Starligue)',3),(26,'Division 2 (Proligue)',3),(27,'National 1',3),(28,'National 2',3),(29,'National 3',3),(30,'Pré-national',3),(31,'Excellence régional',3),(32,'Honneur régional',3),(33,'Pré-régional',3),(34,'Excellence départemental',3),(35,'Honneur départemental',3);
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Gardien',1),(2,'Défenseur Central',1),(3,'Défenseur Gauche',1),(4,'Défenseur Droit',1),(5,'Milieu Défensif',1),(6,'Milieu Relayeur',1),(7,'Milieu Offensif',1),(8,'Milieu Droit',1),(9,'Milieu gauche',1),(10,'Buteur',1),(11,'Attaquant',1),(12,'Attaquant Droit',1),(13,'Attaquant Gauche',1),(14,'Meneur',2),(15,'Arrière',2),(16,'Ailier',2),(17,'Ailier Fort',2),(18,'Pivot',2),(19,'Ailier Gauche',3),(20,'Ailier Droit',3),(21,'Arrière Gauche',3),(22,'Arriére Droit',3),(23,'Demi-centre',3),(24,'Pivot',3),(25,'Gardien',3);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_COACH'),(2,'ROLE_PLAYER'),(4,'ROLE_TEAM');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES (1,'Football'),(2,'Basketball'),(3,'Handball');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2019-08-29 13:27:04','2019-08-29 13:27:04','1900-01-01','1er club de foot sur la métropole liloise','team2@linksport.fr',_binary '\0',NULL,'losc','$2a$10$Bwrinwni67kMJkbvV.KNWe/i9/AUj0x0t9ggm/DHd9JakZ/wpndqG','0645781216','team2',59000,NULL,1,NULL,1),(2,'2019-08-29 13:28:33','2019-08-29 13:28:33','1990-01-01','je suis le vrai pablo','example@linksport.fr',_binary '\0','Pablo','Escobar','$2a$10$QSpA3MIWI/w8vJgSRrOLuOeSsXageBYGnT3hf0OfL6nChlY0yTnzO','0606060606','dealer',59000,1,14,NULL,2),(3,'2019-08-29 13:32:35','2019-08-29 13:32:35','1990-01-01','je suis le vrai pablo','example2@linksport.fr',_binary '\0','Benoit','Evraere','$2a$10$YHc1t5p5DAftME3IfswUSOpk/9ERtdbCfE8eJeGv/4oQP9Yd9mAOa','0606060606','Benoit',59000,1,14,NULL,2),(4,'2019-08-29 13:35:25','2019-08-29 13:35:25','1988-09-15','je suis un grand joueur espagnol','javier@linksport.fr',_binary '\0','Javier','Torres','$2a$10$ELDT.A79T/hZxwKJnEi4RefMPjHnsuC.Iyvaj3N.RmNbVB1cCvEXe','0606060606','javier9',59000,1,9,NULL,1),(5,'2019-08-29 14:45:41','2019-08-29 14:45:41','1997-12-26','Vainqueur de la Coupe de France Régionale de Handball','thomlgt@hotmail.fr',_binary '\0','Thomas','Desaegher','$2a$10$tzEHZQyUcGZmuIykOWzq5OZAZREzhMyZ1yZw..1iFlzSXd.pqX9Te','0676021350','thomlgt',59640,1,28,NULL,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,4);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-29 17:03:12
