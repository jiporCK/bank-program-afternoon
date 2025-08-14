package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoadConfig {

    public static final Properties properties = new Properties();

    public static void loadProperties() {
        try (BufferedReader reader =
                new BufferedReader(new FileReader("config.properties"))) {

            properties.load(reader);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
