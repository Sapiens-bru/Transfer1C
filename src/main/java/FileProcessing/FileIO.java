package FileProcessing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {

    public static void main(String[] args) {

        //Path path = Paths.get("src/main/resourses/testIO.txt");
        Path path = Paths.get("d:\\\\temp\\testIO.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
            writer.write("To be, or not to be. That is the question.");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
