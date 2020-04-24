CREATE DATABASE IF NOT EXISTS cms;
DROP TABLE IF exists cms.customer_info;
DROP TABLE IF exists cms.items;
DROP TABLE IF exists cms.orders;

CREATE TABLE IF NOT EXISTS cms.customer_info(
   customer_id INT PRIMARY KEY AUTO_INCREMENT,
   first_name VARCHAR(100),
   last_name VARCHAR(100),
   street_address VARCHAR(255),
   city VARCHAR(255),
   state VARCHAR(2),
   zip_code VARCHAR(5)
);

INSERT INTO cms.customer_info VALUES(1, 'Eric', 'Acevedo', '8888 VININGS VINTAGE WAY', 'ATLANTA', 'GA', '30080');
INSERT INTO cms.customer_info VALUES(2, 'Phuong', 'Nguyen', '9999 ACRES MILL ROAD', 'ATLANTA', 'GA', '30080');
INSERT INTO cms.customer_info VALUES(3, 'Hara', 'Chaitanya', '2222 ROSWELL GREEN LANE', 'ROSWELL', 'GA', '30081');
INSERT INTO cms.customer_info VALUES(4, 'Malik', 'McMullen', '3232 MABLETON PARKWAY', 'MABLETON', 'GA', '30040');
INSERT INTO cms.customer_info VALUES(5, 'Surya', 'Malladi', '3892 AUSTELL ROAD', 'AUSTELL', 'GA', '30081');
INSERT INTO cms.customer_info VALUES(6, 'Prachi', 'Prachi', '3844 CUMBERLAND PARKWAY', 'SMYRNA', 'GA', '30049');

CREATE TABLE IF NOT EXISTS cms.items(
   item_id INT AUTO_INCREMENT PRIMARY KEY,
   item_name VARCHAR(100) NOT NULL,
   price FLOAT NOT NULL
);

INSERT INTO cms.items VALUES (1, 'Baseball', 9.99);
INSERT INTO cms.items VALUES (2, 'Bat', 19.99);
INSERT INTO cms.items VALUES (3, 'Shoes', 199.99);
INSERT INTO cms.items VALUES (4, 'Basketball', 7.99);
INSERT INTO cms.items VALUES (5, 'Frisbee', 2.99);
INSERT INTO cms.items VALUES (6, 'Hat', 5.99);
INSERT INTO cms.items VALUES (7, 'Boomerang', 29.99);
INSERT INTO cms.items VALUES (8, 'Helmet', 19.99);
INSERT INTO cms.items VALUES (9, 'Kangaroo Saddle', 179.99);
INSERT INTO cms.items VALUES (10, 'Budgie Smugglers', 19.99);
INSERT INTO cms.items VALUES (11, 'Swimming Cap', 5.49);
INSERT INTO cms.items VALUES (12, 'Bow', 399.99);
INSERT INTO cms.items VALUES (13, 'Arrows', 69.99);
INSERT INTO cms.items VALUES (14, 'Surfboard', 299.99);
INSERT INTO cms.items VALUES (15, 'Wax', 5.99);
INSERT INTO cms.items VALUES (16, 'Shark Repellent', 15.99);
INSERT INTO cms.items VALUES (17, 'Saddle', 599.99);
INSERT INTO cms.items VALUES (18, 'Riding cap', 79.99);


CREATE TABLE IF NOT EXISTS cms.orders(
   order_id INT AUTO_INCREMENT PRIMARY KEY,
   customer_id VARCHAR(10) NOT NULL,
   item_id INT NOT NULL, 
   order_date DATE,
   pre_ordered_flag VARCHAR(1)
);

INSERT INTO cms.orders VALUES (1, 1, 1, CURDATE(), 'N');
INSERT INTO cms.orders VALUES (2, 1, 2, '2020-01-20', 'Y');
INSERT INTO cms.orders VALUES (3, 2, 3, '2020-02-18', 'N');
INSERT INTO cms.orders VALUES (4, 2, 4, '2020-01-30', 'Y');
INSERT INTO cms.orders VALUES (5, 3, 5, '2020-02-03', 'N');
INSERT INTO cms.orders VALUES (6, 3, 6, CURDATE(), 'Y');
INSERT INTO cms.orders VALUES (7, 4, 7, '2020-02-18', 'N');
INSERT INTO cms.orders VALUES (8, 4, 8, '2020-01-30', 'Y');
INSERT INTO cms.orders VALUES (9, 4, 9, '2020-01-20', 'N');
INSERT INTO cms.orders VALUES (10, 5, 10, '2020-01-20', 'Y');
INSERT INTO cms.orders VALUES (11, 5, 11, CURDATE(), 'N');
INSERT INTO cms.orders VALUES (12, 6, 12, '2020-01-30', 'Y');
INSERT INTO cms.orders VALUES (13, 6, 13, CURDATE(), 'N');
INSERT INTO cms.orders VALUES (14, 7, 1, '2020-01-20', 'Y');
INSERT INTO cms.orders VALUES (15, 7, 2, '2020-02-18', 'N');
INSERT INTO cms.orders VALUES (16, 8, 14, '2020-01-30', 'Y');
INSERT INTO cms.orders VALUES (17, 8, 15, CURDATE(), 'N');
INSERT INTO cms.orders VALUES (18, 8, 16, '2020-01-20', 'Y');
INSERT INTO cms.orders VALUES (19, 9, 17, '2020-02-18', 'N');
INSERT INTO cms.orders VALUES (20, 9, 18, '2020-01-30', 'Y');
INSERT INTO cms.orders VALUES (21, 10, 6, CURDATE(), 'N');
INSERT INTO cms.orders VALUES (22, 10, 14, '2020-01-20', 'Y');