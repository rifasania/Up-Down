-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Jun 2024 pada 17.51
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tmd_dpbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tscore`
--

CREATE TABLE `tscore` (
  `username` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `up` int(11) NOT NULL,
  `down` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tscore`
--

INSERT INTO `tscore` (`username`, `score`, `up`, `down`) VALUES
('acil', 1799, 9, 13),
('aji', 1282, 7, 9),
('aku', 2563, 15, 17),
('disha', 1782, 9, 13),
('faqih', 408, 2, 4),
('galen', 544, 2, 6),
('gessa', 1020, 5, 10),
('hanan', 998, 3, 9),
('ian', 2040, 14, 16),
('jena', 1156, 6, 11),
('jeryl', 748, 4, 7),
('jeya', 748, 4, 7),
('meyra', 952, 5, 9),
('rafa', 1020, 5, 10),
('sania', 612, 3, 6);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tscore`
--
ALTER TABLE `tscore`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
