package com.SauceDemo.pages;

import com.SauceDemo.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className="cart_item")
    private WebElement cartItem;

    @FindBy(id="first-name")
    private WebElement firstNameInput;

    @FindBy(id="last-name")
    private WebElement lastNameInput;

    @FindBy(id="postal-code")
    private WebElement postCodeInput;

    @FindBy(id="continue")
    private WebElement continueCheckoutButton;

    @FindBy(id="cancel")
    private WebElement cancelButton;

    @FindBy(css="[data-test='total-label']")
    private WebElement totalPrice;

    @FindBy(css="[data-test=payment-info-value]")
    private WebElement paymentInformation;

    @FindBy(id="finish")
    private WebElement finishButton;

    @FindBy(css="[data-test='complete-header']")
    private WebElement completeHeader;

    @FindBy(id="back-to-products")
    private WebElement backToProductButton;

    @FindBy(id="generate-pdf-order")
    private WebElement generatePdfButton;

    @Step("Get the Checkout page title")
    public String getPageTitle(){
       return pageTitle.getText();
    }

    @Step("Enter checkout information and continue")
    public CheckoutPage enterCheckoutInformation(String firstName, String lastName, String postalCode){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postalCode);
        continueCheckoutButton.click();
        return this;
    }

    @Step("Click the Cancel button")
    public CartPage clickCancelButton(){
        cancelButton.click();
        return new CartPage(driver);
    }

    @Step("Verify that the cart item is displayed")
    public boolean isCartItemDisplayed(){
        return cartItem.isDisplayed();
    }

    @Step("Verify that the total price is displayed")
    public boolean isTotalPriceDisplayed(){
       return totalPrice.isDisplayed();
    }

    @Step("Verify that the payment information is displayed")
    public boolean isPaymentInformationDisplayed(){
        return paymentInformation.isDisplayed();
    }

    @Step("Click the Finish button")
    public CheckoutPage clickFinishButton(){
        finishButton.click();
        return this;
    }

    @Step("Get the order completion header")
    public String getCompleteHeader(){
        return completeHeader.getText();
    }

    @Step("Click the Back to Products button")
    public InventoryPage clickBackToProductsButton(){
        backToProductButton.click();
        return new InventoryPage(driver);
    }

    @Step("Click the Generate PDF Order button")
    public void clickGeneratePdfButton(){
        generatePdfButton.click();
    }
}
