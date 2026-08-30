package com.SauceDemo.pages;

import com.SauceDemo.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {


    public ProductDetailsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(className = "inventory_details_name")
    private WebElement productName;

    @FindBy(className = "inventory_details_desc")
    private WebElement productDescription;

    @FindBy(className = "inventory_details_price")
    private WebElement productPrice;

    @FindBy(css = ".inventory_details_img")
    private WebElement productImage;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    private WebElement addToCartButton;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    private WebElement removeButton;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;


    @Step("Get the product name")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Verify that the product description is displayed")
    public boolean isProductDescriptionDisplayed() {
        return productDescription.isDisplayed();
    }

    @Step("Verify that the product price is displayed")
    public boolean isProductPriceDisplayed() {
        return productPrice.isDisplayed();
    }

    @Step("Verify that the product image is displayed")
    public boolean isProductImageDisplayed() {
        return productImage.isDisplayed();
    }

    @Step("Verify that the Add to Cart button is displayed")
    public boolean isAddToCartButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }

    @Step("Click the Add to Cart button")
    public ProductDetailsPage clickAddToCartButton() {
        addToCartButton.click(); return this;
    }

    @Step("Verify that the Remove button is displayed")
    public boolean isRemoveButtonDisplayed() {
        return removeButton.isDisplayed();
    }

    @Step("Click the Remove button")
    public ProductDetailsPage clickRemoveButton() {
        removeButton.click(); return this;
    }

    @Step("Click the Back to Products button")
    public InventoryPage clickBackToProductsButton() {
        backToProductsButton.click(); return new InventoryPage(driver);
    }

}
