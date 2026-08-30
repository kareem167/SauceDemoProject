package com.SauceDemo.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {


    public WebDriver initializerDriver(){
        String browser=System.getProperty("browser","Chrome").toUpperCase();
        boolean isGitHub=System.getenv("GITHUB_ACTIONS") !=null;
        String downloadPath =
                System.getProperty("user.dir") + "\\downloads";

        File downloadDirectory = new File(downloadPath);

        if (!downloadDirectory.exists()) {
            downloadDirectory.mkdirs();
        }

        WebDriver driver;
        switch (browser){
            case "CHROME":
                ChromeOptions chromeOptions=new ChromeOptions();

                Map<String, Object> chromePrefs = new HashMap<>();

                chromePrefs.put(
                        "download.default_directory",
                        downloadPath
                );

                chromePrefs.put(
                        "download.prompt_for_download",
                        false
                );

                chromePrefs.put(
                        "plugins.always_open_pdf_externally",
                        true
                );

                chromePrefs.put(
                        "credentials_enable_service",
                        false
                );

                chromePrefs.put(
                        "profile.password_manager_leak_detection",
                        false
                );

                chromeOptions.setExperimentalOption(
                        "prefs",
                        chromePrefs
                );

                if(isGitHub){
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-shm-usage");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                driver=new ChromeDriver(chromeOptions);
                break;
            case "FIREFOX":
                FirefoxProfile firefoxProfile = new FirefoxProfile();

                firefoxProfile.setPreference(
                        "browser.download.folderList",
                        2
                );

                firefoxProfile.setPreference(
                        "browser.download.dir",
                        downloadPath
                );

                firefoxProfile.setPreference(
                        "browser.helperApps.neverAsk.saveToDisk",
                        "application/pdf"
                );

                firefoxProfile.setPreference(
                        "pdfjs.disabled",
                        true
                );

                firefoxProfile.setPreference(
                        "signon.rememberSignons",
                        false
                );

                firefoxProfile.setPreference(
                        "signon.management.page.breachAlertEnabled",
                        false
                );

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.setProfile(firefoxProfile);

                if (isGitHub){
                    firefoxOptions.addArguments("--headless=new");
                }
                driver=new FirefoxDriver(firefoxOptions);
                break;
            case "EDGE":
                EdgeOptions edgeOptions=new EdgeOptions();

                Map<String, Object> edgePrefs = new HashMap<>();

                edgePrefs.put(
                        "download.default_directory",
                        downloadPath
                );

                edgePrefs.put(
                        "download.prompt_for_download",
                        false
                );

                edgePrefs.put(
                        "plugins.always_open_pdf_externally",
                        true
                );

                edgePrefs.put(
                        "credentials_enable_service",
                        false
                );

                edgePrefs.put(
                        "profile.password_manager_leak_detection",
                        false
                );

                edgeOptions.setExperimentalOption(
                        "prefs",
                        edgePrefs
                );

                if (isGitHub){
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-shm-usage");
                    edgeOptions.addArguments("--window-size=1920,1080");
                }
                driver=new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Browser "+browser+" is not supported");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(27));
        if (!isGitHub) {
            driver.manage().window().maximize();

        }
        return driver;
    }
}
