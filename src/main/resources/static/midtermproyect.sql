-- CREATE SCHEMA midtermPoyect;
USE midtermPoyect;

-- CREATE SCHEMA midtermPoyect_test;
-- USE midtermPoyect_test;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS student_checking;
DROP TABLE IF EXISTS credit_card;
DROP TABLE IF EXISTS saving;
DROP TABLE IF EXISTS checking;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS third_party;
DROP TABLE IF EXISTS account_holder;
DROP TABLE IF EXISTS user;

CREATE TABLE user(
id BIGINT NOT NULL AUTO_INCREMENT,
username VARCHAR(255),
password VARCHAR(255),
PRIMARY KEY(id)
);

INSERT INTO user(username, password) VALUES
('Lorena Pardo', '$2a$10$pA9dFNVJuQpsKc.mmTO2GeNzPm.d17IvAWGsBlUxHaRO6yFhagpZi'),
('Ruth Telleria', '$2a$10$RF3qixgdrEWAc.SPED/.n.azg2nkjv5bIiPht340LJpUB1aMciUsS'),
('Julen Telleria', '$2a$10$uCrKWTQuzGDkzlnf2Qe94uQ6vj/Zpt.sGTRG5egynj8oZQR6mr4de')
;

CREATE TABLE admin(
id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);
INSERT INTO admin(id) VALUES
(1)
;

CREATE TABLE account_holder(
id BIGINT NOT NULL,
date_birth DATE,
street VARCHAR(255),
post_code INT,
mailing_street VARCHAR(255),
mailing_postal_code INT,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);

INSERT INTO account_holder(id, date_birth, street, post_code) VALUES
(2, '1994-07-08', 'ambasaguas 55', 48891),
(3, '1991-06-30', 'ambasaguas 55', 48891)
;

CREATE TABLE third_party(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
hashed_key VARCHAR(255),
PRIMARY KEY (id)
);
INSERT INTO third_party(id, name, hashed_key) VALUES
(12, 'Aitor Gonzalez', '9876432');

CREATE TABLE role(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
user BIGINT,
PRIMARY KEY(id),
FOREIGN KEY (user) REFERENCES user(id)
);
INSERT INTO role(name, user) VALUES
('ADMIN', 1),
('USER', 1),
('USER', 2),
('USER', 3);

CREATE TABLE account(
id BIGINT NOT NULL AUTO_INCREMENT,
amount DECIMAL,
currency VARCHAR (255),
secret_key VARCHAR(255),
primary_owner_id BIGINT,
secondary_owner_id BIGINT,
penalty_fee_amount DECIMAL,
penalty_fee_currency VARCHAR (255),
creation_date DATE,
status VARCHAR(255),
PRIMARY KEY(id),
FOREIGN KEY(primary_owner_id) REFERENCES user(id),
FOREIGN KEY(secondary_owner_id) REFERENCES user(id)
);


CREATE TABLE student_checking(
id BIGINT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);


CREATE TABLE checking(
id BIGINT NOT NULL,
monthly_maintenance_fee_amount DECIMAL,
monthly_maintenance_fee_currency VARCHAR (255),
minimum_balance_amount DECIMAL,
minimum_balance_currency VARCHAR (255),
last_actualized_date DATE,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);

CREATE TABLE saving(
id BIGINT NOT NULL,
interest_rate DECIMAL,
minimum_balance_amount DECIMAL,
minimum_balance_currency VARCHAR (255),
last_actualized_date DATE,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);


CREATE TABLE credit_card(
id BIGINT NOT NULL,
interest_rate DECIMAL,
credit_limit_amount DECIMAL,
credit_limit_currency VARCHAR (255),
last_actualized_date DATE,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);
