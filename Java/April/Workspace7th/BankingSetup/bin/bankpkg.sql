CREATE or ALTER PACKAGE BANK AS
    ---Customer---
     FUNCTION to_cunumbr(num IN number) RETURN Customer.C#%type; FUNCTION newCustomer(name IN Customer.name%type) RETURN VARCHAR;
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
/
CREATE OR REPLACE PACKAGE Body BANK AS
---BRANCH---
--**PART 2 Q3**--
Procedure close_branch(addy IN Branch.Address%type)AS
    branchMA EXCEPTION;
BEGIN
    delete Branch where Address = addy;
    If sql%NOTFOUND THEN
        RAISE branchMA;
    ElSIF sql%FOUND THEN
        dbms_output.put_line('Deleted');    
    END IF;
EXCEPTION
    When branchMA THEN
        dbms_output.put_line('NO BRANCH WAS FOUND, Cant delete');
END;
--**PART 2 Q2**--
FUNCTION open_branch(addy2 IN Branch.Address%type) RETURN VARCHAR IS
    chek Branch.B#%type;
    cursor bcurs is select * from Branch ORDER BY B# ASC;
    newBnum number := 0;
    bexcept EXCEPTION;
    compnumber number;
    bnum Branch.B#%type;
    retu VARCHAR(100);
