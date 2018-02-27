use NWindtaylorz137
go

-- Note please run each trigger as it's own, otherwise the program will crash.

IF EXISTS (SELECT name FROM sysobjects WHERE name = 'order_detail_post_1' AND type = 'TR')
BEGIN
DROP trigger order_detail_post_1
END
GO

IF EXISTS (SELECT name FROM sysobjects WHERE name = 'order_detail_post_2' AND type = 'TR')
BEGIN
DROP trigger order_detail_post_2
END
GO

IF EXISTS (SELECT name FROM sysobjects WHERE name = 'order_detail_post_3' AND type = 'TR')
BEGIN
DROP trigger order_detail_post_3
END
GO

create trigger order_detail_post_1
on [Order Details] after insert
as
Select * from inserted

create trigger order_detail_post_2
on [Order Details] after insert
as
begin
	declare @quant [smallint]
	declare @prodid [int]
	declare @tempquant [smallint]
	set @quant = (Select quantity
					from inserted)
	set @prodid = (Select productid
					from inserted)
	Update Products
	set UnitsInStock = UnitsInStock - @quant
	where ProductID = @prodid
end

create trigger order_detail_post_3
on [Order Details] instead of insert
as
begin
	declare @quant [smallint]
	declare @prodid [int]
	declare @tempquant [smallint]
	set @quant = (Select quantity
					from inserted)
	set @prodid = (Select productid
					from inserted)
	set @tempquant = (Select UnitsInStock
						from Products
						where ProductID = @prodid)
    if (@tempquant - @quant < 0)
	begin
		raiserror('Insufficient quantity to carry out this order', 14, 1)
		return
	end
begin
	Insert into [Order Details] Select * from inserted
end
	Update Products
	set UnitsInStock = UnitsInStock - @quant
	where ProductID = @prodid
end