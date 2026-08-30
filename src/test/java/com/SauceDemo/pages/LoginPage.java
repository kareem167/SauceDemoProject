package com.SauceDemo.pages;

import com.SauceDemo.base.BasePage;
import com.SauceDemo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="user-name")
    private WebElement usernameInput;

    @FindBy(id="password")
    private WebElement PasswordInput;

    @FindBy(id="login-button")
    private WebElement Submit;


    @Step("Open the SauceDemo login page")
    public LoginPage load(){

        driver.get(ConfigUtils.getInstance().GetBaseUrl());
        return this;

    }

    @Step("Login with username: {username} , {password}")
    public InventoryPage Login(String username,String password){
        usernameInput.sendKeys(username);
        PasswordInput.sendKeys(password);
        Submit.click();
        return new InventoryPage(driver);

    }

    @Step("Verify that the Login button is displayed")
    public boolean isLoginButtonDisplayed(){
       return Submit.isDisplayed();

    }


}
