import java.sql.*;
public class TestConnection {
    public static void main(String[] args) {
        String connectionstrng = "jdbc:sqlserver://localhost:1433;Database=Bank;IntegratedSecurity=true;encrypt = true; trustServerCertificate = true";
        try {
            try(Connection connection = DriverManager.getConnection(connectionstrng)){
                System.out.println("Connection established");
            }
            
        } catch (SQLException e) {
            System.out.println("Error Connecting to database");
            e.printStackTrace();
                }
        
    }
    
}
