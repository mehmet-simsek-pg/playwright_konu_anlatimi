package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductTests extends BaseTest {

    @Test
    public void addProductToCart() {

        ProductsPage productsPage = new LoginPage(page)
                .loginAs("standard_user", "secret_sauce");

        Assert.assertTrue(productsPage.isOpenPage());

        productsPage.addProductToCart(1);
        productsPage.addProductToCart(3);

        Assert.assertEquals(productsPage.getCartBadgeCount(), 2);
    }
}
