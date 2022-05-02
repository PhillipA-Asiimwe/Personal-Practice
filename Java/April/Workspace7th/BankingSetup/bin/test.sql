CREATE or ALTER PACKAGE BANK AS
     FUNCTION to_cunumbr(@num IN number) RETURN Customer.C#%type;
     FUNCTION newCustomer(name IN Customer.name%type) RETURN VARCHAR;
     Procedure chekcust(cname IN Customer.Name%type,bull OUT BOOLEAN);
     Procedure h8stcust(higest OUT Customer.C#%type);
     FUNCTION rmv_customer(name IN Customer.Name%type) Return VARCHAR;
     ---BRANCH---
     Procedure ibranch(bn IN number ,addy IN char,newb out Branch.B#%type);
     FUNCTION branch_fun(addy1 IN Branch.Address%type) RETURN Branch.B#%type;
     FUNCTION open_branch(addy2 IN Branch.Address%type) RETURN VARCHAR;
     Procedure close_branch(addy IN Branch.Address%type);
     ---ACCOUNT---
     PROCEDURE getCnum(name IN customer.name%type,cnumb Out customer.C#%type);
     PROCEDURE getBnum(name IN Branch.address%type,cnumb Out Branch.B#%type);
     FUNCTION to_Anum(brn Branch.B#%type, anum number) RETURN Account.A#%type;
     PROCEDURE h8staccount(cusnum IN Account.CU#%type,branchnum IN Branch.B#%type,highest OUT Account.A#%type);
     FUNCTION open_acc(cust IN customer.name%type, addy IN Branch.Address%type, ammount IN number) RETURN varchar;
     PROCEDURE close_acc(accnum IN Account.A#%type);
     PROCEDURE withdraw(amunt IN Account.A#%type,ammount IN number);
     PROCEDURE deposit(amunt IN Account.A#%type,ammount IN number);
     PROCEDURE actransfer(acun1t IN Account.A#%type,acun2t IN Account.A#%type,amount IN number);
     FUNCTION close_E_acc  RETURN VARCHAR;
     Function deposits(name IN Customer.name%type, Location IN Branch.Address%type,Ammount In Account.Balance%type) RETURN NUMBER;
     FUNCTION transferr(name1 IN Customer.name%type,Location1 IN Branch.Address%type,name2 IN Customer.name%type,Location2 IN Branch.Address%type,amount IN account.Balance%type) RETURN NUMBER;
     --Prints--
     FUNCTION shw_All_BRanchs RETURN VARCHAR;
     Function shwcustoms( nme In Customer.name%type) Return varchar;
     FUNCTION shwBRanchs( ady In Branch.address%type) RETURN VARCHAR;

     ---HELPER FUNCTIONS---
     Function lcheck(highest iN Account.A#%type)RETURN BOOLEAN;

END BANK;   
