package org.assignment.crypto.common;

import org.assignment.crypto.App;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;


public class CommonUtils {

    private static final String APP_PROPERTIES = "app.properties";

    public static String getParameterValue(String[] args, String paramKey) {
        try {
            return Arrays.stream(args).filter(a -> a.contains(paramKey)).findFirst().get().split("=")[1];
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Map<String,String> readPortfolioFile(String filePath){
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            return lines.stream().collect(Collectors.toMap(
                l -> l.split("=")[0],
                l -> l.split("=")[1])
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String readProperty(String key) {
        Properties prop = new Properties();
        try {
            prop.load(App.class.getClassLoader().getResourceAsStream(APP_PROPERTIES));
            return prop.getProperty(key);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
