
SET SQL_SAFE_UPDATES = 0;
DELETE FROM Item_Price_History;

DELETE FROM Walmart_Items;

DELETE FROM Item;
SET SQL_SAFE_UPDATES = 1;

INSERT INTO Item
(Item_Id, Item_Name, Item_Quantity)
VALUES
(1,'Pampers Swaddlers Diapers',25),
(2,'Great Value 18 oz Party Plastic Cups',20),
(3,'Boys Long Sleeve Crew T-Shirt with Rib Cuffs',10),
(4,'(3 Pack) Great Value Cleaning Bleach, 128 fl oz',5),
(5,'Trim Nailcare Fingernail & Toenail Clippers', 189);
 

INSERT INTO Walmart_Items(
Item_Id, 
Walmart_Item_Id, 
Walmart_Name, 
Walmart_MSRP, 
Walmart_Sale_Price, 
Walmart_UPC, 
Walmart_Category_Path,
Walmart_Thumbnail_Image,
Walmart_Large_Image, 
Walmart_Model_Number, 
Walmart_Affiliate_Add_To_Cart_Url, 
Walmart_Stock) 
VALUES
(
1, 
'204175667', 
'Pampers Swaddlers Diapers (Choose Size and Count) Size 3, 136 Count', 
39.94, 
39.94, 
'037000773061', 
'Baby/Diapering/Diapers/Disposable Diapers', 
'https://i5.walmartimages.com/asr/7f6644fc-3409-4b36-ae14-124aa7312358_1.7bcfe40b3af629223698a281fd152626.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF', 
'https://i5.walmartimages.com/asr/7f6644fc-3409-4b36-ae14-124aa7312358_1.7bcfe40b3af629223698a281fd152626.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF', 
'3700077306', 
'http://c.affil.walmart.com/t/api01?l=https%3A%2F%2Fwww.walmart.com%2Fip%2FPampers-Swaddlers-Diapers-Choose-Size-and-Count-Size-3-136-Count%2F204175667%3Faffp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi', 
'Available'
),
(
2,
'122270233',
'Great Value 18 oz Party Plastic Cups, 120 count',
6.98,
6.98,
'078742187082',
'Household Essentials/Paper & Plastic/Disposable Tabletop/Plastic Cups',
'https://i5.walmartimages.com/asr/3bc5a57f-3660-4cb9-b29b-3d4f13a0fa98_1.2cd6cb184b8a8bfafef09f4fc9ba8f87.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
'https://i5.walmartimages.com/asr/3bc5a57f-3660-4cb9-b29b-3d4f13a0fa98_1.2cd6cb184b8a8bfafef09f4fc9ba8f87.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
'C67184',
'http://c.affil.walmart.com/t/api01?l=https%3A%2F%2Fwww.walmart.com%2Fip%2FGreat-Value-18-oz-Party-Plastic-Cups-120-count%2F122270233%3Faffp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi',
'Available'
),
(
3,
'45707349',
'Boys Long Sleeve Crew T-Shirt with Rib Cuffs',
6.49,
6.49,
'885306371227',
'Clothing/Kids Clothing/Boys Clothing/Big Boys Clothing/Big Boys Tops & T-Shirts/Big Boys T-Shirts & Tank Tops',
'https://i5.walmartimages.com/asr/6a58d244-149d-4289-810e-a38cb1809782_1.db9222a98866370ca1692ea6de7ad850.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF',
'https://i5.walmartimages.com/asr/6a58d244-149d-4289-810e-a38cb1809782_1.db9222a98866370ca1692ea6de7ad850.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF',
'CB05Q9F',
'http://c.affil.walmart.com/t/api01?l=https%3A%2F%2Fwww.walmart.com%2Fip%2FBoys-Long-Sleeve-Crew-T-Shirt-with-Rib-Cuffs%2F45707349%3Faffp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi',
'Available'
),
(
4,
'168382713',
'(3 Pack) Great Value Cleaning Bleach, 128 fl oz',
3.96,
3.96,
'078742088327',
'Household Essentials/Laundry/Bleach/Great Value Bleach',
'https://i5.walmartimages.com/asr/e55f9b71-65ec-484d-ace0-7cd8a3503d58_1.8aba39a1f471dfaded729c47f62b3be2.png?odnHeight=100&odnWidth=100&odnBg=ffffff',
'https://i5.walmartimages.com/asr/e55f9b71-65ec-484d-ace0-7cd8a3503d58_1.8aba39a1f471dfaded729c47f62b3be2.png?odnHeight=450&odnWidth=450&odnBg=ffffff',
'',
'http://c.affil.walmart.com/t/api01?l=https%3A%2F%2Fwww.walmart.com%2Fip%2F3-Pack-Great-Value-Cleaning-Bleach-128-fl-oz%2F168382713%3Faffp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi',
'NOT_AVAILABLE'
),
(
5,
'299081844',
'(2 Pack) Trim Nailcare Fingernail & Toenail 09625 Clippers, 2 ct',
2.98,
2.98,
'071603096257',
'Beauty/Makeup/Nails',
'',
'https://i5.walmartimages.com/asr/bb331b76-5693-456f-bce1-57b01189baa3_1.920aa980e78a7a59dccf03deff759c94.jpeg?odnHeight=180&odnWidth=180&odnBg=ffffff',
'',
'http://c.affil.walmart.com/t/api01?l=https%3A%2F%2Fwww.walmart.com%2Fip%2F2-Pack-Trim-Nailcare-Fingernail-Toenail-09625-Clippers-2-ct%2F299081844%3Faffp1%3D5ng3ElK8UpN4FAdJcQys4Bghacg8YfNH0-Huetnv0oo%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi',
'NOT_AVAILABLE'
);


INSERT INTO Item_Price_History
(Item_Price_Id, Item_Id, Price, Date, Last_update_Date)
VALUES
(1, 1, 39.00, '2018-07-01', date),
(2, 1, 41.00, '2018-08-01', date),
(3, 1, 43.00, '2018-09-01', date),
(4, 1, 40.00, '2018-10-01', date),
(5, 1, 39.00, '2018-11-01', date),
(6, 2, 10.00, '2018-06-01', date),
(7, 2, 9.50, '2018-07-01', date),
(8, 2, 9.50, '2018-08-01', date),
(9, 2, 9.25, '2018-09-01', date),
(10, 2, 8.75, '2018-10-01', date),
(11, 2, 8.00, '2018-11-01', date),
(12, 3, 6.50, '2018-09-01', date),
(13, 3, 6.55, '2018-10-01', date),
(14, 3, 6.49, '2018-11-01', date),
(15, 4, 4.00, '2018-08-01', date),
(16, 4, 4.00, '2018-09-01', date),
(17, 4, 4.10, '2018-10-01', date),
(18, 4, 4.00, '2018-11-01', date),
(19, 5, 3.80, '2018-11-01', date),
(20, 5, 3.90, '2018-11-01', date);



