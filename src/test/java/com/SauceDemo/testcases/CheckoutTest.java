package com.SauceDemo.testcases;

import com.SauceDemo.base.BaseTest;
import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.utils.ConfigUtils;
import com.SauceDemo.utils.PdfUtils;
import com.SauceDemo.utils.TestDataUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Feature("Checkout")
public class CheckoutTest extends BaseTest {

    @Story("Continue to checkout overview")
    @Description("Verify that the user can enter valid checkout information and continue to the Checkout Overview page.")
    @Test(description = "Verify navigation to the Checkout Overview page")
    public void shouldContinueToCheckoutOverview(){
        LoginPage loginPage=new LoginPage(getDriver());
      String overviewPageTitle= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .getPageTitle();

        Assert.assertEquals(overviewPageTitle,"Checkout: Overview","Checkout overview page title should be 'Checkout: Overview'");
    }

    @Story("Cancel checkout")
    @Description("Verify that clicking the Cancel button during checkout returns the user to the shopping cart page.")
    @Test(description = "Verify navigation back to the cart when Cancel is clicked")
    public void shouldReturnToCartPageWhenCancelIsClicked(){
        LoginPage loginPage=new LoginPage(getDriver());
        String cartPageTitle= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .clickCancelButton()
                .isCartPageTitleDisplayed();

        Assert.assertEquals(cartPageTitle,"Your Cart","Cart page title should be 'Your Cart' after clicking Cancel");
    }

    @Story("View items in checkout overview")
    @Description("Verify that the selected product is displayed on the Checkout Overview page before completing the order.")
    @Test(description = "Verify that the cart item is displayed in checkout overview")
    public void shouldDisplayItemsInCheckoutOverview(){
        LoginPage loginPage=new LoginPage(getDriver());
        boolean isItemDisplayedInCart= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .isCartItemDisplayed();

        Assert.assertTrue(isItemDisplayedInCart,"Cart item should be displayed on the checkout overview page");
    }

    @Story("View total price in checkout overview")
    @Description("Verify that the total price is displayed on the Checkout Overview page before completing the order.")
    @Test(description = "Verify that the total price is displayed in checkout overview")
    public void shouldDisplayTotalPriceInCheckoutOverview(){
        LoginPage loginPage=new LoginPage(getDriver());
        boolean isTotalPriceDisplayedInCart= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .isTotalPriceDisplayed();

        Assert.assertTrue(isTotalPriceDisplayedInCart,"Total price should be displayed on the checkout overview page");
    }

    @Story("View payment information")
    @Description("Verify that payment information is displayed on the Checkout Overview page before completing the order.")
    @Test(description = "Verify that payment information is displayed")
    public void shouldDisplayPaymentInformation(){
        LoginPage loginPage=new LoginPage(getDriver());
        boolean isPaymentInformationDisplayedInCart= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .isPaymentInformationDisplayed();

        Assert.assertTrue(isPaymentInformationDisplayedInCart,"Payment information should be displayed on the checkout overview page");
    }

    @Story("Complete checkout")
    @Description("Verify that the user can successfully complete the checkout process and receives the order confirmation message.")
    @Test(description = "Verify successful checkout completion")
    public void shouldCompleteCheckoutSuccessfully(){
        LoginPage loginPage=new LoginPage(getDriver());
        String thankYouText= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .getCompleteHeader();

        Assert.assertEquals(thankYouText,"Thank you for your order!","Order confirmation message should be 'Thank you for your order!'");
    }

    @Story("Return to inventory after checkout")
    @Description("Verify that clicking the Back to Products button after completing an order returns the user to the Inventory page.")
    @Test(description = "Verify navigation back to the Inventory page")
    public void shouldReturnToInventoryPageWhenBackToProductsIsClicked(){
        LoginPage loginPage=new LoginPage(getDriver());
        String inventoryPageTitle= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .clickBackToProductsButton()
                .getPageTitle();

        Assert.assertEquals(inventoryPageTitle,"Products","Inventory page title should be 'Products' after clicking Back to Products");
    }

    @Story("Generate order PDF")
    @Description("Verify that an order confirmation PDF can be generated successfully after completing the checkout process.")
    @Test(description = "Verify that the order PDF is downloaded")
    public void shouldGeneratePdf(){
        LoginPage loginPage=new LoginPage(getDriver());
        loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .clickGeneratePdfButton();

        String downloadPath =
                System.getProperty("user.dir") + "\\downloads";

        Assert.assertTrue(PdfUtils.isPdfDownloaded(downloadPath),"PDF should be downloaded");
    }

    @Story("Generate non-empty order PDF")
    @Description("Verify that the generated order PDF contains data and is not empty.")
    @Test(description = "Verify that the generated PDF is not empty")
    public void shouldGenerateNonEmptyPdf() {
        LoginPage loginPage=new LoginPage(getDriver());
        loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .clickGeneratePdfButton();

        Assert.assertTrue(PdfUtils.isPdfNotEmpty("downloads"),"Generated PDF should not be empty");
    }

    @Story("Validate generated PDF file")
    @Description("Verify that the generated order document is downloaded as a PDF file.")
    @Test(description = "Verify that the downloaded file is a PDF")
    public void shouldGeneratePdfFile() {
        LoginPage loginPage=new LoginPage(getDriver());
        loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .clickGeneratePdfButton();

        String downloadPath =
                System.getProperty("user.dir") + "\\downloads";

        Assert.assertTrue(PdfUtils.isPdfFile(downloadPath)," Downloaded file should be a PDF");
    }

    @Story("Validate customer first name in PDF")
    @Description("Verify that the generated order PDF contains the customer's first name entered during checkout.")
    @Test(description = "Verify that the first name is included in the generated PDF")
    public void shouldIncludeFirstNameInGeneratedPdf() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .clickGeneratePdfButton();

        String downloadPath =
                System.getProperty("user.dir") + "\\downloads";

        String pdfText = PdfUtils.getPdfText(downloadPath);

        Assert.assertTrue(pdfText.contains(TestDataUtils.getInstance().getFirstname()),
                "PDF should contain the first name");
    }

    @Story("Validate customer last name in PDF")
    @Description("Verify that the generated order PDF contains the customer's last name entered during checkout.")
    @Test(description = "Verify that the last name is included in the generated PDF")
    public void shouldIncludeLastNameInGeneratedPdf() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .clickGeneratePdfButton();


        String downloadPath =
                System.getProperty("user.dir") + "\\downloads";

        String pdfText = PdfUtils.getPdfText(downloadPath);

        Assert.assertTrue(pdfText.contains(TestDataUtils.getInstance().getLastname()),
                "Generated PDF should contain the customer's last name" );

    }

    @Story("Validate postal code in PDF")
    @Description("Verify that the generated order PDF contains the postal code entered during checkout.")
    @Test(description = "Verify that the postal code is included in the generated PDF")
    public void shouldBeAbleToGetPostalCodeFromThePdf() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .checkout()
                .enterCheckoutInformation(TestDataUtils.getInstance().getFirstname(), TestDataUtils.getInstance().getLastname(), TestDataUtils.getInstance().getPostal_code())
                .clickFinishButton()
                .clickGeneratePdfButton();

       String downloadPath =
                System.getProperty("user.dir") + "\\downloads";

        String pdfText = PdfUtils.getPdfText(downloadPath);

        Assert.assertTrue(pdfText.contains(TestDataUtils.getInstance().getPostal_code()),
                "PDF should contain the postal code");
    }



}
