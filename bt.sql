-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 19, 2019 lúc 03:44 AM
-- Phiên bản máy phục vụ: 10.1.31-MariaDB
-- Phiên bản PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bt`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `atm`
--

CREATE TABLE `atm` (
  `ID` varchar(30) NOT NULL,
  `Pin` varchar(30) NOT NULL,
  `Ten` varchar(30) NOT NULL,
  `Sotien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `atm`
--

INSERT INTO `atm` (`ID`, `Pin`, `Ten`, `Sotien`) VALUES
('A1', '132', 'Lam', 80),
('A2', '147', 'ALS', 20);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `atm`
--
ALTER TABLE `atm`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
