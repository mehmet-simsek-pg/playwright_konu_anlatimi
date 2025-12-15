import com.microsoft.playwright.*;

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

            // browser i kapattik
            browser.close();
        }
    }
}
