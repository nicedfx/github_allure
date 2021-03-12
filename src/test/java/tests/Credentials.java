package tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Credentials {

    private String readFile() {
        Path path = Paths.get("src/test/resources/credentials.txt");
        try {
            String creds = Files.readAllLines(path).get(0);
            return creds;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserName() {
        String creds = readFile();
        Integer commaPosition = creds.indexOf(",", 0);
        String userName = creds.substring(0,commaPosition);
        return userName;
    }

    public String getPassword() {
        String creds = readFile();
        Integer commaPosition = creds.indexOf(",", 0);
        String userName = creds.substring(commaPosition + 1);
        return userName;
    }
}