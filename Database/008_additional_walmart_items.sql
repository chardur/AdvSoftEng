INSERT INTO Item (Item_Name, Item_Quantity)
VALUES ('Coleman RoadTrip LXE Portable Propane Grill', 1),
       ('Crock-Pot 6 Qt', 1),
       ('Oral-B 1000', 1),
       ('Apple iPad 32GB', 1),
       ('Barbie DreamHouse', 1),
       ('Microsoft Xbox One X 1TB', 1),
       ('Hot Wheels Ultimate Garage Tower', 1),
       ('SentrySafe', 1);


INSERT INTO Walmart_Items (Item_Id, Walmart_Item_Id, Walmart_Name, Walmart_MSRP, Walmart_Sale_Price)
VALUES ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'Coleman RoadTrip LXE Portable Propane Grill' LIMIT 1), '43980087', 'Coleman RoadTrip LXE Portable Propane Grill', 0.00, 0.00),
       ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'Crock-Pot 6 Qt' LIMIT 1), '56206232', 'Crock-Pot 6 Qt', 0.00, 0.00),
       ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'Oral-B 1000' LIMIT 1), '14927035', 'Oral-B 1000', 0.00, 0.00),
       ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'Apple iPad 32GB' LIMIT 1), '876555631', 'Apple iPad 32GB', 0.00, 0.00),
       ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'Barbie DreamHouse' LIMIT 1), '109617949', 'Barbie DreamHouse', 0.00, 0.00),
       ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'Microsoft Xbox One X 1TB' LIMIT 1), '276629190', 'Microsoft Xbox One X 1TB', 0.00, 0.00),
       ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'Hot Wheels Ultimate Garage Tower' LIMIT 1), '549728515', 'Hot Wheels Ultimate Garage Tower', 0.00, 0.00),
       ((SELECT Item_Id FROM Item WHERE Item_Name LIKE 'SentrySafe' LIMIT 1), '39603725', 'SentrySafe', 0.00, 0.00);

