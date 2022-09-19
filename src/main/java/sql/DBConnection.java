package sql;
import static settings.AppSettings.*;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DBConnection {
    private Connection connection;

    public DBConnection(SupportedDB dbType){
        connectDatabase(dbType);
    }

    public void connectDatabase(SupportedDB dbType){
        if (connection!=null){return;}

        if (dbType == SupportedDB.MSSQL) {
            connectMSSQL();
        }
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        connection = null;
    }

    private void connectMSSQL(){
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String dbURL = MS_SQL_PATH;
        Properties properties = new Properties();
        properties.put("integratedSecurity","true");
        properties.put("encrypt","false");
        properties.put("trustServerCertificate","true");
        properties.put("user",MS_SQL_USER);
        properties.put("password",MS_SQL_PWD);

        connection= null;
        try {
            connection = DriverManager.getConnection(dbURL,properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection == null) {
            throw new RuntimeException(" no connection ");
        }
    }

    public ResultSet makeRequest(String query){
        if (connection == null) {
            throw new RuntimeException(" no connection ");
        }
        if (query==null) {
            query = "SELECT 1";
        }

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i)).append(", ");
                }
                System.out.println(row);

            }
            return rs;

        } catch (SQLException e) {
            return null;
        }
    }



}