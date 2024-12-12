-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Des 2024 pada 13.38
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tesaplikasi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `history_login`
--

CREATE TABLE `history_login` (
  `id_history_login` varchar(100) NOT NULL,
  `id_user` varchar(100) NOT NULL,
  `token` text NOT NULL,
  `expired_time` varchar(10) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `history_login`
--

INSERT INTO `history_login` (`id_history_login`, `id_user`, `token`, `expired_time`, `created_at`) VALUES
('508a43db-1a0b-40b8-aa5d-6f9aa4b46b1e', '90e31c01-0196-485f-9852-acf47153778e', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJwYXNzd29yZFwiOlwia2hhZGFmaTEyM1wiLFwiZW1haWxcIjpcImtoYWRhZmlAZ21haWwuY29tXCJ9IiwiaWF0IjoxNzMzOTEwMDc0LCJleHAiOjE3MzM5NTMyNzR9.SUKB61TwUIkSETnXLu5lA0pVCTaMPbHc0AV2LiNcag8', '43200000', '2024-12-11'),
('073743a2-18d4-4a42-bb9a-7e5388889532', '90e31c01-0196-485f-9852-acf47153778e', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJwYXNzd29yZFwiOlwia2hhZGFmaTEyM1wiLFwiZW1haWxcIjpcImtoYWRhZmlAZ21haWwuY29tXCJ9IiwiaWF0IjoxNzMzOTc2ODE3LCJleHAiOjE3MzQwMjAwMTd9.tjVq5CNr9Fl7IuKWFjqg7HZtNIcBhie3XgM6hmwXpBg', '43200000', '2024-12-12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `history_transaksi`
--

CREATE TABLE `history_transaksi` (
  `id_history_transaksi` varchar(250) NOT NULL,
  `id_user` varchar(250) NOT NULL,
  `id_ppob_merchant` varchar(250) NOT NULL,
  `invoice_number` varchar(50) NOT NULL,
  `service_code` varchar(50) NOT NULL,
  `service_name` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `transaction_type` varchar(50) NOT NULL,
  `total_amount` int(11) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `history_transaksi`
--

INSERT INTO `history_transaksi` (`id_history_transaksi`, `id_user`, `id_ppob_merchant`, `invoice_number`, `service_code`, `service_name`, `jenis`, `transaction_type`, `total_amount`, `created_at`) VALUES
('b11e963c-3233-4961-ac07-4a8760ea2cdc', '90e31c01-0196-485f-9852-acf47153778e', 'produk1', 'INVThu Dec 12 11:43:46 WIB 2024-01', 'PULSA', 'pulsa', 'P_5000', 'PAYMENT', 6000, '2024-12-12'),
('648b8615-db21-4800-aa32-8c5a48710ed8', '90e31c01-0196-485f-9852-acf47153778e', 'produk1', 'INV12122024-11', 'PULSA', 'pulsa', 'P_5000', 'PAYMENT', 6000, '2024-12-12'),
('8f1179fc-ce05-4eac-baca-89184a8cf1b4', '90e31c01-0196-485f-9852-acf47153778e', 'produk1', 'INV12122024-3', 'PULSA', 'pulsa', 'P_5000', 'PAYMENT', 6000, '2024-12-12'),
('ef60eb23-248d-4b5e-87cb-f6d62e304cca', '90e31c01-0196-485f-9852-acf47153778e', 'produk1', 'INV12122024-4', 'PULSA', 'pulsa', 'P_5000', 'PAYMENT', 6000, '2024-12-12'),
('c271441f-93e9-42ad-a023-dbbc8158b3c4', '90e31c01-0196-485f-9852-acf47153778e', 'produk1', 'INV12122024-5', 'PULSA', 'pulsa', 'P_5000', 'PAYMENT', 6000, '2024-12-12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `ppob_merchant`
--

CREATE TABLE `ppob_merchant` (
  `id_ppob_merchant` varchar(250) NOT NULL,
  `service_code` varchar(100) NOT NULL,
  `service_name` varchar(50) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `created_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `ppob_merchant`
--

INSERT INTO `ppob_merchant` (`id_ppob_merchant`, `service_code`, `service_name`, `jenis`, `price`, `created_at`) VALUES
('produk1', 'PULSA', 'pulsa', 'P_5000', 6000, '2024-12-12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `saldo_user`
--

CREATE TABLE `saldo_user` (
  `id_saldo` varchar(250) NOT NULL,
  `id_user` varchar(250) NOT NULL,
  `nominal_saldo` int(11) NOT NULL,
  `created_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `saldo_user`
--

INSERT INTO `saldo_user` (`id_saldo`, `id_user`, `nominal_saldo`, `created_at`) VALUES
('3adec38d-24e2-4345-ab17-e1b4df4f7a96', '90e31c01-0196-485f-9852-acf47153778e', 494000, '2024-12-12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `user_id` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama_lengkap` varchar(250) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `nama_lengkap`, `created_at`, `updated_at`) VALUES
('90e31c01-0196-485f-9852-acf47153778e', 'khadafi@gmail.com', 'khadafi123', 'Khadafi', '2024-12-11', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
