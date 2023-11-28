-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2023 a las 18:14:39
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `jcvd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuegos`
--

CREATE TABLE `videojuegos` (
  `id` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Genero` varchar(25) DEFAULT NULL,
  `Fechalanzamiento` date DEFAULT NULL,
  `Compania` varchar(50) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `videojuegos`
--

INSERT INTO `videojuegos` (`id`, `Nombre`, `Genero`, `Fechalanzamiento`, `Compania`, `Precio`) VALUES
(1, 'Pokémon Pinball: Rubí y Zafiro', 'Juego de estrategia', '2003-08-01', 'Nintendo', 60.00),
(2, 'Pokémon Ranger: Sombras de Almia', 'Videojuego de aventura', '2008-03-20', 'HAL Laboratory', 39.00),
(3, 'Kirby y la tierra olvidada', 'Videojuego de plataformas', '2022-03-25', 'HAL Laboratory', 49.99),
(4, 'Mario Kart DS', 'Videojuego de carreras', '2005-11-14', 'Nintendo', 33.94),
(5, 'Pro Evolution Soccer 6', 'Fútbol', '2006-04-27', 'Konami', 6.98),
(6, '¡Shin Chan Flipa en Colores!', 'Aventuras ', '2007-11-16', 'Namco Bandai ', 20.00),
(7, 'Wii Sports Resort', 'Videojuego de deportes', '2009-06-25', 'Nintendo', 29.99),
(8, 'Super Mario Galaxy', 'Videojuego de acción', '2007-11-01', 'Nintendo', 42.00),
(9, 'The Legend of Zelda: Majora\'s Mask', 'Videojuego de acción', '2000-04-27', 'Nintendo', 150.00),
(10, 'Pokemon XD', 'Videojuego de aventuras', '2005-08-09', 'Nintendo', 59.95),
(123, 'Mario Slam Basketball', 'Videojuego de deportes', '2006-06-27', 'Nintendo', 39.99);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
