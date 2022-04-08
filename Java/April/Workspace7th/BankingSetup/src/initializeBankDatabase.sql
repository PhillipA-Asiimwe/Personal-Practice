-- Create a new table called 'TableName' in schema 'SchemaName'
-- Drop the table if it already exists
IF OBJECT_ID('dbo.Branch', 'U') IS NOT NULL
DROP TABLE dbo.Branch
GO
-- Create the table in the specified schema
CREATE TABLE dbo.Branch
(
    B# [NVARCHAR](3) PRIMARY KEY,
    address [NVARCHAR](64) NOT NULL
);
GO

-- Create a new table called 'TableName' in schema 'SchemaName'
-- Drop the table if it already exists
IF OBJECT_ID('dbo.Customer', 'U') IS NOT NULL
DROP TABLE dbo.Customer
GO
-- Create the table in the specified schema
CREATE TABLE dbo.Customer
(
    C# [NVARCHAR](5) PRIMARY KEY, -- primary key column
    Name [NVARCHAR](50) NOT NULL,
    
);
GO

-- Create a new table called 'TableName' in schema 'SchemaName'
-- Drop the table if it already exists
IF OBJECT_ID('dbo.TableName', 'U') IS NOT NULL
DROP TABLE dbo.Account
GO
-- Create the table in the specified schema
CREATE TABLE dbo.Account
(
    A# [NVARCHAR](7) PRIMARY KEY,
    CU# [NVARCHAR](5) NOT NULL,
    Balance INT NOT NULL,
    FOREIGN KEY (CU#) REFERENCES Customer (C#),
    CHECK(Balance >= 0)
);
GO