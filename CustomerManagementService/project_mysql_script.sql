CREATE DATABASE IF NOT EXISTS cms;
DROP TABLE IF exists cms.customer_info;
DROP TABLE IF exists cms.items;
DROP TABLE IF exists cms.orders;

CREATE TABLE IF NOT EXISTS cms.customer_info(
   customer_id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(100),
   street_address VARCHAR(255),
   city VARCHAR(255),
   state VARCHAR(2),
   zip_code VARCHAR(5),
   email_id VARCHAR(100)
);

INSERT INTO cms.customer_info VALUES(1, 'Hara Chaitanya',  '8888 VININGS VINTAGE WAY', 'KEENE', 'NH', '03446','hara@cms.com');
INSERT INTO cms.customer_info VALUES(2, 'Himaja Ch',  '9999 ACRES MILL ROAD', 'ATLANTA', 'GA', '30080','himaja@gmail.com');
INSERT INTO cms.customer_info VALUES(3, 'Sameeraja B', '2222 ROSWELL GREEN LANE', 'ROSWELL', 'GA', '30081','sameeraja@cms.com');
INSERT INTO cms.customer_info VALUES(4, 'Srija M',  '3232 MABLETON PARKWAY', 'MABLETON', 'GA', '30040','srija@cs.com');
INSERT INTO cms.customer_info VALUES(5, 'Surya Kali', '3892 AUSTELL ROAD', 'NEWYORK', 'NY', '30081','surya@kali.com');
INSERT INTO cms.customer_info VALUES(6, 'Prachi Kriya', '3844 CUMBERLAND PARKWAY', 'SMYRNA', 'GA', '30049','prachi@yahoo.com');

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
   order_date DATE
);

INSERT INTO cms.orders VALUES (1, 1, 1, CURDATE());
INSERT INTO cms.orders VALUES (2, 1, 2, '2020-01-20');
INSERT INTO cms.orders VALUES (3, 2, 3, '2020-02-18');
INSERT INTO cms.orders VALUES (4, 2, 4, '2020-01-30');
INSERT INTO cms.orders VALUES (5, 3, 5, '2020-02-03');
INSERT INTO cms.orders VALUES (6, 3, 6, CURDATE());
INSERT INTO cms.orders VALUES (7, 4, 7, '2020-02-18');
INSERT INTO cms.orders VALUES (8, 4, 8, '2020-01-30');
INSERT INTO cms.orders VALUES (9, 4, 9, '2020-01-20');
INSERT INTO cms.orders VALUES (10, 5, 10, '2020-01-20');
INSERT INTO cms.orders VALUES (11, 5, 11, CURDATE());
INSERT INTO cms.orders VALUES (12, 6, 12, '2020-01-30');
INSERT INTO cms.orders VALUES (13, 6, 13, CURDATE());
