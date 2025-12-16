package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CartTests extends BaseTest {

    @Test
    public void removeProductFromCart() {

        ProductsPage productsPage = new LoginPage(page)
                .loginAs("standard_user", "secret_sauce");

        Assert.assertTrue(productsPage.isOpenPage());

        // burada kac eleman eklediysek cart icerisine, o sayida eleman silebiliriz.
        productsPage.addProductToCart(2);
        //productsPage.addProductToCart(3); eger bir eleman daha eklersem expected degerim 1 olmali
        CartPage cartPage = productsPage.clickCartIcon();

        Assert.assertTrue(cartPage.isOpenPage());

        cartPage.removeItem(1);

        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }

    @Test
    public void clickContinueBtn() {

        ProductsPage productsPage = new LoginPage(page)
                .loginAs("standard_user", "secret_sauce");

        Assert.assertTrue(productsPage.isOpenPage());

        productsPage.addProductToCart(1);

        CartPage cartPage = productsPage.clickCartIcon();

        Assert.assertTrue(cartPage.isOpenPage());

        ProductsPage backToProductPage = cartPage.clickContinue();

        Assert.assertTrue(backToProductPage.isOpenPage());

    }
}
