use NWindtaylorz137
go

Create table UserProfiles(
	LoginID int,
	Firstname FirstName,
	Lastname LastName,
	[State] varchar(31) not null,
	DOB date not null
	Primary KEY (LoginID)
	)