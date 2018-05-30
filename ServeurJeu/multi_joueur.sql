-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 29 mai 2018 à 18:26
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `multi_joueur`
--
CREATE DATABASE IF NOT EXISTS `multi_joueur` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `multi_joueur`;

-- --------------------------------------------------------

--
-- Structure de la table `capaciter`
--

DROP TABLE IF EXISTS `capaciter`;
CREATE TABLE IF NOT EXISTS `capaciter` (
  `capacite` int(11) NOT NULL AUTO_INCREMENT,
  `nombreVie` int(11) NOT NULL,
  `scrore` int(11) NOT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`capacite`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personnage`
--

DROP TABLE IF EXISTS `personnage`;
CREATE TABLE IF NOT EXISTS `personnage` (
  `pseudo` varchar(50) NOT NULL,
  `profil` varchar(50) NOT NULL,
  `capacite` int(11) NOT NULL,
  PRIMARY KEY (`pseudo`),
  KEY `profil` (`profil`),
  KEY `capacite` (`capacite`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

DROP TABLE IF EXISTS `profil`;
CREATE TABLE IF NOT EXISTS `profil` (
  `profil` int(11) NOT NULL AUTO_INCREMENT,
  `resistance` int(11) NOT NULL,
  `typeAttaque` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`profil`),
  KEY `typeAttaque` (`typeAttaque`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `typeattaque`
--

DROP TABLE IF EXISTS `typeattaque`;
CREATE TABLE IF NOT EXISTS `typeattaque` (
  `typeAttaque` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`typeAttaque`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;
--
--
-- Contraintes pour la table `personnage`
--
ALTER TABLE `personnage`
  ADD CONSTRAINT `FK_association1` FOREIGN KEY (`profil`) REFERENCES `profil` (`profil`);
--
-- Contraintes pour la table `personnage`
--
ALTER TABLE `personnage`
  ADD CONSTRAINT `FK_association2` FOREIGN KEY (`capacite`) REFERENCES `capaciter` (`capacite`);
--
-- Contraintes pour la table `profil`
--
ALTER TABLE `profil`
  ADD CONSTRAINT `FK_association3` FOREIGN KEY (`typeAttaque`) REFERENCES `typeattaque` (`typeAttaque`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
