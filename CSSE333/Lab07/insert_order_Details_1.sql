/*
 * Zachary Taylor 4/19/2017
 * this user procedure attempts to insert into the Order Details table.
 * status is 0 if successful
 * status is 1 if OrderID or ProductID is null THROWS ERROR
 * status is 2 if OrderID or ProductID does not exist in the table THROWS ERROR
 * status is 3 if there is not enough quantity in UnitsInStock in the Products table to satisfy the increase/decrease. THROWS ERROR

*/

/****** Object:  StoredProcedure [dbo].[insert_Order Details_1]    Script Date: 4/19/2017 2:46:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create PROCEDURE [dbo].[insert_Order Details_1]
	(@OrderID_1 	[int],
	 @ProductID_2 	[int],
	 @UnitPrice_3 	[money] = NULL,
	 @Quantity_4 	[smallint],
	 @Discount_5 	[real] = 0)
AS 
Declare @status [smallint] -- status variable
Declare @bool [int] -- This is if it exists
-- Check if null
IF @OrderID_1 is null or @ProductID_2 is null
   or @Quantity_4 is null
begin
	raiserror('Null OrderID or ProductID', 14, 1)
	set @status = 1
	return(@status)
end
-- Case statement to determine if something exists
set @bool = 
(SELECT Count(OrderID)
from [Order Details]
where OrderID = @OrderID_1 and ProductID = @ProductID_2)

IF @bool != 0
begin
	set @status = 2
	raiserror('Value already exists in the given table! Please run update!',14, 1)
	return(@status)
end

--Check if we have enough of the stuff to see if we can even validate the order
Declare @enough smallint
set @enough = 
((Select UnitsInStock
 from Products
 where ProductID = @ProductID_2)
	- @Quantity_4)
-- If neg, we know we cannot update so we abort
if @enough < 0
begin
	set @status = 3
	raiserror('Invalid Quantity: Not enough left to satisfy, aborted', 14, 1)
	return(@status)
end

--Update the products table
update [Products]
set [UnitsInStock] = @enough
where ([ProductID] = @ProductID_2)

if @enough < (Select products.ReorderLevel from products where [productid] = @ProductID_2)
begin
	raiserror('Waring: Product stock has dropped below reorder level, please reorder!', 14, 1)
end

-- Fixing unit price default value
if @UnitPrice_3 is null
begin
set @UnitPrice_3 = 
(Select UnitPrice
from Products
where ProductID = @ProductID_2)
end

INSERT INTO [Order Details] 
	 ( [OrderID], [ProductID], [UnitPrice], [Quantity], [Discount]) 
VALUES ( @OrderID_1, @ProductID_2, @UnitPrice_3, @Quantity_4, @Discount_5)
set @status = 0
return(@status)
