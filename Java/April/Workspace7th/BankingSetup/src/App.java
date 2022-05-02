import java.sql.*;
import java.util.*;

import javax.swing.InputMap;


public class App {
    //JDBC driver and url initialization
    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_URL = "jdbc:sqlserver://localhost;databaseName=Bank;integratedSecurity=true;";
    //Database credentials
    static final String USER = "root";
    static final String PASS = "MYsql12";
    //Program variables

    static int highestB = 000;
    static  String todelete ;
    static boolean deleting = false;
    static int currentBN;
    static int currentCN;
    static List<Integer> unUsedBranchNumbers = new ArrayList<Integer>();
	static List<Integer> unUsedAccountNumbers = new ArrayList<Integer>();
    public static void main(String[] args) {
        Scanner inputt = new Scanner (System.in);
        Connection conn = null;
        boolean flag = true;
    
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("JDBC connected");
            System.out.println("===================================");
            System.out.println("Welcome Employee!");
            while (flag) {
                System.out.println("What would you like to do? (Reply with option number) ");
                System.out.println("1. Open a new Branch - Provide a location");
                System.out.println("2. Add Customer to Customer table - Provide customer Name");
                System.out.println("3. open Account - Provide a customer name, branch address or branch number , an initial ammount 0 is defualt");
                System.out.println("4. Show Customer - Provide a customer name ");
                System.out.println("5. Show Branch - Provide branch name");
                System.out.println("6. Show all branches");
                System.out.println("7. Deposit - Provide Customer name, Branch location, ammount");
                System.out.println("8. Transfer - Provide a Customer name, two account numbers first one FROM , second one TO");
                System.out.println("9. Delete Customer if account not available");
                System.out.println("10 Close empty accounts");
                System.out.println("99 Quits the program");
                int input = inputt.nextInt(); 
                System.out.println();
                if (input < 1 || (input > 10 && input != 99)){
                    System.out.println("***Wrong Option number try again.***");
                }else{
                    switch (input) {
                        case 1:
                            System.out.println("***What is the Address ? ***");
                            inputt = new Scanner(System.in);
                            String reply = inputt.nextLine();
                            String newbrnch;
                            System.out.println();
                            newbrnch = openbranch(reply,conn);
                            if (newbrnch!=null){
                                System.out.println("*** " +newbrnch+ " ***");
                            }else{
                                System.out.println("***ERROR Branch not added***");
                            }
                            System.out.println("**********************************");
                            System.out.println();

                            break;
                        case 2:
                            String replable = "";
                            System.out.println("*** WHO is the new customer? ***");
                            inputt = new Scanner(System.in);
                            String reply2 = inputt.nextLine();
                            System.out.println();
                            replable = createcustomer(reply2,conn);
                            System.out.println(replable);
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 3:
                            replable = "";
                            System.out.println("***Opening Account***");
                            System.out.println("Who's new account?");
                            inputt = new Scanner(System.in);
                            String reply3 = inputt.nextLine();
                            System.out.println("Where is the new account?");
                            inputt = new Scanner(System.in);
                            String reply4 = inputt.nextLine();
                            System.out.println("How much is going in ?");
                            inputt = new Scanner(System.in);
                            int numbr1 = inputt.nextInt();
                            System.out.println();
                            replable = createAccount(reply3,reply4,numbr1,conn);
                            System.out.println("***"+replable+"***");
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 4:
                            System.out.println("Show customer");
                            inputt = new Scanner(System.in);
                            String fun = inputt.nextLine();
                            System.out.println();
                            showcustomer(fun,conn);
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 5:
                            System.out.println("Show Branch details");
                            inputt = new Scanner(System.in);
                            String inn = inputt.nextLine();
                            System.out.println();
                            showbranch(inn,conn);
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 6:
                            System.out.println("Showing all branches");
                            System.out.println();
                            show_allbrnch(conn);
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 7:
                            System.out.println("***Customer Name***");
                            inputt = new Scanner(System.in);
                            String nm2 = inputt.nextLine();

                            System.out.println("***Branch Location***");
                            inputt = new Scanner(System.in);
                            String an2 = inputt.nextLine();

                            System.out.println("***Provide An ammount to Deposit***");
                            inputt = new Scanner(System.in);
                            int a2 = inputt.nextInt();
                            System.out.println();
                            depositinto(nm2,an2,a2,conn);
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 8:
                            System.out.println("***Account Transfer***");
                            System.out.println("***Provide Customer Name***");
                            inputt = new Scanner(System.in);
                            String nm3 = inputt.nextLine();
                            System.out.println("***Branch Location***");
                            inputt = new Scanner(System.in);
                            String an13 = inputt.nextLine();
                            System.out.println("***Second account name ***");
                            inputt = new Scanner(System.in);
                            String nm4 = inputt.nextLine();
                            System.out.println("***Branch Location***");
                            inputt = new Scanner(System.in);
                            String an14 = inputt.nextLine();
                            System.out.println("***Amount?***");
                            inputt = new Scanner(System.in);
                            int am12 = inputt.nextInt();
                            System.out.println();
                            transfermon(nm3,an13,nm4,an14,am12,conn);
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 9:
                            String re1;
                            System.out.println("*** Who would you like to delete ? ***");
                            inputt = new Scanner(System.in);
                            String bn1 = inputt.nextLine();
                            System.out.println();
                            re1 = closecust(bn1,conn);
                            System.out.println( re1);
                            System.out.println("**********************************");
                            System.out.println();
                            break;
                        case 10:
                            String re ;
                            re = closeEaccounts(conn);
                            System.out.println(re);
                            System.out.println("**********************************");
                            System.out.println();
                        
                            break;
                        case 99:
                            System.out.println("Thank you for using Amanya's Bank system");
                            flag = false;
                            break;

                    }
            }


            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println("GOODBYE");
        inputt.close();

    
    }
    private static String openbranch(String location, Connection conn ){
        String result ="";
        try{
            CallableStatement stmt = conn.prepareCall("{? = call BANK.open_branch(?)}");
            stmt.registerOutParameter(1,java.sql.Types.CHAR);
            stmt.setString(2,location);
            stmt.execute();
            result= stmt.getString(1);
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
            result = null;
        }

        return result;


    }
    private static String createcustomer(String name,Connection conn){
        String result ="";
        try{
            CallableStatement stmt = conn.prepareCall("{? = call BANK.newCustomer(?)}");
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.setString(2,name);
            stmt.execute();
            result = stmt.getString(1);
            stmt.close();
           

        }catch(SQLException e){
            e.printStackTrace();
            result = "There was a problem";
        }
        return result;
    }

    private static String createAccount(String name, String address, int ammount,Connection conn){
        String result = "";
        try{
            CallableStatement stmt = conn.prepareCall("{? = call BANK.open_acc(?,?,?)}");
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.setString(2,name);
            stmt.setString(3,address);
            stmt.setInt(4,ammount);
            stmt.execute();
            result = stmt.getString(1);
            stmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;

    }
    private static void show_allbrnch(Connection conn){
        String [] result ;
        try{
            CallableStatement stmt = conn.prepareCall("{?= call BANK.shw_All_BRanchs()}");
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.execute();
            result = stmt.getString(1).split(" , ");
            for(String s : result){
                System.out.println(s);
            }
            stmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void showcustomer(String name ,Connection conn){
        String [] result ;

        try{
            CallableStatement stmt = conn.prepareCall("{?=call BANK.shwcustoms(?)}");
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.setString(2,name);
            stmt.execute();
            result = stmt.getString(1).split(" , ");
            for (String a : result){
                System.out.println(a);
            }

            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private static void showbranch(String brnch,Connection conn){
        String [] result ;
        try{
            CallableStatement stmt = conn.prepareCall("{?=call BANK.shwBRanchs(?)}");
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.setString(2,brnch);
            stmt.execute();
            result = stmt.getString(1).split(" , ");
            for (String a : result){
                System.out.println(a);
            }
            stmt.close();


        }catch(SQLException e){ 
            e.printStackTrace();

        }
    }
    private static void depositinto(String name, String location, int amount, Connection conn){
        int result=-1;

        if (amount>0){
            try{
                CallableStatement stmt = conn.prepareCall("{? = call BANK.deposits(?,?,?)");
                stmt.registerOutParameter(1,java.sql.Types.INTEGER);
                stmt.setString(2,name);
                stmt.setString(3,location);
                stmt.setInt(4,amount);
                stmt.execute();
                stmt.close();
               result = stmt.getInt(1);
               System.out.println(result);
                if (result == 1) {
                    System.out.println("Deposited Money into account");
                }else if ( result==0){
                    System.out.println("Customer does not have an account");
                }

            }catch(SQLException e){
                System.out.println("Customer does not have an account");
            }
        }  
    }
    private static void transfermon(String name, String Location, String name2, String Location2,int amount,Connection conn){
        int result;
        if (amount>0){
            try{
                CallableStatement stmt = conn.prepareCall("{? = call BANK.transferr(?,?,?,?,?)");
                stmt.registerOutParameter(1,java.sql.Types.INTEGER);
                stmt.setString(2,name);
                stmt.setString(3,Location);
                stmt.setString(4,name2);
                stmt.setString(5,Location2);
                stmt.setInt(6,amount);
                stmt.execute();
                result = stmt.getInt(1);
                stmt.close();
                if (result == 1) {
                    System.out.println("Transfered Money into account");
                }else if ( result==0){
                    System.out.println("Money was not transfered");
                }


            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    private static String closecust(String name, Connection conn){
        String res="";
        try{
            CallableStatement stmt = conn.prepareCall("{? = call BANK.rmv_customer(?)");
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.setString(2,name);
            stmt.execute();
            res=stmt.getString(1);
            stmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    private static String closeEaccounts(Connection conn){
        String res="";
        try{
            CallableStatement stmt = conn.prepareCall("{? = call BANk.close_E_acc()");
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.execute();
            res = stmt.getString(1);
            stmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }


    
}