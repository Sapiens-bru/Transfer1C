import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class AppSettings {
    static String MS_SQL_PATH;
    static String MS_SQL_USER;
    static String MS_SQL_PWD;

    static void Initialaize(){
        Path path = Paths.get("local_settings.txt");

        Map<String,String> properties = new HashMap<String,String>();

        try {
            for (String oneline: Files.readAllLines(path)) {
                String[] propPair = oneline.split("=");
                if (propPair.length==2){
                    properties.put(propPair[0].toUpperCase().trim(),propPair[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<String,String> property:properties.entrySet()) {
            try {
                Field appSettingsField = AppSettings.class.getDeclaredField(property.getKey());
                appSettingsField.setAccessible(true);
                appSettingsField.set(null,property.getValue());
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
