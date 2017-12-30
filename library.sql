-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 30 Décembre 2017 à 15:09
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `library`
--

-- --------------------------------------------------------

--
-- Structure de la table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(70) NOT NULL,
  `lastname` varchar(70) NOT NULL,
  `nativeCountry` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `author`
--

INSERT INTO `author` (`id`, `firstname`, `lastname`, `nativeCountry`) VALUES
(1, 'Victor', 'Hugo', 1),
(2, 'Emile', 'Zola', 1),
(3, 'Sujit', 'Thapa', 2),
(4, 'Georges', 'Sand', 1);

-- --------------------------------------------------------

--
-- Structure de la table `author_book`
--

CREATE TABLE IF NOT EXISTS `author_book` (
  `id_author` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  PRIMARY KEY (`id_author`,`id_book`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `author_book`
--

INSERT INTO `author_book` (`id_author`, `id_book`) VALUES
(1, 2),
(1, 3),
(1, 22),
(1, 34),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 39),
(1, 40),
(1, 41),
(1, 44),
(1, 45),
(1, 51),
(1, 52),
(2, 3),
(2, 7),
(2, 23),
(2, 46),
(2, 47),
(2, 48),
(2, 49),
(3, 0),
(3, 24),
(3, 50),
(4, 27),
(4, 28),
(4, 52),
(4, 53),
(4, 54),
(4, 55),
(5, 25),
(5, 26),
(6, 29),
(7, 30),
(8, 31),
(8, 42),
(8, 43),
(9, 32),
(10, 33);

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `availability` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=56 ;

--
-- Contenu de la table `book`
--

INSERT INTO `book` (`id`, `title`, `price`, `availability`) VALUES
(45, 'Les misérables', '10.00', 1),
(46, 'Germinal', '5.00', 1),
(47, 'La bête humaine', '5.00', 1),
(48, 'Au bonheur des dames', '6.00', 1),
(49, 'La fortune des Rougon', '10.00', 1),
(50, 'bonjour la vie', '2.00', 1),
(51, 'Le dernier jour', '5.00', 1),
(52, 'Les 4 mains', '5.00', 1),
(53, 'La mare au diable', '4.00', 1),
(54, 'Flaubert et moi', '2.00', 1),
(55, 'Chopin et moi', '2.00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `nativecountry`
--

CREATE TABLE IF NOT EXISTS `nativecountry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `nativecountry`
--

INSERT INTO `nativecountry` (`id`, `country`) VALUES
(1, 'France'),
(2, 'Nepal');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(70) NOT NULL,
  `password` char(20) NOT NULL,
  `firstname` varchar(70) NOT NULL,
  `lastname` varchar(70) NOT NULL,
  `email` varchar(90) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `pseudo`, `password`, `firstname`, `lastname`, `email`, `role`) VALUES
(1, 'adrien', '123456', 'adrien', 'cerbelaud', 'adri@hotmail.com', 'ADMIN'),
(2, 'sujit', '12345', 'sujit', 'thapa', 'sujit@hotmail.com', 'USER');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
