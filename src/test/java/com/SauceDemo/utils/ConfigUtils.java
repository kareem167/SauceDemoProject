package com.SauceDemo.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private Properties properties;
    private static ConfigUtils configUtils;

    private ConfigUtils() throws IOException {
        String envuser=System.getProperty("envuser","standard_user").toUpperCase();
        switch (envuser){
            case "STANDARD_USER":
                properties=PropertiesUtils.LoadProperties("C:\\Users\\dell\\IdeaProjects\\SauceDemoProject1\\src\\test\\java\\com\\SauceDemo\\Config\\standard_user.properties");
                break;
            case "PROBLEM_USER":
                properties=PropertiesUtils.LoadProperties("C:\\Users\\dell\\IdeaProjects\\SauceDemoProject1\\src\\test\\java\\com\\SauceDemo\\Config\\problem_user.properties");
                break;
            case "LOCKED_OUT_USER":
                properties=PropertiesUtils.LoadProperties("C:\\Users\\dell\\IdeaProjects\\SauceDemoProject1\\src\\test\\java\\com\\SauceDemo\\Config\\locked_out_user.properties");
                break;
            default:
                throw new RuntimeException("this user "+envuser+" doesnt exist");

        }

    }

    public static ConfigUtils getInstance(){
        if(configUtils ==null){
            try {
                configUtils =new ConfigUtils();
            } catch (IOException e) {
                throw new RuntimeException("falied to load configuration file");
            }
        }
        return configUtils;
    }

    public String GetBaseUrl(){
        String BaseUrl=properties.getProperty("baseurl");
        if(BaseUrl !=null){
            return BaseUrl;
        }
        throw new RuntimeException("couldnt find the baseurl in the file");

    }

    public String GetUserName(){
        String UserName=properties.getProperty("username");
        if(UserName !=null){
            return UserName;
        }
        throw new RuntimeException("couldnt find the username in the file");

    }


    public String GetPassword(){
        String Password=properties.getProperty("password");
        if(Password !=null){
            return Password;
        }
        throw new RuntimeException("couldnt find the password in the file");

    }
}
