package com.SauceDemo.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {

    public  static Properties LoadProperties(String filepath) throws IOException {
        File file=new File(filepath);
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("coluldnt find the file");
        }
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("couldnt read the file");
        }
        inputStream.close();
        return properties;


    }
}
