package com.SauceDemo.testcases;

import com.SauceDemo.base.BaseTest;
import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.utils.ConfigUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Authentication")
public class LoginTest extends BaseTest {

    @Story("Successful login with valid credentials")
    @Description("Verify that a user can log in successfully with valid username and password and is redirected to the Inventory page.")
    @Test(description = "Verify successful login with valid credentials")
    public void ShouldBeAbleToLogin(){
        LoginPage loginPage=new LoginPage(getDriver());
       String IsMainLogoDisplayed= loginPage
               .load()
               .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
               .getPageTitle();
        Assert.assertEquals(IsMainLogoDisplayed,"Products","couldnt find the main logo");
    }
}
