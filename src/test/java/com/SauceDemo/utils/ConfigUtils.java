package com.SauceDemo.utils;

import java.io.IOException;
import java.util.Properties;
import java.io.File;

public class ConfigUtils {

    private Properties properties;
    private static ConfigUtils configUtils;

    private ConfigUtils() throws IOException {
        String envuser=System.getProperty("envuser","standard_user").toUpperCase();

        String configDirectory = System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "test"
                + File.separator
                + "java"
                + File.separator
                + "com"
                + File.separator
                + "SauceDemo"
                + File.separator
                + "config"
                + File.separator;

        switch (envuser){
            case "STANDARD_USER":
                properties=PropertiesUtils.LoadProperties(configDirectory+"standard_user.properties");
                break;
            case "PROBLEM_USER":
                properties=PropertiesUtils.LoadProperties(configDirectory+"problem_user.properties");
                break;
            case "LOCKED_OUT_USER":
                properties=PropertiesUtils.LoadProperties(configDirectory+"locked_out_user.properties");
                break;
            case "PERFORMANCE_GLITCH_USER":
                properties=PropertiesUtils.LoadProperties(configDirectory+"performance_glitch_user.properties");
                break;
            case "ERROR_USER":
                properties=PropertiesUtils.LoadProperties(configDirectory+"error_user.properties");
                break;
            case "VISUAL_USER":
                properties=PropertiesUtils.LoadProperties(configDirectory+"visual_user.properties");
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
