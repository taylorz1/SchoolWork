/*
 * Zachary Taylor 4/19/2017
 * this user procedure updates order details. It cross checks the update requirements with the product table
   to determine if this is a valid update. Throws errors for invalid entries and checks the validity of the
   parameters
 * status is 0 if successful
 * status is 1 if OrderID or ProductID is null THROWS ERROR
 * status is 2 if OrderID or ProductID does not exist in the table THROWS ERROR
 * status is 3 if there is not enough quantity in UnitsInStock in the Products table to satisfy the increase/decrease. THROWS ERROR

*/

/****** Object:  StoredProcedure [dbo].[update_Order Details_1]    Script Date: 4/19/2017 2:15:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create PROCEDURE [dbo].[update_Order Details_1]
	(@OrderID_1 	[int],
	 @ProductID_2 	[int],
            @NewUnitPrice_3 [money] = NULL,	
            @NewQuantity_4  [smallint]= NULL,
	 @NewDiscount_5 [real] = NULL)
AS
Declare @status [smallint] -- status variable
Declare @bool [int] -- This is if it exists
-- Check if null
IF @OrderID_1 is null or @ProductID_2 is null
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
IF @bool = 0
begin
	set @status = 2
	raiserror('Value does not exist in the given table, cannot update',14, 1)
	return(@status)
end
-- Update Order Detail values
if @NewQuantity_4 is not null
begin
-- Calculating what the UnitsInStock would be post-update.
Declare @enough smallint
set @enough = 
((Select UnitsInStock
 from Products
 where ProductID = @ProductID_2)
 + (Select Quantity
    from [Order Details]
	where ( [OrderID] = @OrderID_1 AND [ProductID] = @ProductID_2))
	- @NewQuantity_4)
-- If neg, we know we cannot update so we abort
if @enough < 0
begin
	set @status = 3
	raiserror('Invalid Quantity: Not enough left to satisfy, aborted', 14, 1)
	return(@status)
end
-- Begin updates
update [Products]
set [UnitsInStock] = @enough
where ([ProductID] = @ProductID_2)

-- Just checking if we should reorder
if @enough < (Select products.ReorderLevel from products where [productid] = @ProductID_2)
begin
	raiserror('Waring: Product stock has dropped below reorder level, please reorder!', 14, 1)
end
-- Update order details
UPDATE [Order Details]
SET [Quantity] = @NewQuantity_4
WHERE	( [OrderID] = @OrderID_1 AND [ProductID] = @ProductID_2)

end

-- Other update values
if @NewUnitPrice_3 is not null
begin
Update [Order Details]
set [UnitPrice] = @NewUnitPrice_3
where ([OrderID] = @OrderID_1 and [ProductId] = @ProductID_2)
end

if @NewDiscount_5 is not null
begin
Update [Order Details]
set [Discount] = @NewDiscount_5
where ([OrderID] = @OrderID_1 and [ProductID] = @ProductID_2)
end

set @status = 0
return(@status)