BEGIN
    retu:= '';
    chek := branch_fun(addy2);
    IF chek IS NOT NULL THEN
        RAISE bexcept;
    END IF;
    newBnum := newBnum + 1;
    for br IN bcurs LOOP
        exit WHEN bcurs%NOTFOUND;
        compnumber := TO_NUMBER(br.B#);        
        IF newBnum != compnumber THEN
            EXIT;
        ELSE
            newBnum := newBnum + 1;
        END IF;
    END LOOP;
    ibranch(newBnum,addy2,bnum);
    retu := 'NEW BRANCH OPEN B#: ' || bnum ||' LOCATION: '|| addy2;
    RETURN retu;

EXCEPTION
    WHEN bexcept THEN
        retu:= 'Branch already exists';
        RETURN retu;
    When others THEN
        retu := 'UNKNOWN PROBLEM open';
        RETURN retu;
END;

--**PART 2 Q1**--
FUNCTION branch_fun(addy1 IN Branch.Address%type) RETURN Branch.B#%type IS 
    cursor bcurs is select * from Branch;
BEGIN
    for br in bcurs loop
        exit when bcurs%NOTFOUND;
        IF addy1 = br.Address THEN
            RETURN br.B#;
        END IF ;
    END LOOP;
    RETURN NULL;
END;

Procedure ibranch(bn IN number ,addy IN char,newb out Branch.B#%type)AS
numerror EXCEPTION;
leadingz varchar(3);
numchar char(3);
tempchar varchar(3);
BEGIN
    IF (bn > 999) THEN
        RAISE numerror ;
    ELSIF (bn > 99) THEN
        leadingz := '' ;
    ELSIF (bn > 9) THEN
        leadingz := '0' ;
    ELSE
        leadingz := '00' ;    
    END IF;
    tempchar := TO_CHAR(bn);
    numchar := CONCAT(leadingz,tempchar);
    INSERT INTO Branch VALUES(numchar,addy);
    newb := numchar;
    COMMIT;
EXCEPTION
    WHEN numerror THEN
        dbms_output.put_line('Number TOO BIG') ;
    When others THEN
        dbms_output.put_line('UNKNOWN PROBLEM') ;
END;

---CUSTOMER---
--**PART 2 Q4**--
    FUNCTION newCustomer(name IN Customer.name%type) RETURN VARCHAR As
        custEx EXCEPTION;
        custb BOOLEAN;
        higestnum Customer.C#%type;
        Stat number;
        retter VARCHAR(1000);
    BEGIN
        retter :='';
        chekcust(name,custb);
        IF custb THEN
            RAISE custEx;
        End IF;
        h8stcust(higestnum);
        INSERT INTO CUSTOMER VALUES(higestnum,name);
        retter := 'Created customer '|| name || ' With C#: '|| higestnum;
        COMMIT;
        RETURN retter;

    EXCEPTION
        when custEx THEN
            retter:= 'CREATING CUSTOMER ERROR: customer already in the table';
            RETURN retter;
        when others THEN   
            retter:= 'UNKNOW ERROR';
            RETURN retter;
    END;

    PROCEDURE chekcust(cname IN Customer.Name%type,bull OUT BOOLEAN) AS 
        cursor cust is select * from Customer;
    BEGIN
        for cus in cust loop
            exit when cust%NOTFOUND;
            IF cname = cus.Name THEN
                bull := TRUE;
                RETURN ;
            END IF ;
        END LOOP;
        bull := FALSE;
    END;

    FUNCTION to_cunumbr(num IN number) RETURN Customer.C#%type is
        leadingz varchar(5);
        numbchar varchar(5);
        Tempvar varchar(5);
        NUMEX EXCEPTION;
    BEGIN
        IF (num > 99999)THEN
            RAISE NUMEX;
        ELSIF (num > 9999) THEN
            leadingz := '';
        ELSIF (num > 999) THEN
            leadingz := '0';
        ELSIF (num > 99) THEN
            leadingz := '00' ;
        ELSIF (num > 9) THEN
            leadingz := '000' ;
        ELSE
            leadingz := '0000' ;    
        END IF;
        Tempvar := TO_CHAR(num);
        numbchar := CONCAT(leadingz,Tempvar);
        RETURN numbchar;

    EXCEPTION
        when NUMEX THEN
            dbms_output.put_line('TOO big a number');
        when others THEN
            dbms_output.put_line('UNKNOWN');
    END;

    PROCEDURE h8stcust(higest OUT Customer.C#%type) AS 
        cursor cust is select * from Customer;
        iteratnum number;
        compnumbe number;
    BEGIN
        iteratnum := 1;
        for cus in cust loop
            exit when cust%NOTFOUND;
            compnumbe := TO_NUMBER(cus.C#);
            IF (compnumbe != iteratnum) THEN
                higest := to_cunumbr(iteratnum);
                RETURN ;
            Else
                iteratnum :=iteratnum + 1;
            END IF;
        END LOOP;
        higest := to_cunumbr(iteratnum);
    END;
--**PART 2 Q5**--
FUNCTION rmv_customer(name IN Customer.Name%type) Return VARCHAR AS
    custEx EXCEPTION;
    custb BOOLEAN;
    res VARCHAR(1000);
    cursor cust is select * from Customer;
BEGIN
chekcust(name,custb);
IF custb THEN
    for cus in cust loop
            exit when cust%NOTFOUND;
            IF (cus.Name=name) THEN
                DELETE CUSTOMER where C# = cus.C# ;
                res := 'Deleted Customer ' || name;
            END IF;
    END LOOP;
ELSE
    RAISE custEx;
END IF;
RETURN res;
EXCEPTION
    when custEx THEN
        res := 'COULD NOT DELETE CUSTOMER NOT FOUND';
        RETURN res;
END;


---ACCOUNT---
PROCEDURE getCnum(name IN customer.name%type,cnumb Out customer.C#%type) AS
    cursor cuscur is Select * from Customer;
    compstr Customer.name%type; 
BEGIN
    for ac IN cuscur LOOP
        exit WHEN cuscur%NOTFOUND;
        compstr := ac.Name;
        IF (compstr=name) THEN
            cnumb:= ac.C# ;
        END IF;
    END LOOP;
END;

PROCEDURE getBnum(name IN Branch.address%type,cnumb Out Branch.B#%type) AS
    cursor cuscur is Select * from Branch;
    compstr Branch.address%type; 
BEGIN
    for ac IN cuscur LOOP
        exit WHEN cuscur%NOTFOUND;
        compstr := ac.address;
        IF (compstr=name) THEN
            cnumb:= ac.B# ;
        END IF;
    END LOOP;
END;

FUNCTION to_Anum(brn Branch.B#%type, anum number)RETURN Account.A#%type is
    accnum varchar2(7);
    anumconver varchar(4);
    tempvar varchar(4);
    numbex EXCEPTION;
    leadingz varchar(4);
BEGIN
    IF (anum>9999) THEN
        RAISE numbex;
    ELSIF (anum>999) THEN
        leadingz := '';
    ELSIF (anum>99) THEN
        leadingz := '0';
    ELSIF (anum >9) THEN
        leadingz := '00';
    ELSE
        leadingz := '000';
    END IF;
    tempvar := To_char(anum);
    anumconver := CONCAT(leadingz,tempvar);
    accnum := CONCAT(brn,anumconver);
    RETURN accnum;

EXCEPTION
    when numbex THEN
        dbms_output.put_line('NUMBER TOO BIG');
END;

PROCEDURE h8staccount(cusnum IN Account.CU#%type,branchnum IN Branch.B#%type,highest OUT Account.A#%type) AS
    cursor acus is select * From Account Order by A# ASC ;
    hyes number;
    numcomp number;
    cutAnum char(4);
    toenter varchar(6);
    cursor acuss is select * From Account ;
    dup BOOLEAN;
    dup2 BOOLEAN;
BEGIN
    dup := TRUE;
    hyes := 1;
    for cus in acus loop
        exit when acus%NOTFOUND;
        cutAnum := SUBSTR(cus.A#,0,3);
        IF (cutAnum=branchnum) THEN
            hyes:= hyes + 1;
        END IF;       
    END LOOP;
     highest:= to_Anum(branchnum,hyes);
    while dup
    LOOP
        dup2 := lcheck(highest);
        if (dup2) THEN
        hyes:= hyes + 1;
        highest:= to_Anum(branchnum,hyes);
        ELSE
            dup := False;
        END If;
     END loop;   
END;
--**PART 2 Q6**--
Function open_acc(cust IN customer.name%type, addy IN Branch.Address%type, ammount IN number) RETURN varchar AS
    custntable BOOLEAN;
    branchnum Branch.B#%type;
    cusnum Customer.C#%type;
    newAnum Account.A#%type;
    retu varchar(1000);
BEGIN
retu := '';
IF (ammount>-1) THEN
    chekcust(cust,custntable);
    IF (custntable) THEN
        branchnum := branch_fun(addy);
        IF branchnum IS NOT NULL THEN
            getCnum(cust,cusnum);
            getBnum(addy,branchnum);
            h8staccount(cusnum,branchnum,newAnum);
            insert into account values (newAnum,cusnum,ammount);
            If sql%NOTFOUND THEN
                retu := 'Not inserted';
                RETURN retu;
            ElSIF sql%FOUND THEN
                retu:= 'NEW ACCOUNT CREATED A# '||newAnum||' CU# '||cusnum||' Ammount $'||ammount;
                RETURN retu;   
            END IF;
        ELSE 
            retu:= 'BRANCH NOT IN TABLE';
            RETURN retu;
        END IF;
    ELSE 
        retu := 'CUSTOMER NOT IN TABLE';
        RETURN retu;
    END If;
END IF;
RETURN retu;
END;

--**PART 2 Q7**--
PROCEDURE close_acc(accnum IN Account.A#%type) AS
    cursor accur is select * from account ; 
    chekanum Account.A#%type;
BEGIN
    for cus in accur LOOP
        exit WHEN accur%NOTFOUND;
        chekanum := cus.A#;
        If (chekanum = accnum) THEN
            IF (cus.Balance = 0) THEN
                DELETE Account WHERE A# = chekanum;
            END IF;
        END IF;
    END LOOP;
END;
--**PART 2 Q8**--
PROCEDURE withdraw(amunt IN Account.A#%type,ammount IN number) AS
    cursor acc(a Account.A#%type) IS select * from account where A# = a;
    amm number;
    newamm number;
BEGIN
    for ac IN acc(amunt) LOOP
        exit WHEN acc%NOTFOUND;
        amm := ac.Balance;
        IF (ammount<=amm) THEN
            newamm := amm - ammount;
            Update Account set Balance = newamm where A# = amunt;
        END IF;
    END LOOP;
END;
--**PART 2 Q9**--
PROCEDURE deposit(amunt IN Account.A#%type,ammount IN number) AS
    cursor acc(a Account.A#%type) IS select * from account where A# = a;
    amm number;
    newamm number;
BEGIN
    for ac IN acc(amunt) LOOP
        exit WHEN acc%NOTFOUND;
        amm := ac.Balance;
        IF (ammount>-1) THEN
            newamm := amm + ammount;
            Update Account set Balance = newamm where A# = amunt;
        END IF;
    END LOOP;
END;
--**PART 2 Q10**--
PROCEDURE actransfer(acun1t IN Account.A#%type,acun2t IN Account.A#%type,amount IN number) AS
BEGIN
    withdraw(acun1t,amount);
    deposit(acun2t,amount);
END;

FUNCTION transferr(name1 IN Customer.name%type,Location1 IN Branch.Address%type,name2 IN Customer.name%type,Location2 IN Branch.Address%type,amount IN account.Balance%type) RETURN NUMBER AS
    cust BOOLEAN;
    cust2 BOOLEAN;
    custn1 Customer.C#%type;
    custn2 Customer.C#%type;
    account1 account.A#%type;
    account2 account.A#%type;
    brn1 Branch.B#%type;
    brn2 Branch.B#%type;
    cursor ac(cu Customer.C#%type,br Branch.B#%type) IS select A# from account where CU#=cu and A# like br;
    pass1 char(4);
    pass2 char(4);
    num NUMBER;
BEGIN
    num := 0;
    chekcust(name1,cust);
    chekcust(name2,cust2);
    IF (cust and cust2) THEN 
        getCnum(name1,custn1);
        getCnum(name2,custn2);
        getBnum(Location1,brn1);
        getBnum(Location2,brn2);
        pass1:= CONCAT(brn1,'%');
        pass2 := CONCAT(brn2,'%');
        FOR cus IN ac(custn1,pass1) Loop
            exit When ac%NOTFOUND;
            account1 := cus.A#;
            num := num + 1;
        END LOOP;
        FOR cus IN ac(custn2,pass2) Loop
            exit When ac%NOTFOUND;
            account2 := cus.A#;
            num := num + 1;
        END LOOP;
        if (num = 2) THEN
            actransfer(account1,account2,amount);
            Return 1;
        END IF;
        Return 0;
    END IF;
    Return 0;

END;

Function deposits(name IN Customer.name%type, Location IN Branch.Address%type,Ammount In Account.Balance%type) RETURN NUMBER AS
    cnum Customer.C#%type;
    cusb Boolean;
    brunchnum Branch.B#%type;
    pass char(4);
    cursor dcus(cn Customer.C#%type,bn Branch.B#%type) is Select A# from account where Cu#= cn and A# like bn ; 
BEGIN
chekcust(name,cusb);
IF cusb THEN
getCnum(name,cnum);
brunchnum := branch_fun(Location);
pass := CONCAT(brunchnum,'%');
FOR cus IN dcus(cnum,pass) Loop
    exit when dcus%NOTFOUND;
    deposit(cus.A#,Ammount);
    Return 1;
END LOOP;

END IF;
Return 0;
END;

FUNCTION close_E_acc  RETURN VARCHAR AS
cursor rvmc is Select A# from account where Balance = 0 ;
res VARCHAR(100);
countt number;
BEGIN
countt := 1;
For cus in rvmc Loop 
    exit when rvmc%NOTFOUND;
    close_acc(cus.A#);
    countt := countt + 1;
END Loop;
res := 'Deleted '|| countt ||' Accounts ';
RETURN res;
END;

---SHOW---
--**PART 2 Q12**--
FUNCTION shw_All_BRanchs RETURN VARCHAR AS
    cursor brnum is Select Address from Branch ORDER by B#;
    bn Branch.Address%type;
    retur varchar(3000);
BEGIN
retur := '';
open brnum ;
Loop
    fetch brnum into bn;
    Exit When brnum%NOTFOUND;
    retur:= retur || shwBRanchs(bn) || ' ,  , ';
END Loop;
CLOSE brnum;
Return retur;
END;
--**PART 2 Q13**--
Function shwcustoms( nme In Customer.name%type) Return varchar AS
    chek Customer.C#%type;
    cust BOOLEAN;
    shwexecption EXCEPTION;
    cursor recCus(cr account.CU#%type) is select * from account where CU# Like cr;
    Tamount NUMBER;
    pass char(6);
    returnn varchar(1000);
    returnerr varchar(30);
BEGIN
returnerr:='';
returnn:='';
chekcust(nme,cust);
Tamount:=0;
IF cust THEN
getCnum(nme,chek);
pass:= CONCAT(chek,'%');
returnn:='Showing account '|| nme ||' , ';
returnn:= returnn ||'CU#          A#       BALANCE       TOTAL , ';
returnn:= returnn|| '================================================= , ';
FOR cus IN recCus(pass) LOOP
    exit When recCus%NOTFOUND ;
    returnn:= returnn ||cus.CU#||'     '||cus.A#||'        '||cus.balance||' , ';
    Tamount:= Tamount + cus.balance;
END LOOP; 
returnn:= returnn||' ------------------------------------$'||Tamount||' ';
ELSE
    RAISE shwexecption;
END IF;
Return returnn; 
EXCEPTION
    WHEN shwexecption THEN
        returnerr:='Customer NOT FOUND';
        Return returnerr;
    When others THEN
        returnerr:='UNKNOWN PROBLEM';
        Return returnerr;

END;
--**PART 2 Q11**--
FUNCTION shwBRanchs( ady In Branch.address%type) RETURN VARCHAR AS
    chek Branch.B#%type;
    shwexecption EXCEPTION;
    cursor recBR(br Branch.B#%type) is select * from account where A# Like br;
    Tamount NUMBER;
    pass char(4);
    returnn varchar(1000);
    returnerr varchar(30);
BEGIN
returnerr := '';
returnn := '';
chek:= branch_fun(ady);
Tamount:=0;
IF chek IS NULL THEN 
    RAISE shwexecption;
END IF;
pass := CONCAT(chek,'%');
    returnn:= returnn ||'Showing accounts and balance from Branch NO:'||chek||' , ';
    returnn:= returnn ||'A#            BALANCE         TOTAL'||' , ';
    returnn:= returnn ||'===================================='||' , ';
    FOR cus IN recBR(pass) LOOP
        exit When recBR%NOTFOUND;
        returnn:= returnn ||cus.A#||'         $'||cus.balance||' , ';
        Tamount:= Tamount + cus.balance;
    END LOOP;
    returnn:= returnn ||' -----------------------------$'||Tamount;
    Return returnn; 
    
EXCEPTION
    WHEN shwexecption THEN
        Return 'Branch NOT FOUND';
    When others THEN
        Return 'UNKNOWN PROBLEM';

END;
Function lcheck(highest iN Account.A#%type)RETURN BOOLEAN IS 
    BUL BOOLEAN;
    cursor acuss is select * From Account ;
BEGIN
    for cuss in acuss loop
      exit when acuss%NOTFOUND;
            If (highest = cuss.A#) THEN
                dbms_output.put_line('found duplicate');
                BUL := TRUE ; 
                RETURN BUL;
            END IF;      
    END LOOP;
    BUL := False;
    RETURN BUL;
END;
    
END BANK;
/
