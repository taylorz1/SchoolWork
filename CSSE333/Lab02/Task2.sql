Use NWindtaylorz137
go
Select top 8 ProductID, ProductName, UnitsInStock, UnitPrice
from products
where UnitsInStock < 20
order by UnitsInStock desc, ProductID