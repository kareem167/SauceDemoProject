package com.SauceDemo.utils;

import java.io.IOException;
import java.util.Properties;

public class TestDataUtils {

    private static TestDataUtils testDataUtils;
    private Properties properties;


    private TestDataUtils() throws IOException {

        String testData = System.getProperty("testData", "testdata1").toLowerCase();
        switch (testData){
            case "testdata1":
                properties=PropertiesUtils.LoadProperties("C:\\Users\\dell\\IdeaProjects\\SauceDemoProject1\\src\\test\\java\\com\\SauceDemo\\Config\\testdata1.properties");
                break;
            case "testdata2":
                properties=PropertiesUtils.LoadProperties("C:\\Users\\dell\\IdeaProjects\\SauceDemoProject1\\src\\test\\java\\com\\SauceDemo\\Config\\testdata2.properties");
            default:
                throw new RuntimeException("this test data "+ testData + "doesnt exist" );
        }

    }

    public static TestDataUtils getInstance(){
        if(testDataUtils ==null){
            try {
                testDataUtils =new TestDataUtils();
            } catch (IOException e) {
                throw new RuntimeException("failed to load the test data file");
            }
        }
        return testDataUtils;
    }

    public String getFirstname(){
       String firstname=properties.getProperty("firstname");
        if(firstname !=null){
            return firstname;
        }
        throw new RuntimeException("couldnt find the firstname in the file");
    }

    public String getLastname(){
        String lastname=properties.getProperty("lastname");
        if(lastname !=null){
            return lastname;
        }
        throw new RuntimeException("couldnt find the lastname in the file");
    }

    public String getPostal_code(){
       String postal_code= properties.getProperty("postalcode");
        if(postal_code !=null){
            return postal_code;
        }
        throw new RuntimeException("couldnt find the postal_code in the file");
    }

}
