Use Bank 
drop table Branch;
drop table Account;
drop table Customer;

Create table Branch (
    B# char(3) Primary key,
    Address Varchar(64) Unique Not Null);

Create table Customer(
    C# char(5) Primary Key,
    Name Varchar(64) Unique Not Null);

Create table Account(
    A# Char(7) Primary Key,
    CU# Char(5) Not Null,
    Balance Integer Not Null,
    Foreign Key (CU#) References Customer (C#),
    Check(Balance >= 0));