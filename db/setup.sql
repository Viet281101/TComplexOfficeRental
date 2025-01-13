
CREATE DATABASE IF NOT EXISTS TComplexOfficeRental;

USE TComplexOfficeRental;

CREATE TABLE IF NOT EXISTS office_space (
	id INT AUTO_INCREMENT PRIMARY KEY,
	code VARCHAR(10) NOT NULL UNIQUE,
	area DECIMAL(10, 2) NOT NULL,
	status ENUM('Trống', 'Hạ tầng', 'Đầy đủ') NOT NULL,
	floor INT NOT NULL,
	type ENUM('Văn phòng chia sẻ', 'Văn phòng trọn gói') NOT NULL,
	rent_price DECIMAL(15, 2) NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	description TEXT
);

INSERT INTO office_space (code, area, status, floor, type, rent_price, start_date, end_date, description)
VALUES
('MB001', 100.00, 'Trống', 2, 'Văn phòng chia sẻ', 2000000.00, '2020-11-20', '2021-11-25', 'Mặt bằng tầng 2'),
('MB002', 240.00, 'Trống', 5, 'Văn phòng trọn gói', 5000000.00, '2020-04-03', '2020-10-03', 'Mặt bằng tầng 5');

ALTER TABLE office_space
ADD CONSTRAINT chk_dates CHECK (start_date < end_date);
