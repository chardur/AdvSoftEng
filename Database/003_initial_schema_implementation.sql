
USE shmoozed;


-- This drops the previous temporary item table to allow for changes and new additions.

DROP TABLE item;



-- These add the tables based on our initial schema

CREATE TABLE Item (
	Item_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	Item_Name varchar(255) NOT NULL,
	Item_Quantity int
);


CREATE TABLE Buyer_Items (
	Buyer_Item_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	Item_Id int,
	Price decimal(10,2),
	User_Id int
);

CREATE TABLE Alerts (
	Alert_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	Buyer_Item_Id int,
	Seller_Item_Id int,	
	Last_Update_Date timestamp,
	User_Id int
);


CREATE TABLE User (
	User_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	User_First_Name varchar(50),
	User_Last_Name varchar(50),
	User_Email varchar(50),
	User_Password varchar(50),
	User_Username varchar(50)
);


CREATE TABLE Item_Price_History (
	Item_Price_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	Item_Id int,
	Price decimal(10,2),
	Date timestamp,
	Last_update_Date timestamp
);


CREATE TABLE Seller_Items (
	Seller_Item_Id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	Item_Id int,
	Price decimal(10,2),
	User_Id int
);



-- Foreign key additions

ALTER TABLE Alerts
ADD FOREIGN KEY (Buyer_Item_Id) 
REFERENCES Buyer_Items(Buyer_Item_Id);

ALTER TABLE Alerts
ADD FOREIGN KEY (Seller_Item_Id) 
REFERENCES Seller_Items(Seller_Item_Id);

ALTER TABLE Alerts
ADD FOREIGN KEY (User_Id) 
REFERENCES User(User_Id);

ALTER TABLE Item_Price_History
ADD FOREIGN KEY (Item_Id) 
REFERENCES Item(Item_Id);

ALTER TABLE Seller_Items
ADD FOREIGN KEY (Item_Id) 
REFERENCES Item(Item_Id);

ALTER TABLE Seller_Items
ADD FOREIGN KEY (User_Id) 
REFERENCES User(User_Id);

ALTER TABLE Buyer_Items
ADD FOREIGN KEY (Item_Id) 
REFERENCES Item(Item_Id);

ALTER TABLE Buyer_Items
ADD FOREIGN KEY (User_Id) 
REFERENCES User(User_Id);

