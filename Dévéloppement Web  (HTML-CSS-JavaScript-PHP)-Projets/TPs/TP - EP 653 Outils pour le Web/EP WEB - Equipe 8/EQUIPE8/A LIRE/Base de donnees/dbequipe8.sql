-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 02, 2014 at 01:37 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dbequipe8`
--
CREATE DATABASE IF NOT EXISTS `dbequipe8` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dbequipe8`;

-- --------------------------------------------------------

--
-- Table structure for table `catalogue`
--

CREATE TABLE IF NOT EXISTS `catalogue` (
  `ID` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `Marque` varchar(20) NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `Famille` varchar(20) NOT NULL,
  `Dimension` float NOT NULL,
  `Poids` float NOT NULL,
  `Infotech` varchar(100) NOT NULL,
  `Prix` int(10) NOT NULL,
  `imagepath` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `catalogue`
--

INSERT INTO `catalogue` (`ID`, `Marque`, `Nom`, `Famille`, `Dimension`, `Poids`, `Infotech`, `Prix`, `imagepath`) VALUES
(1, 'Acer', 'Acer Aspire 5732z', 'Ordinateur Portable', 4565460, 15646, 'CPU:44Ghz RAM:2Gb\r\nOS:Windows 7 Display:10.1 in\r\n1280Ã—720 TFT LCD \r\nStorage:1TB\r\nBattery:3 cell 24 ', 45549, 'images/Acer Aspire 5732z.jpg'),
(2, 'Sony', 'XBOX one1TB', 'XBOX One', 125, 122, '12212', 4542, 'images/XBOX one1TB.jpg'),
(3, 'Sony', 'PS4', 'Playstation 4', 51651, 35, '3515131', 400, 'images/PS4.jpg'),
(4, 'LDLC', 'LDLC 41X', 'Ordinateur de Bureau', 54, 11, '16565651', 1542, 'images/LDLC 41X.jpg'),
(5, 'Sony', 'Sony 3D', 'TV et Ecran', 51, 51651, '5165', 14462, 'images/Sony 3D.jpg'),
(6, 'Motorola', 'GPS4x', 'GPS', 515, 51551, '165', 1516, 'images/GPS4x.jpg'),
(7, 'CELL', 'CELL78', 'Telephonie', 5465, 2111, '15145111451451WE \r\n516516ARE\r\n5165\r\n51d MORE DE RIRE\r\n6541654641656565\r\n5126 LOL', 465, 'images/CELL78.jpg'),
(8, 'Samcron', 'Samscron 5D', 'Appareil Photo', 4245, 5454, '5454', 4554, 'images/Samscron 5D.jpg'),
(9, 'Microsoft', 'MS Office 2007', 'Logiciels', 4, 21, 'Evolution de la suite bureautique de Microsoft', 100, 'images/MS OFFFICE 2007.jpg'),
(10, 'Toshiba', 'Toshiba 65x', 'Ordinateur Portable', 35135, 531312, 'CPU:44Ghz RAM:2Gb\r\nOS:Windows 7 Display:10 in\r\n1280Ãƒâ€”720 TFT LCD \r\nStorage:1TB\r\nBattery:3 cell 24', 4213, 'images/Toshiba 65x.png');

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `PID` int(4) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(20) NOT NULL,
  `quantite` int(4) NOT NULL,
  `Prix` varchar(4) NOT NULL,
  `command` varchar(15) NOT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `IDuser` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`IDuser`),
  UNIQUE KEY `ID` (`IDuser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`IDuser`, `username`, `password`) VALUES
(1, 'admin', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
