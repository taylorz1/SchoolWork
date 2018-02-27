-- =============================================
-- Create View template
-- =============================================
USE NWindtaylorz137
GO

IF object_id(N'dbo.UKViewQuery', 'V') IS NOT NULL
	DROP VIEW dbo.UKViewQuery
GO

CREATE VIEW dbo.UKViewQuery AS
SELECT OrderID, CustomerID, Shippers.CompanyName, ShipName, ShipCity, ShipCountry 
from Shippers, Orders 
where OrderID >= 10400 and OrderID <= 10600 
		and Shippers.ShipperID = Orders.shipvia
		and ShipCountry = 'UK'