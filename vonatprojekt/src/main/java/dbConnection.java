import java.sql.*;
import javax.swing.*;

public class dbConnection {
    Connection conn=null;
    public static Connection dbConnector(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/vonatjegyDB", "root", "marigol1");
            return conn;
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
}
