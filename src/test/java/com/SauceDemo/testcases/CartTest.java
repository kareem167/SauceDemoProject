package com.SauceDemo.testcases;

import com.SauceDemo.base.BaseTest;
import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.utils.ConfigUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Shopping Cart")
public class CartTest extends BaseTest {

    @Story("View shopping cart page")
    @Description("Verify that the shopping cart page displays the correct page title when the user opens the cart.")
    @Test(description = "Verify the shopping cart page title")
    public void shouldDisplayCartPageTitle(){
        LoginPage loginPage=new LoginPage(getDriver());
     String isCartPageTitleDisplayed=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openShoppingCart()
                .isCartPageTitleDisplayed();

     Assert.assertEquals(isCartPageTitleDisplayed,"Your Cart","Cart page title should be 'Your Cart'");
    }


    @Story("View shopping cart items")
    @Description("Verify that the shopping cart displays the correct number of products after multiple products are added to the cart.")
    @Test(description = "Verify the number of items in the shopping cart")
    public void shouldDisplayCorrectNumberOfItemsInCart(){
        LoginPage loginPage=new LoginPage(getDriver());
      String cartItemCount = loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .addProductsToCart(3)
                .openShoppingCart()
                .getShoppingCartItemCount();

      Assert.assertEquals(cartItemCount,"3","Cart should contain "+ cartItemCount+ " items");
    }

    @Story("View product price in shopping cart")
    @Description("Verify that the product price is displayed correctly when a product is added to the shopping cart.")
    @Test(description = "Verify that the product price is displayed in the cart")
    public void shouldDisplayItemPriceInCart(){

       LoginPage loginPage=new LoginPage(getDriver());
       boolean isItemPriceDisplayed=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
               .isItemPriceDisplayed();

        Assert.assertTrue(isItemPriceDisplayed,"Item price should be displayed in the cart");
    }

    @Story("Remove item from shopping cart")
    @Description("Verify that a product is successfully removed from the shopping cart when the user clicks the Remove button.")
    @Test(description = "Verify that an item can be removed from the shopping cart")
    public void shouldRemoveItemFromCart(){
        LoginPage loginPage=new LoginPage(getDriver());
        boolean isCartItemsDispayed=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .clickFirstAddToCartButton()
                .openShoppingCart()
                .removeItem()
                .isCartItemDisplayed();

        Assert.assertFalse(isCartItemsDispayed,"Cart item should not be displayed after removal");

    }

    @Story("Continue shopping from cart")
    @Description("Verify that clicking the Continue Shopping button navigates the user from the shopping cart back to the Inventory page.")
    @Test(description = "Verify navigation back to the Inventory page")
    public void shouldReturnToInventoryPageWhenContinueShoppingIsClicked(){
        LoginPage loginPage=new LoginPage(getDriver());
      String isMainLogoDisplayed=loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .openShoppingCart()
                .continueShopping()
              .getPageTitle();

      Assert.assertEquals(isMainLogoDisplayed,"Products","Inventory page title should be 'Products' after clicking Continue Shopping");
    }

    @Story("Navigate from cart to checkout")
    @Description("Verify that clicking the Checkout button from the shopping cart navigates the user to the Checkout: Your Information page.")
    @Test(description = "Verify navigation to the Checkout page")
    public void shouldNavigateToCheckoutPage(){
        LoginPage loginPage=new LoginPage(getDriver());
       String CheckoutPageTitle =loginPage
                .load()
                .Login(ConfigUtils.getInstance().GetUserName(),ConfigUtils.getInstance().GetPassword())
                .addProductsToCart(1)
                .openShoppingCart()
                .checkout()
                .getPageTitle();

       Assert.assertEquals(CheckoutPageTitle,"Checkout: Your Information","Checkout page title should be 'Checkout: Your Information'");

    }
}
