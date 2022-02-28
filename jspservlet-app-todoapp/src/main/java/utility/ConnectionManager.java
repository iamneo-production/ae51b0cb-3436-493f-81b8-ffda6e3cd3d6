package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    public static Connection getConnection(){
        
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/todoapp", "root", "examly");
        }
        catch(Exception exe){
            ExceptionPrinter.printException(exe);
        }

        return connection;
    }

}
