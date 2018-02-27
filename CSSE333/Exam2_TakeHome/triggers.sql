use Pubstaylorz137
go

Create trigger discount_trigger_insert ON discounts
instead of INSERT as
BEGIN
	DECLARE @inputdisc decimal(4,2)
	DECLARE @inputhq int

	SET @inputdisc = (Select discount from inserted);
	SET @inputhq = (Select highqty from inserted);

	IF (@inputdisc > 12 or @inputhq > 1000)
		BEGIN
			raiserror('this is not an allowed insert', 14, 1);
		END
	ELSE
		BEGIN
			insert into discounts
			Select * from inserted
		END
END


go




Create trigger discount_trigger_update ON discounts
instead of UPDATE as
BEGIN
	DECLARE @inputdisc decimal(4,2)
	DECLARE @inputhq int
	DECLARE @inputdiscounttype varchar(40)

	SET @inputdisc = (Select discount from inserted);
	SET @inputhq = (Select highqty from inserted);
	SET @inputdiscounttype = (Select discounttype from inserted);
	IF (@inputdisc > 12 or @inputhq > 1000)
		BEGIN
			raiserror('this is not an allowed update', 14, 1);
		END
	ELSE
		BEGIN
			Update Discounts
			SET highqty = @inputhq,
				discount = @inputdisc
			where Discounts.discounttype = @inputdiscounttype
		END
END