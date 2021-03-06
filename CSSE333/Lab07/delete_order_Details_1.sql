/*
 * Zachary Taylor 4/19/2017
 * This user defined procedure allows a user to remove
	an entry from the data table OrderDetails, given
	that that entry exists. It checks if the given
	input is null and then checks if it exists in the
	tables involved. 
 * IF IT DOES NOT EXIST IN THE TABLES IT RETURNS STATUS = 2 AND THROWS AN ERROR
 * IF IT EITHER INPUT IS NULL IT RETURNS STATUS = 1 AND THROWS AN ERROR
 * IF IT SUCCEEDS IT RETURNS STATUS = 0
*/
/****** Object:  StoredProcedure [dbo].[delete_Order Details_1]    Script Date: 4/19/2017 1:40:01 PM ******/

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create  PROCEDURE [dbo].[delete_Order Details_1]
	(@OrderID_1 	[int],
	 @ProductID_2 	[int])
AS
Declare @status [smallint] -- status variable
Declare @bool [int] -- This is if it exists
-- Check if null
IF @OrderID_1 is null or @ProductID_2 is null
begin
	raiserror('Null values are not allowed', 14, 1)
	set @status = 1
	return(@status)
end
-- Case statement to determine if something exists
set @bool = 
(SELECT Count(OrderID)
from [Order Details]
where OrderID = @OrderID_1 and ProductID = @ProductID_2)
IF @bool = 0
begin
	set @status = 2
	raiserror('Value does not exist in the given table, cannot delete',14, 1)
	return(@status)
end
-- I don't know if I need to do this but whatever.
-- Gets the current value of the stock
declare @current [smallint]
set @current = 
(Select UnitsInStock
from Products
where ProductID = @ProductID_2)

-- Updates the relevant stock.
update products
set UnitsInStock = @current + (Select Quantity from [Order Details] where OrderID = @OrderID_1 and ProductID = @ProductID_2)
where ProductID = @ProductID_2;
set @status = 0
--Delete the row with the given OrderID and ProductID in OrderDetails 
DELETE [Order Details] 
WHERE ( [OrderID]	 = @OrderID_1 AND [ProductID]= @ProductID_2)
return(@status)
