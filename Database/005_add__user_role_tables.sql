USE shmoozed;



-- Add Tables

CREATE TABLE User_Role (
	UserRole_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	User_Id int,
	Role_Id int
);

CREATE TABLE Role (
	Role_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	Role varchar(30)
);



-- Foreign key additions

ALTER TABLE User_Role
ADD FOREIGN KEY (User_Id) 
REFERENCES User(User_Id);

ALTER TABLE User_Role
ADD FOREIGN KEY (Role_Id) 
REFERENCES Role(Role_Id);



-- Roles

INSERT INTO Role (Role)
VALUES ('Buyer'),
       ('Seller'),
       ('Admin');
