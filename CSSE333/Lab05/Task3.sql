use NWindtaylorz137
go

Select Products.ProductName, Products.UnitPrice
from Products
where UnitPrice > 10
order by UnitPrice desc


Update Products
set Products.UnitPrice = Products.UnitPrice * 1.02