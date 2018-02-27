USE [NWindtaylorz137]
GO

/****** Object:  Table [dbo].[UserProfiles]    Script Date: 3/31/2017 3:11:52 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[UserProfiles](
	[LoginID] [int] NOT NULL,
	[Firstname] [dbo].[FirstName] NOT NULL,
	[Lastname] [dbo].[LastName] NOT NULL,
	[State] [varchar](31) NOT NULL,
	[DOB] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LoginID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[UserProfiles]  WITH CHECK ADD  CONSTRAINT [FK_UserProfiles_Login] FOREIGN KEY([LoginID])
REFERENCES [dbo].[Login] ([LoginID])
ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[UserProfiles] CHECK CONSTRAINT [FK_UserProfiles_Login]
GO

ALTER TABLE [dbo].[UserProfiles]  WITH CHECK ADD  CONSTRAINT [CK_UserProfiles] CHECK  (([DOB]>'1900-01-01' AND [DOB]<getdate()))
GO

ALTER TABLE [dbo].[UserProfiles] CHECK CONSTRAINT [CK_UserProfiles]
GO

ALTER TABLE [dbo].[UserProfiles]  WITH CHECK ADD  CONSTRAINT [CK_UserProfiles_1] CHECK  (([State]='CA' OR [State]='KY' OR [State]='MO' OR [State]='IL' OR [State]='IA' OR [State]='IN'))
GO

ALTER TABLE [dbo].[UserProfiles] CHECK CONSTRAINT [CK_UserProfiles_1]
GO

