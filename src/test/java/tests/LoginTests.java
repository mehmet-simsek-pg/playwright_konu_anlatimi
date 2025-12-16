package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTests extends BaseTest {

    @Test
    public void successfullyLogin() {
        LoginPage loginPage = new LoginPage(page);
        ProductsPage productsPage =
        loginPage.loginAs("standard_user", "secret_sauce");

        // Login sonrasi product page acildi mi?
        Assert.assertTrue(productsPage.isOpenPage());
    }

    @Test
    public void failedLogin() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.fillUsername("locked_out_user")
                 .fillPassword("secret_sauce")
                 .clickLoginBtnFail();

        Assert.assertTrue(loginPage.isErrorMessageVisible());

    }
}
