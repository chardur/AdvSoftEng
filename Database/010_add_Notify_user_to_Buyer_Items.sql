-- Add column for notifying user
Use shmoozed;
ALTER TABLE Buyer_Items
ADD Notify_user bit;
