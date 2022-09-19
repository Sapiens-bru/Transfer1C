package FileProcessing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static settings.AppSettings.*;

public class FileIO {

    public static void main(String[] args) {

        //Path path = Paths.get("src/main/resourses/testIO.txt");
        Path path = Paths.get(OUTPUT_FILE);
        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
            writer.write("To be, or not to be. That is the question.");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
