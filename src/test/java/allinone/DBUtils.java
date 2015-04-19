package allinone;

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
    private final String DBNAME      = "";
    private final String DBTYPE      = "postgresql";
    private final String DBIP        = "";
    private final String USER        = "rl";
    private final String PASS        = "";
    private final String DB_URL      = "jdbc:" + DBTYPE + "://" + DBIP + "/" + DBNAME;
    
    public String getSessionDataBySessionID(String sessionid) {
        String query = "  select auth_u...";
        
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
    
    
}
