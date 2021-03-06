USE master
CREATE DATABASE BookingSys
ON PRIMARY
(
	NAME = BookingData,
	FILENAME = 'C:\PRG 381 Project\SQLData\BookingData.mdf',
	SIZE = 20MB,
	MAXSIZE = 50MB,
	FILEGROWTH = 10%
),
FILEGROUP Secondary
(
	NAME = BookingData2,
	FILENAME = 'C:\PRG 381 Project\SQLData\BookingData2.ndf',
	SIZE = 10MB,
	MAXSIZE = 30MB,
	FILEGROWTH = 20%
)
LOG ON
(
	NAME = BookingLog,
	FILENAME = 'C:\PRG 381 Project\SQLLog\AirlineLog.mdf',
	SIZE = 10MB,
	MAXSIZE = 150MB,
	FILEGROWTH = 10%
)