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

CREATE TABLE admin(
id BIGINT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE account_holder(
id BIGINT NOT NULL AUTO_INCREMENT,
date_birth DATE,
street VARCHAR(255),
post_code INT,
mailing_street VARCHAR(255),
mailing_postal_code INT,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE third_party(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
hashed_key VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE role(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
user BIGINT,
PRIMARY KEY(id),
FOREIGN KEY (user) REFERENCES user(id)
);


CREATE TABLE account(
id BIGINT NOT NULL AUTO_INCREMENT,
balance DECIMAL,
secret_key VARCHAR(255),
primary_owner_id BIGINT,
secondary_owner_id BIGINT,
penalty_fee DECIMAL,
creation_date DATE,
status VARCHAR(255),
PRIMARY KEY(id),
FOREIGN KEY(primary_owner_id) REFERENCES user(id),
FOREIGN KEY(secondary_owner_id) REFERENCES user(id)
);

CREATE TABLE student_checking(
id BIGINT NOT NULL AUTO_INCREMENT,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);

CREATE TABLE checking(
id BIGINT NOT NULL AUTO_INCREMENT,
monthly_maintenance_fee DECIMAL,
minimum_balance DECIMAL,
last_actualized_date DATE,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);

CREATE TABLE saving(
id BIGINT NOT NULL AUTO_INCREMENT,
interest_rate DECIMAL,
minimum_balance DECIMAL,
last_actualized_date DATE,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);

CREATE TABLE credit_card(
id BIGINT NOT NULL AUTO_INCREMENT,
interest_rate DECIMAL,
credit_limit DECIMAL,
last_actualized_date DATE,
PRIMARY KEY(id),
FOREIGN KEY(id) REFERENCES account(id)
);


