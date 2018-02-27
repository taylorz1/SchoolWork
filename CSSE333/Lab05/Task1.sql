use NWindtaylorz137
go

sp_help [Employees]

Select t.name as [table name], i.name as [index name], i.*
from sysobjects as t join sysindexes as i on t.id = i.id
where t.id > 100
group by t.name

Select Count(i.name) as [Indices Count]
From sysobjects AS t JOIN sysindexes AS i ON t.id = i.id
where t.id > 100 and t.name = 'Customers'


Select i.name as [Index Name]
From sysobjects as t join sysindexes as i on t.id = i.id
where t.id > 100 and t.name = 'Suppliers'