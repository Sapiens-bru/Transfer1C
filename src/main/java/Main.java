import FileProcessing.FileIO;
import static settings.AppSettings.*;
import spring.StartWebApplication;
import sql.DBConnection;
import sql.SupportedDB;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        Initialaize();

        if (APPLICATION_TYPE.equalsIgnoreCase("CLI")){
            runCLIApp();
        } else if (APPLICATION_TYPE.equalsIgnoreCase("WEB")) {
            runWEBApp(args);
        } else {
            throw new RuntimeException("APPLICATION_TYPE must be WEB or CLI");
        }
    }

    static void runCLIApp(){
        //FileIO.main(null);
        String query = "Select * from "+MS_SQL_DATABASE+"."+MS_SQL_INFORG;
        DBConnection dbConnection = new DBConnection(SupportedDB.MSSQL);
        ResultSet rs = dbConnection.makeRequest(query);
    }
    static void runWEBApp(String... args){
        StartWebApplication.run(args);
    }

}
