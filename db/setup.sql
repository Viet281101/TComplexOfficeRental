CREATE DATABASE IF NOT EXISTS TComplexOfficeRental;

USE TComplexOfficeRental;

CREATE TABLE IF NOT EXISTS office_space (
	id INT AUTO_INCREMENT PRIMARY KEY,
	code VARCHAR(20) NOT NULL UNIQUE,
	area DOUBLE NOT NULL,
	status ENUM('Available', 'Under Construction', 'Fully Furnished') NOT NULL,
	floor INT NOT NULL CHECK (floor BETWEEN 1 AND 15),
	type ENUM('Shared Office', 'Private Office') NOT NULL,
	rent_price DOUBLE NOT NULL CHECK (rent_price > 1000000),
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	description TEXT,
	CHECK (area > 20),
	CHECK (DATEDIFF(end_date, start_date) >= 180)
);

SET GLOBAL sql_mode = '';
TRUNCATE TABLE office_space;

ALTER TABLE office_space
MODIFY COLUMN status ENUM('Available', 'Under Construction', 'Fully Furnished') NOT NULL;

ALTER TABLE office_space
MODIFY COLUMN type ENUM('Shared Office', 'Private Office') NOT NULL;


INSERT INTO office_space (code, area, status, floor, type, rent_price, start_date, end_date, description)
VALUES 
	('A01-01-01', 50.0, 'Available', 1, 'Shared Office', 2000000, '2023-01-01', '2023-07-01', 'Prime location, well-lit office space.'),
	('B02-02-02', 75.0, 'Fully Furnished', 2, 'Private Office', 3500000, '2023-02-01', '2023-08-01', 'Fully equipped with furniture and amenities.');


UPDATE office_space
SET status = 'Available', type = 'Shared Office'
WHERE code = 'A01-01-01';

UPDATE office_space
SET status = 'Fully Furnished', type = 'Private Office'
WHERE code = 'B02-02-02';


SELECT CHAR_LENGTH('Available');
SELECT HEX('Available');

SET GLOBAL sql_mode = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

