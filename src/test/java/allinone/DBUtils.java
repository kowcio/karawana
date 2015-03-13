/*
 * 
 */
package rlhd.hd.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.log4j.Logger;

public class DBUtils {
    
    public Logger        log         = Logger.getLogger(getClass());
    
    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DBNAME      = "rl";
    private final String DBTYPE      = "postgresql";
    private final String DBIP        = "10.0.0.6";
    private final String USER        = "rl";
    private final String PASS        = "rlIsAwesome";
    private final String DB_URL      = "jdbc:" + DBTYPE + "://" + DBIP + "/" + DBNAME;
    
    public String getSessionDataBySessionID(String sessionid) {
        String query = "  select auth_user.id, username, email,userena_userenasignup.id, user_id, activation_key from auth_user\r\n"
                + "    left JOIN \r\n"
                + "    userena_userenasignup on auth_user.id = userena_userenasignup.user_id \r\n"
                + "     where auth_user.id > 47950 \r\n" + "    AND auth_user.email = ? order by auth_user.id desc";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        String activationKey = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement(query);
            stmt.setString(1, sessionid);
            // query
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            
            while (rs.next()) {
                
                StringBuffer sb = new StringBuffer();
                for (int i = 1; i <= columnCount; i++)
                    sb.append(rsMetaData.getColumnLabel(i) + " # ");
                log.info("COLUMNS : " + sb.toString());
                
                for (int i = 1; i <= columnCount; i++)
                    sb.append(rs.getString(i) + " # ");
                log.info("ROW " + sb.toString());
                activationKey = rs.getString("activation_key");
            }
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activationKey;
    }
    
    public String getActivationLinkByEmail(String email) {
        
        String query = "  select auth_user.id, username, email,userena_userenasignup.id, user_id, activation_key from auth_user\r\n"
                + "    left JOIN \r\n"
                + "    userena_userenasignup on auth_user.id = userena_userenasignup.user_id \r\n"
                + "     where auth_user.id > 47950 \r\n" + "    AND auth_user.email = ? order by auth_user.id desc";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        String activationKey = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            // query
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            
            while (rs.next()) {
                
                StringBuffer sb = new StringBuffer();
                for (int i = 1; i <= columnCount; i++)
                    sb.append(rsMetaData.getColumnLabel(i) + " # ");
                log.info("COLUMNS : " + sb.toString());
                
                for (int i = 1; i <= columnCount; i++)
                    sb.append(rs.getString(i) + " # ");
                log.info("ROW " + sb.toString());
                activationKey = rs.getString("activation_key");
            }
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activationKey;
        
    }
    
}
