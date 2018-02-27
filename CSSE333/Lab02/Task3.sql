Use NWINDTaylorz137
go
Select Products.ProductID, ProductName, Quantity, Products.UnitPrice, Discount, round(Products.UnitPrice - Products.UnitPrice*Discount, 0) as [Actual Cost]
from [Order Details] Join Products on Products.ProductID = [Order Details].ProductID
where Quantity >= 100
order by Quantity, Products.ProductID