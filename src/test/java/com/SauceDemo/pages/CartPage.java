package com.SauceDemo.pages;

import com.SauceDemo.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage (WebDriver driver){
        super(driver);
    }

    @FindBy(className = "title")
    private WebElement cartPageTitle;

    @FindBy(className="inventory_item_price")
    private WebElement inventoryItemPrice;

    @FindBy(css=".btn.btn_secondary.btn_small.cart_button")
    private WebElement removeButton;

    @FindBy(className="cart_item")
    private List<WebElement> cartItems;

    @FindBy(css=".btn.btn_secondary.back.btn_medium")
    private WebElement continueShoppingButton;

    @FindBy(css="[data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(css="[data-test='shopping-cart-badge']")
    private List<WebElement> shoppingCartBadge;


    @Step("Get the Cart page title")
    public String isCartPageTitleDisplayed(){
        return cartPageTitle.getText();
    }

    @Step("Get the number of items in the shopping cart")
    public String getShoppingCartItemCount(){
        return shoppingCartBadge.getFirst().getText();
    }

    @Step("Verify that the item price is displayed")
    public boolean isItemPriceDisplayed(){
       return inventoryItemPrice.isDisplayed();
    }

    @Step("Remove the item from the shopping cart")
    public CartPage removeItem(){
        removeButton.click();
        return this;
    }

    @Step("Verify that the cart item is displayed")
    public boolean isCartItemDisplayed() {
        return !cartItems.isEmpty() && cartItems.getFirst().isDisplayed();
    }

    @Step("Click the Continue Shopping button")
    public InventoryPage continueShopping(){
        continueShoppingButton.click();
        return new InventoryPage(driver);

    }

    @Step("Click the Checkout button")
    public CheckoutPage checkout(){
        checkoutButton.click();
        return new CheckoutPage(driver);

    }

}



