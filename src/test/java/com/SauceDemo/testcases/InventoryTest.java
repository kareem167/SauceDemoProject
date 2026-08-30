package com.SauceDemo.testcases;

import com.SauceDemo.base.BaseTest;
import com.SauceDemo.pages.InventoryPage;
import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.utils.ConfigUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Feature("Inventory")
public class InventoryTest extends BaseTest {

    @Story("Display inventory products")
    @Description("Verify that the Inventory page displays exactly six products.")
    @Test(description = "Verify that six products are displayed")
    public void shouldDisplaySixInventoryItems(){
        LoginPage loginPage=new LoginPage(getDriver());
       int itemCount= loginPage.load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .getInventoryItemCount();
        Assert.assertEquals(itemCount,6,"Inventory should contain 6 products");
    }

    @Story("Display product names")
    @Description("Verify that all product names are displayed correctly on the Inventory page.")
    @Test(description = "Verify that all product names are displayed")
    public void shouldDisplayAllProductNames(){
        LoginPage loginPage=new LoginPage(getDriver());
       boolean isProductNamesDisplayed=loginPage.load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .areAllProductNamesDisplayed();
       Assert.assertTrue(isProductNamesDisplayed,"All product names should be displayed");

    }

    @Story("Display product prices")
    @Description("Verify that all product prices are displayed with the dollar currency symbol.")
    @Test(description = "Verify that all product prices use dollar currency")
    public void shouldDisplayAllProductPricesWithDollarCurrency() {
        LoginPage loginPage=new LoginPage(getDriver());
        List<String> actualProductPrices = loginPage.load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .getProductPrices();
        for (String price : actualProductPrices) {
            Assert.assertTrue(
                    price.startsWith("$"),
                    "Price does not start with $: " + price
            );
        }
    }

    @Story("Add product to cart")
    @Description("Verify that a product can be added to the shopping cart and that the cart item count is updated.")
    @Test(description = "Verify that a product can be added to the cart")
    public void shouldAddProductToCart(){
        LoginPage loginPage = new LoginPage(getDriver());
        String itemCount=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .getShoppingCartItemCount();

        Assert.assertEquals(itemCount,"1","Shopping cart should contain 1 product");
    }

    @Story("Remove product from cart")
    @Description("Verify that a product can be removed from the shopping cart and that the shopping cart badge is no longer displayed.")
    @Test(description = "Verify that a product can be removed from the cart")
    public void shouldRemoveProductFromCart(){
        LoginPage loginPage = new LoginPage(getDriver());
        boolean isShoppingBadgeDisplayed=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .removeBackpackButtonFromCart()
                .isShoppingCartBadgeDisplayed();

        Assert.assertFalse(isShoppingBadgeDisplayed,"Shopping cart badge should not be displayed after removing the product");
    }

    @Story("Add multiple products to cart")
    @Description("Verify that multiple products can be added to the shopping cart and that the cart item count matches the number of products added.")
    @Test(description = "Verify that multiple products can be added to the cart")
    public void shouldAddMultipleProductsToCart(){
        int numberOfProductsToTest=3;
      LoginPage loginPage = new LoginPage(getDriver());
        String cartItemCount=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .addProductsToCart(numberOfProductsToTest)
                .getShoppingCartItemCount();

        Assert.assertEquals(cartItemCount,String.valueOf(numberOfProductsToTest),"Shopping cart should contain 3 products");

    }

    @Story("Sort products by name in descending order")
    @Description("Verify that products are sorted correctly from Z to A when the Name (Z to A) sorting option is selected.")
    @Test(description = "Verify product sorting from Z to A")
    public void shouldSortProductsFromZToA(){
       LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword());


        List<String> originalProductNames = inventoryPage.getProductNames();

        List<String> actualProductNames =inventoryPage.sortProductsFromZToA()
                .getProductNames();

        List<String> expectedProductNames = new ArrayList<>(originalProductNames);
        expectedProductNames.sort(Comparator.reverseOrder());

      Assert.assertEquals(actualProductNames,expectedProductNames,"Products should be sorted alphabetically from Z to A");

    }

    @Story("Sort products by name in ascending order")
    @Description("Verify that products are sorted correctly from A to Z when the Name (A to Z) sorting option is selected.")
    @Test(description = "Verify product sorting from A to Z")
    public void shouldSortProductsFromAToZ() {
        LoginPage loginPage = new LoginPage(getDriver());

        InventoryPage inventoryPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword());

        List<String> originalProductNames = inventoryPage.getProductNames();

        List<String> actualProductNames = inventoryPage
                .sortProductsFromAToZ()
                .getProductNames();

        List<String> expectedProductNames = new ArrayList<>(originalProductNames);
        expectedProductNames.sort(Comparator.naturalOrder());

        Assert.assertEquals(actualProductNames, expectedProductNames,"Products should be sorted alphabetically from A to Z");
    }

    @Story("Sort products by price from low to high")
    @Description("Verify that products are sorted correctly by price in ascending order when the Price (low to high) option is selected.")
    @Test(description = "Verify product sorting by price from low to high")
    public void shouldSortProductsByPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(getDriver());

        InventoryPage inventoryPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword());

        List<Double> originalProductPrices = inventoryPage.getProductPriceValues();

        List<Double> actualProductPrices = inventoryPage
                .sortProductsPricesFromLowToHigh()
                .getProductPriceValues();

        List<Double> expectedProductPrices = new ArrayList<>(originalProductPrices);
        expectedProductPrices.sort(Comparator.naturalOrder());

        Assert.assertEquals(actualProductPrices,expectedProductPrices,"Products should be sorted by price from low to high");
    }

    @Story("Sort products by price from high to low")
    @Description("Verify that products are sorted correctly by price in descending order when the Price (high to low) option is selected.")
    @Test(description = "Verify product sorting by price from high to low")
    public void shouldSortProductsByPriceHighToLow() {
        LoginPage loginPage = new LoginPage(getDriver());

        InventoryPage inventoryPage = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword());

        List<Double> originalProductPrices = inventoryPage.getProductPriceValues();

        List<Double> actualProductPrices = inventoryPage
                .sortProductsPricesFromHighToLow()
                .getProductPriceValues();

        List<Double> expectedProductPrices = new ArrayList<>(originalProductPrices);
        expectedProductPrices.sort(Comparator.reverseOrder());

        Assert.assertEquals(actualProductPrices,expectedProductPrices,"Products should be sorted by price from high to low");
    }

    @Story("Navigate to shopping cart")
    @Description("Verify that the user can navigate from the Inventory page to the shopping cart page by clicking the shopping cart icon.")
    @Test(description = "Verify navigation to the shopping cart page")
    public void shouldBeAbleToEnterToCartPage(){
       LoginPage loginPage=new LoginPage(getDriver());
       String isCartPageTitleDisplayed = loginPage
               .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openShoppingCart()
                .isCartPageTitleDisplayed();

       Assert.assertEquals(isCartPageTitleDisplayed,"Your Cart","the cart page title is not displayed");


    }

    @Story("Logout from application")
    @Description("Verify that the user can successfully log out of the application and is returned to the Login page.")
    @Test(description = "Verify successful logout")
    public void shouldBeAbleToLogout(){
        LoginPage loginPage=new LoginPage(getDriver());
       boolean isLoginButtonDisplayed= loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(), ConfigUtils.getInstance().GetPassword())
                .logout()
                .isLoginButtonDisplayed();

       Assert.assertTrue(isLoginButtonDisplayed,"should be able to logout");
    }


}
