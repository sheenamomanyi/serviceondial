-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2019 at 02:37 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `serviceondial_db`
--

CREATE Database IF NOT EXISTS `serviceondial_db`;
-- --------------------------------------------------------

--
-- Table structure for table `serviceproviders_tb`
--

CREATE TABLE `serviceproviders_tb` (
  `id` int(10) NOT NULL,
  `provider_id` varchar(200) NOT NULL,
  `where_located` varchar(100) NOT NULL,
  `phone_no` int(10) NOT NULL,
  `qualification` varchar(200) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `entry_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `serviceproviders_tb`
--

INSERT INTO `serviceproviders_tb` (`id`, `provider_id`, `where_located`, `phone_no`, `qualification`, `username`, `password`, `entry_time`) VALUES
(1, 'Collins \'The Engineer\'', 'Maseno', 712508814, 'Dip. Electrical Engineering plus 3yrs of experience', 'mbwika', 'mypassword', '2019-02-19 20:39:20'),
(2, 'John The Computer Guy', 'Kodele', 745896532, 'diploma in IT', 'johnsmith', 'johnsmith', '2019-02-20 21:16:18'),
(4, '', '', 0, '', 'janedoe', 'janedoe', '2019-03-05 03:31:23'),
(7, 'Hacker', 'Nairobi', 704305238, 'Vac IT', 'Berna', '12345678', '2019-02-25 09:53:19'),
(8, 'Sheena', 'Kisii', 702042697, 'Electrical certificate', 'Sheena', 'sheena', '2019-02-27 10:34:04'),
(9, 'Provider ', 'Location', 794628500, '', 'provider', 'providerpass', '2019-03-05 00:27:37'),
(10, 'CBSkenya', 'Nairobi Westland', 790096780, 'Bachelor Degree in IT', 'Bah', 'Victor', '2019-03-05 00:54:14'),
(12, 'Bbs', 'Kisii', 702042697, 'IT', 'sheena123', 'sheena123', '2019-03-05 00:57:00');

-- --------------------------------------------------------

--
-- Table structure for table `services_tb`
--

CREATE TABLE `services_tb` (
  `id` int(10) NOT NULL,
  `service_type` varchar(50) NOT NULL,
  `provider_id` varchar(100) NOT NULL,
  `charge` varchar(100) NOT NULL,
  `area` varchar(100) NOT NULL,
  `phone_no` bigint(15) NOT NULL,
  `entry_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `services_tb`
--

INSERT INTO `services_tb` (`id`, `service_type`, `provider_id`, `charge`, `area`, `phone_no`, `entry_time`) VALUES
(1, 'Electrical Wiring', 'Collins \'The Engineer\'', '4k per room/unit. Price negotiable.', 'Kisumu county', 712345678, '2019-02-24 21:22:27'),
(2, 'Network Installation', 'John The Computer Guy', '7k for every 10 workstations', 'Mombasa, Kilifi, Kwale County', 714325678, '2019-02-26 10:45:33'),
(3, 'Baby Sitting', 'Mama Wambora', '100 per hr', 'Embakasi', 714325876, '2019-02-24 21:25:14'),
(4, 'Building & Construction', 'Collins \'The Engineer\'', 'Friendly charges', 'Kisumu Area', 712345678, '2019-02-24 21:22:54'),
(5, 'Computer Repair', 'John The Computer Guy', 'Pocket friendly charges', 'Mombasa CBD', 714325678, '2019-02-24 21:25:03'),
(6, 'Motor Mechanic', 'Collins \'The Engineer\'', 'Friendly charges and discounts', 'Kisumu City', 712345678, '2019-02-24 21:26:04'),
(7, 'Plumbing', 'John The Plumber', 'Pocket friendly charges', 'Nairobi CBD', 714325678, '2019-02-24 21:24:58'),
(8, 'Electrical Wiring', 'Collins \'The Engineer\'', '4k per room/unit. Price negotiable.', 'Kisumu county', 712345678, '2019-02-24 21:23:23'),
(9, 'Network Installation', 'John The Computer Guy', '7k for every 10 workstations', 'Mombasa, Kilifi, Kwale County', 714325678, '2019-02-26 10:45:54'),
(10, 'Building & Construction', 'Collins \'The Engineer\'', 'Friendly charges', 'Kisumu Area', 712345678, '2019-02-24 21:23:32'),
(11, 'Computer Repair', 'John The Computer Guy', 'Pocket friendly charges', 'Mombasa CBD', 714325678, '2019-02-24 21:24:49'),
(12, 'Motor Mechanic', 'Collins \'The Engineer\'', 'Friendly charges and discounts', 'Kisumu City', 712345678, '2019-02-24 21:26:17'),
(13, 'Plumbing', 'John The Plumber', 'Pocket friendly charges', 'Nairobi CBD', 714325678, '2019-02-24 21:24:44'),
(14, 'Electrical Wiring', 'Collins \'The Engineer\'', '4k per room/unit. Price negotiable.', 'Kisumu county', 712345678, '2019-02-24 21:23:48'),
(15, 'Network Installation', 'John The Computer Guy', '7k for every 10 workstations', 'Mombasa, Kilifi, Kwale County', 714325678, '2019-02-26 10:46:05'),
(16, 'Building & Construction', 'Collins \'The Engineer\'', 'Friendly charges', 'Kisumu Area', 712345678, '2019-02-24 21:23:52'),
(17, 'Computer Repair', 'John The Computer Guy', 'Pocket friendly charges', 'Mombasa CBD', 714325678, '2019-02-24 21:24:31'),
(18, 'Motor Mechanic', 'Collins \'The Engineer\'', 'Friendly charges and discounts', 'Kisumu City', 712345678, '2019-02-24 21:26:26'),
(19, 'Plumbing', 'John The Plumber', 'Pocket friendly charges', 'Nairobi CBD', 714325678, '2019-02-24 21:24:21'),
(20, 'Electrical Wiring', 'Walter Electricals', 'KSh. 3000/= per room', 'Busia', 724098678, '2019-03-05 00:30:57'),
(25, 'Event DJ', 'Jemo The Mix Master', 'KSh. 15000/=', 'Kiambu', 714369852, '2019-03-05 00:31:15'),
(26, 'Taxi', 'The City Cab', 'Starting from KSh. 500/=', 'Mombasa', 723896523, '2019-03-05 00:31:39'),
(27, 'Laundry', 'Jane', 'Starting from KSh. 300/=', 'Kawangware', 725369852, '2019-03-05 00:33:32'),
(28, 'Computer Repair', 'John The Computer Guy', 'From KSh. 500/=', 'Maseno', 736251478, '2019-03-05 00:22:13'),
(29, 'Plumbing', 'bbs', '200 Per Hour', 'kisii', 702042697, '2019-03-05 01:00:37'),
(30, 'Hhhhjj', '', '500', 'tghhjjk', 0, '2019-03-05 06:27:32'),
(31, 'Software Installation', '', 'Ksh. 500/=', 'Kisumu City', 0, '2019-03-05 06:32:34');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `serviceproviders_tb`
--
ALTER TABLE `serviceproviders_tb`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `provider_id` (`provider_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `services_tb`
--
ALTER TABLE `services_tb`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `serviceproviders_tb`
--
ALTER TABLE `serviceproviders_tb`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `services_tb`
--
ALTER TABLE `services_tb`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
