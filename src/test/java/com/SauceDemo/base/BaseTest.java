package com.SauceDemo.base;

import com.SauceDemo.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileWriter;


public class BaseTest {

    protected ThreadLocal<WebDriver> driver=new ThreadLocal<>();


    public void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    public WebDriver getDriver(){
        return this.driver.get();
    }

    @BeforeMethod
    public void setUp(){
        WebDriver  driver=new DriverFactory().initializerDriver();
        setDriver(driver);

        createAllureEnvironment();
    }

    @AfterMethod
    public  void tearDown(ITestResult result){
        String testCaseName=result.getMethod().getMethodName();
        File destFile=new File("target"+File.separator+"screenshots"+File.separator+testCaseName+".png");
        if (!result.isSuccess()) {
            takeScreenshot(destFile);
        }
        getDriver().quit();
    }

    public void takeScreenshot(File destFile){
      File file= ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,destFile);
            InputStream is=new FileInputStream(destFile);
            Allure.addAttachment("screenshot",is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createAllureEnvironment() {
        String browser = System.getProperty("browser", "Chrome");

        File allureResultsDirectory = new File("allure-results");

        if (!allureResultsDirectory.exists()) {
            allureResultsDirectory.mkdirs();
        }

        File environmentFile =
                new File(allureResultsDirectory, "environment.properties");

        try (FileWriter writer = new FileWriter(environmentFile)) {

            writer.write("Browser=" + browser + System.lineSeparator());
            writer.write("Operating System=" + System.getProperty("os.name")
                    + System.lineSeparator());
            writer.write("Java Version=" + System.getProperty("java.version")
                    + System.lineSeparator());
            writer.write("Environment=QA" + System.lineSeparator());
            writer.write("Application=SauceDemo" + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Could not create Allure environment file", e);
        }
    }
}
