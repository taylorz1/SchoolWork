alter database [Lab3-2_taylorz1]
	Modify file (Name = 'Lab3-2_taylorz1Log',
	maxsize = 30MB)
GO

alter database [Lab3-2_taylorz1]
	Modify file (Name = 'Lab3-2_taylorz1Log',
	size = 10MB)
GO

alter database [Lab3-2_taylorz1]
	Modify file (Name = 'Lab3-2_taylorz1Log',
	filegrowth = 22%)
GO