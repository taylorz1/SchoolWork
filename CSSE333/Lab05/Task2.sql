use NWindtaylorz137
go

Create nonclustered index Orders_Customers_link on Orders (CustomerID)
with (Fillfactor = 75);
go

Create nonclustered index Products_SupplierID_link on Products (SupplierID)
with (fillfactor = 100);
go

sp_help Products