/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarproject.login;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author towns
 */
public class MyConnection {
    public static Connection getConnection() throws Exception {
      Class.forName("com.mysql.jdbc.Driver");
      
      String hostName = "www.secs.oakland.edu";
      String user = "betownson";
      String password = "WChG3zEdQP5ntscb";
      String dbName = "betownson";
      String url = String.format("jdbc:mysql://www.secs.oakland.edu:3306/betownson?zeroDateTimeBehavior=convertToNull");
      return DriverManager.getConnection(url, user, password);
      
    }
}
