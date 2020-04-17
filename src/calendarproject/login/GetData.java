/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarproject.login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author towns
 */
public class GetData {
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    
    private void CloseConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    //Store username that is logged in
    public String getDataFromDataBase(String username) throws Exception {
        String result = username;
        
        
      
        
        return result;
    }
    
    
}
