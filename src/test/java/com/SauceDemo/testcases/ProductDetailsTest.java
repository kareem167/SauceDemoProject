package com.SauceDemo.testcases;

import com.SauceDemo.base.BaseTest;
import com.SauceDemo.pages.InventoryPage;
import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.pages.ProductDetailsPage;
import com.SauceDemo.utils.ConfigUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Product Details")
public class ProductDetailsTest extends BaseTest {

    @Story("Display product name")
    @Description("Verify that the correct product name is displayed on the product details page.")
    @Test(description = "Verify the product name")
    public void shouldDisplayCorrectProductName() {
        LoginPage loginPage = new LoginPage(getDriver());
        
        ProductDetailsPage productDetailsPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack");

        Assert.assertEquals(productDetailsPage.getProductName(),"Sauce Labs Backpack","Correct product name should be displayed");
    }

    @Story("Display product description")
    @Description("Verify that the product description is displayed on the product details page.")
    @Test(description = "Verify the product description is displayed")
    public void shouldDisplayProductDescription() {
        LoginPage loginPage = new LoginPage(getDriver());

        ProductDetailsPage productDetailsPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack");

        Assert.assertTrue(productDetailsPage.isProductDescriptionDisplayed(),"Product description should be displayed");

    }

    @Story("Display product price")
    @Description("Verify that the product price is displayed on the product details page.")
    @Test(description = "Verify the product price is displayed")
    public void shouldDisplayProductPrice() {
        LoginPage loginPage = new LoginPage(getDriver());

        ProductDetailsPage productDetailsPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack");

        Assert.assertTrue(productDetailsPage.isProductPriceDisplayed(),"Product price should be displayed");

    }

    @Story("Display product image")
    @Description("Verify that the product image is displayed on the product details page.")
    @Test(description = "Verify the product image is displayed")
    public void shouldDisplayProductImage() {
        LoginPage loginPage = new LoginPage(getDriver());

        ProductDetailsPage productDetailsPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack");

        Assert.assertTrue(productDetailsPage.isProductImageDisplayed(),"Product image should be displayed");


    }

    @Story("Display Add to Cart button")
    @Description("Verify that the Add to Cart button is displayed on the product details page.")
    @Test(description = "Verify the Add to Cart button is displayed")
    public void shouldDisplayAddToCartButton() {
        LoginPage loginPage = new LoginPage(getDriver());

        ProductDetailsPage productDetailsPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack");

        Assert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed(),"Add to Cart button should be displayed");

    }

    @Story("Add product to cart")
    @Description("Verify that a product can be added to the shopping cart from the product details page and that the Remove button is displayed afterward.")
    @Test(description = "Verify that a product can be added to the cart")
    public void shouldAddProductToCart() {
        LoginPage loginPage = new LoginPage(getDriver());

        ProductDetailsPage productDetailsPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack");

        productDetailsPage.clickAddToCartButton();

        Assert.assertTrue(productDetailsPage.isRemoveButtonDisplayed(),"Remove button should be displayed after adding the product");

    }

    @Story("Remove product from cart")
    @Description("Verify that a product can be removed from the shopping cart from the product details page and that the Add to Cart button is displayed afterward.")
    @Test(description = "Verify that a product can be removed from the cart")
    public void shouldRemoveProductFromCart() {
        LoginPage loginPage = new LoginPage(getDriver());

        ProductDetailsPage productDetailsPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack");

        productDetailsPage.clickAddToCartButton();
        productDetailsPage.clickRemoveButton();

        Assert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed(),"Add to Cart button should be displayed after removing the product");

    }

    @Story("Return to products page")
    @Description("Verify that clicking the Back to Products button from the product details page returns the user to the Products page.")
    @Test(description = "Verify navigation back to the Products page")
    public void shouldReturnToProductsPage() {
        LoginPage loginPage = new LoginPage(getDriver());

        InventoryPage inventoryPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openProduct("Sauce Labs Backpack")
                .clickBackToProductsButton();

        Assert.assertEquals(inventoryPage.getPageTitle(),"Products","User should return to the Products page");


    }

}
