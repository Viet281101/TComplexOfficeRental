
CREATE DATABASE IF NOT EXISTS TComplexOfficeRental;

USE TComplexOfficeRental;

CREATE TABLE IF NOT EXISTS office_space (
	id INT AUTO_INCREMENT PRIMARY KEY,
	code VARCHAR(10) NOT NULL UNIQUE,
	area DECIMAL(10, 2) NOT NULL,
	status ENUM('Vacant', 'Infrastructure', 'Fully Furnished') NOT NULL,
	floor INT NOT NULL,
	type ENUM('Shared Office', 'Private Office') NOT NULL,
	rent_price DECIMAL(15, 2) NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	description TEXT,
	CONSTRAINT chk_dates CHECK (start_date < end_date)
);

INSERT IGNORE INTO office_space (code, area, status, floor, type, rent_price, start_date, end_date, description)
VALUES
('MB001', 100.00, 'Vacant', 2, 'Shared Office', 2000000.00, '2020-11-20', '2021-11-25', 'Mặt bằng tầng 2'),
('MB002', 240.00, 'Vacant', 5, 'Private Office', 5000000.00, '2020-04-03', '2020-10-03', 'Mặt bằng tầng 5');

SHOW VARIABLES LIKE 'character_set%';

SELECT * FROM office_space;

