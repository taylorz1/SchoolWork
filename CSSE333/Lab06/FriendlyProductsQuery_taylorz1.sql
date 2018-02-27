USE NWindtaylorz137
GO

IF object_id(N'dbo.FriendlyProducts', 'V') IS NOT NULL
	DROP VIEW dbo.FriendlyProducts
GO

CREATE VIEW dbo.FriendlyProducts AS
Select Products.ProductID, Products.ProductName, Products.QuantityPerUnit, Products.UnitPrice,
Suppliers.CompanyName, Categories.CategoryName
From Products, Suppliers, Categories
Where Products.SupplierID = Suppliers.SupplierID and Products.Discontinued = 0 and Categories.CategoryID = Products.CategoryID
