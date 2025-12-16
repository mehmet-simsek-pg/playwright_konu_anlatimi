package pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // Selectors
    private final String usernameInput = "#user-name";
    private final String passwordInput = "#password";
    private final String loginBtn = "#login-button";
    private final String errorMessage = "[data-test='error']";

    /**
     * Neden return ifadesini sadece click login de kullandik?
     * diger metotlarda hatali ya da dogru giris olup olmamasina bakmadan
     * sayfa da her hangi bir degisiklik olup baska bir sayfaya navigate olmuyor.
     * Ama login butona bastigimda eger islem basarili olursa sayfa(Page) degisiyor.
     * POM yapisi geregi bunu belirtmem gerek, ki devam eden testlerde sayfa(page) ile
     * alakali sorun yasamayalim.
     *
     */
    public LoginPage(Page page) {
        super(page);
    }

    /**
     * Username alanını doldurur.
     * Sayfa değişmediği için LoginPage döndürür.
     */
    public LoginPage fillUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    /**
     * Password alanını doldurur.
     * Sayfa değişmediği için LoginPage döndürür.
     */
    public LoginPage fillPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    /**
     * Login işlemi başarılı olduğunda
     * kullanıcı ProductsPage'e yönlendirilir.
     * POM prensibi gereği, sayfa değiştiği için
     * yeni sayfayı temsil eden Page Object döndürülür.
     */
    public ProductsPage clickLoginBtnSuccess() {
        click(loginBtn);
        return new ProductsPage(page);
    }

    /**
     * Login işlemi başarısız olduğunda
     * aynı sayfada (LoginPage) kalınır.
     * Bu yüzden LoginPage döndürülür.
     */
    public LoginPage clickLoginBtnFail() {
        click(loginBtn);
        return this;
    }

    public ProductsPage loginAs(String username, String password) {

        fillUsername(username);
        fillPassword(password);
        clickLoginBtnSuccess();
        return new ProductsPage(page); // Basarili bir sekilde login olduktan acilan sayfa
        // ProductPage oldugu icin metodun dönüs degeri void olamaz
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorMessageVisible() {
        return isVisible(errorMessage);
    }
}
