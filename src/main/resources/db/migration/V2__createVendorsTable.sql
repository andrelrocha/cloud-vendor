CREATE TABLE vendors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    street VARCHAR(100),
    complement VARCHAR(100),
    city VARCHAR(100),
    state CHAR(2)
);