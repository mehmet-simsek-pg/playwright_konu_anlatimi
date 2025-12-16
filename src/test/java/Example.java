import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class Example {
    public static void main(String[] args) {

        // Playwright baslatildi
        try (Playwright playwright = Playwright.create()){

            // Browser baslatiliyor
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(500)
            );

            // Yeni tab olusturmak, yani yeni sekme acilmasi icin kullanilir. Zorunlu degildir
            BrowserContext browserContext = browser.newContext();
            // Page page = browser.newPage(); bu sekilde de kullanilabilir
            Page page = browserContext.newPage();

            // burada siteyi actik
            page.navigate("https://www.saucedemo.com/");
            System.out.println(page.title());
            //page.pause(); // inspector u baslatmak icin kullandik

            // Placeholder ile kullanim sekli
            // input alani icerisinde kullanicinin gördügü kisim
            Locator username = page.getByPlaceholder("Username");
            username.fill("standard_user");

            Locator password = page.getByPlaceholder("Password");
            password.fill("secret_sauce");

            // Role ile kullanim
            Locator loginBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
            loginBtn.click();
            page.pause();

            // browser i kapattik
            browser.close();
        }
    }
}
