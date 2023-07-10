
package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import DAO.AccountDAO;


public class ConnectDB {
     public Connection getConnection()throws Exception {
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber + "\\" + instance +";databaseName="+dbName;
        if(instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName + "; encrypt=true;trustservercertificate=true; sslProtocol=TLSv1.2";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);

    }   
    /*Insert your other code right after this comment*/
    /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "LENOVO";
    private final String dbName = "ApartmentDB";
    private final String portNumber = "1433";
    private final String instance="";//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "sa";
    private final String password = "123456";
    public static void main(String[] args) {
        
        
        
    }

    private void Exception() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
 