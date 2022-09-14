import settings.AppSettings;
import spring.StartWebApplication;

public class Main {
    public static void main(String[] args) {
        AppSettings.Initialaize();

        if (AppSettings.APPLICATION_TYPE.equalsIgnoreCase("CLI")){
            runCLIApp();
        } else if (AppSettings.APPLICATION_TYPE.equalsIgnoreCase("WEB")) {
            runWEBApp(args);
        } else {
            throw new RuntimeException("APPLICATION_TYPE must be WEB or CLI");
        }
    }

    static void runCLIApp(){

    }
    static void runWEBApp(String... args){
        StartWebApplication.run(args);
    }

}
