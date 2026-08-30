package com.SauceDemo.pages;

import com.SauceDemo.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className="inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className="inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @FindBy(className="inventory_item_price")
    private List<WebElement> inventoryItemPrices;

    @FindBy(css=".btn.btn_primary.btn_small.btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(css=".btn.btn_secondary.btn_small.btn_inventory")
    private List<WebElement> removeFromCartButtons;

    @FindBy(className="shopping_cart_badge")
    private List<WebElement> shoppingCartBadge;

    @FindBy(className="product_sort_container")
    private WebElement productSortDropdown;

    @FindBy(className ="shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(id="react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(id="logout_sidebar_link")
    private WebElement logoutButton;


    @Step("Get the Inventory page title")
    public String getPageTitle(){
        return pageTitle.getText();
    }

    @Step("Get the number of inventory items")
    public int getInventoryItemCount(){
        return inventoryItems.size();
    }

    @Step("Verify that all product names are displayed")
    public boolean areAllProductNamesDisplayed(){

        for (WebElement item : inventoryItemNames) {

            if (!item.isDisplayed()) {
                return false;
            }
        }

        return true;
    }

    @Step("Get all product prices")
    public List<String>  getProductPrices(){

        List<String> productPrices = new ArrayList<>();
        for (WebElement item : inventoryItemPrices) {
            productPrices.add(item.getText());
        }
        return productPrices;
    }

    @Step("Click the first Add to Cart button")
    public InventoryPage clickFirstAddToCartButton(){
        addToCartButtons.getFirst().click();
        return this;
    }

    @Step("Get the shopping cart item count")
    public String getShoppingCartItemCount(){
        return shoppingCartBadge.getFirst().getText();
    }

    @Step("Remove the first product from the shopping cart")
    public InventoryPage removeBackpackButtonFromCart(){
        removeFromCartButtons.getFirst().click();
        return this;
    }

    @Step("Verify that the shopping cart badge is displayed")
    public boolean isShoppingCartBadgeDisplayed(){
        return !shoppingCartBadge.isEmpty()
                && shoppingCartBadge.getFirst().isDisplayed();
    }

    @Step("Add {numberOfProducts} products to the shopping cart")
    public InventoryPage addProductsToCart(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            addToCartButtons.get(i).click();
        }
        return this;
    }

    @Step("Sort products from Z to A")
    public InventoryPage sortProductsFromZToA() {
        Select select = new Select(productSortDropdown);
        select.selectByVisibleText("Name (Z to A)");
        return this;
    }

    @Step("Sort products from A to Z")
    public InventoryPage sortProductsFromAToZ() {
        Select select = new Select(productSortDropdown);
        select.selectByVisibleText("Name (A to Z)");
        return this;
    }

    @Step("Sort products by price from low to high")
    public InventoryPage sortProductsPricesFromLowToHigh() {
        Select select = new Select(productSortDropdown);
        select.selectByVisibleText("Price (low to high)");
        return this;
    }

    @Step("Sort products by price from high to low")
    public InventoryPage sortProductsPricesFromHighToLow() {
        Select select = new Select(productSortDropdown);
        select.selectByVisibleText("Price (high to low)");
        return this;
    }

    @Step("Get all product names")
    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement item : inventoryItemNames) {
            productNames.add(item.getText());
        }
        return productNames;
    }

    @Step("Open the shopping cart")
    public CartPage openShoppingCart(){
        shoppingCartLink.click();
        return new CartPage(driver);
    }

    @Step("Get all product prices as numeric values")
    public List<Double> getProductPriceValues() {
        List<Double> productPrices = new ArrayList<>();

        for (WebElement item : inventoryItemPrices) {
            productPrices.add(
                    Double.parseDouble(
                            item.getText().replace("$", "")
                    )
            );
        }

        return productPrices;
    }

    @Step("Open product: {productName}")
    public ProductDetailsPage openProduct(String productName) {

        for (WebElement product : inventoryItemNames) {
            if (product.getText().equals(productName)) {
                product.click();
                return new ProductDetailsPage(driver);
            }
        }
        throw new RuntimeException("Product not found: " + productName);

    }

    @Step("Logout from the SauceDemo application")
    public LoginPage logout(){
        burgerMenuButton.click();
        logoutButton.click();
        return new LoginPage(driver);
    }

}
