package utility;

import java.sql.SQLException;

public class ExceptionPrinter {
    
    public static void printSqlException(SQLException exe) {
                System.err.println("SQL state: " + exe.getSQLState());
                System.err.println("Error code: " + exe.getErrorCode());
                System.err.println("Error message: " + exe.getMessage());
    }

    public static void printException(Exception exe) {
                System.err.println("Exception: " + exe.getMessage());
    }
}
