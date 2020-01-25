-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 25 Sty 2020, 11:05
-- Wersja serwera: 10.4.10-MariaDB
-- Wersja PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `terminarz`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grupy`
--

CREATE TABLE `grupy` (
  `id_grupa` int(11) NOT NULL,
  `nazwa_grupa` text COLLATE utf8_polish_ci NOT NULL,
  `opis_grupa` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `grupy`
--

INSERT INTO `grupy` (`id_grupa`, `nazwa_grupa`, `opis_grupa`) VALUES
(1, 'szkoła', 'Obowiązki szkolne\r\nKonkursy szkolne\r\nEtc.'),
(2, 'zawody', 'Zawody różne');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przypisania`
--

CREATE TABLE `przypisania` (
  `id_przypis` int(11) NOT NULL,
  `id_grupa` int(11) NOT NULL,
  `id_zadanie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `przypisania`
--

INSERT INTO `przypisania` (`id_przypis`, `id_grupa`, `id_zadanie`) VALUES
(1, 1, 2),
(2, 1, 3),
(3, 2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zadania`
--

CREATE TABLE `zadania` (
  `id_zadanie` int(11) NOT NULL,
  `data_zadanie` date NOT NULL,
  `tytul_zadanie` text COLLATE utf8_polish_ci NOT NULL,
  `opis_zadanie` text COLLATE utf8_polish_ci NOT NULL,
  `priorytet_zadanie` text COLLATE utf8_polish_ci NOT NULL,
  `czy_wykonane` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zadania`
--

INSERT INTO `zadania` (`id_zadanie`, `data_zadanie`, `tytul_zadanie`, `opis_zadanie`, `priorytet_zadanie`, `czy_wykonane`) VALUES
(1, '2020-01-30', 'Turniej szachowy', 'Zamek w Suchej Beskidzkiej\r\n9:00', 'ważne', 0),
(2, '2020-04-15', 'Konkurs SEP na program komputerowy', 'Program komputerowy edukacyjny/użytkowy\r\n\"Terminarz\"', 'ważne', 0),
(3, '2020-01-19', 'Przygotowanie komputerów na egzamin zawodowy', 'ZS im. Walerego Goetla w Suchej Beskidzkiej\r\nKontynuowanie przygotowania komputerów do egzaminu zawodowego', 'ważne', 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `grupy`
--
ALTER TABLE `grupy`
  ADD PRIMARY KEY (`id_grupa`);

--
-- Indeksy dla tabeli `przypisania`
--
ALTER TABLE `przypisania`
  ADD PRIMARY KEY (`id_przypis`);

--
-- Indeksy dla tabeli `zadania`
--
ALTER TABLE `zadania`
  ADD PRIMARY KEY (`id_zadanie`);

--
-- AUTO_INCREMENT dla tabel zrzutów
--

--
-- AUTO_INCREMENT dla tabeli `grupy`
--
ALTER TABLE `grupy`
  MODIFY `id_grupa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `przypisania`
--
ALTER TABLE `przypisania`
  MODIFY `id_przypis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `zadania`
--
ALTER TABLE `zadania`
  MODIFY `id_zadanie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
