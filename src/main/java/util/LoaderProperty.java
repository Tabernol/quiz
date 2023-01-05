package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoaderProperty {

    public static Properties get(String fileName) {
        Properties properties;
        try {
            FileReader fileReader = new FileReader(fileName);
            properties = new Properties();
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
