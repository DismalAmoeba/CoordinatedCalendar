/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetData;

import Connection.MyConnection;
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
    
    public String getDataFromDataBase() throws Exception {
        String result = "";
        try {
            conn = MyConnection.getConnection();
            if(conn != null) {
                String sql ="Select USER_NUM from USER";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    result = result + "," + rs.getString("USER_NUM");
                }
            }
        } catch(Exception e) {
            
        }finally {
            CloseConnection();
        }
        return result;
    }
}
