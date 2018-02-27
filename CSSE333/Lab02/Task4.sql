Use NWindtaylorz137
go
SET ROWCOUNT 5;
SELECT ProductName,QuantityPerUnit,UnitPrice
FROM Products
Where CategoryID = 4
ORDER BY UnitPrice DESC