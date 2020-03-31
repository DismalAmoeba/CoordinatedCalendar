/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author towns
 */
public class MyConnection {
    public static Connection getConnection() throws Exception {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      
      String hostName = "coordinatedcalendar.database.windows.net";
      String dbName = "";
      String user = "user";
      String password = "V:qkp1sGmrUW";
      String url = String.format("jdbc:sqlserver://coordinatedcalendar.database.windows.net:1433;database=CoordinatedCalendar;user=user@coordinatedcalendar;password=V:qkp1sGmrUW;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30");
            
      return DriverManager.getConnection(url, user, password);
        
    }
}
