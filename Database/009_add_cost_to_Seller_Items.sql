-- Add column for cost of item to seller
Use shmoozed;
ALTER TABLE Seller_Items
ADD Seller_Cost decimal(10,2);