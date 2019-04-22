USE shmoozed;



-- Add Table With all of Walmart's Item Info

CREATE TABLE Walmart_Items (
	Item_Id int,
    Walmart_Item_Id varchar(255) PRIMARY KEY NOT NULL,
    Walmart_Name varchar(255),
    Walmart_MSRP decimal(10,2),
	Walmart_Sale_Price decimal(10,2),
    Walmart_UPC varchar(32),
    Walmart_Category_Path varchar(255),
    Walmart_Thumbnail_Image varchar(2083),
    Walmart_Large_Image varchar(2083),
    Walmart_Model_Number varchar(255),
    Walmart_Affiliate_Add_To_Cart_Url varchar(2083),
    Walmart_Stock int
);



-- Foreign key additions

ALTER TABLE Walmart_Items
ADD FOREIGN KEY (Item_Id) 
REFERENCES Item(Item_Id);
