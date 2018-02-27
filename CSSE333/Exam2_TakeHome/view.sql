use Pubstaylorz137
go


CREATE VIEW cheap_books
AS 
select TOP 2000000000
	b.pub_name as publisher, a.title, a.au_lname as [author-last], a.au_fname as [author-first], a.price, a.price * a.ytd_sales as [YTD Sales $]
from improved_titleview a, publishers b
where a.price <= 15 and a.pub_id = b.pub_id
order by a.au_lname, a.au_fname
go