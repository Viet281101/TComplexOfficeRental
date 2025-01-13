CREATE DATABASE IF NOT EXISTS TComplexOfficeRental;
USE TComplexOfficeRental;

CREATE TABLE IF NOT EXISTS OfficeSpace (
	id VARCHAR(10) PRIMARY KEY,
	area INT NOT NULL,
	status ENUM('Trống', 'Hạ tầng', 'Đầy đủ') NOT NULL,
	floor INT NOT NULL CHECK (floor BETWEEN 1 AND 15),
	type ENUM('Văn phòng chia sẻ', 'Văn phòng trọn gói') NOT NULL,
	rent_price DECIMAL(10,2) NOT NULL CHECK (rent_price > 1000000),
	start_date DATE NOT NULL,
	end_date DATE NOT NULL CHECK (end_date > start_date + INTERVAL 6 MONTH)
);
