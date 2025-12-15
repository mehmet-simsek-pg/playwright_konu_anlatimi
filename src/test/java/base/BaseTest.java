package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;

    protected BrowserContext browserContext;
    protected Page page;

    protected final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
        // Burada playwright i baslatiyoruz
        if (playwright == null) {
            playwright = Playwright.create();
        }

        if (browser == null) {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(500)
            );
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){

        if (browser != null) {
            browser.close();
            browser = null;
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){

        if (browserContext != null) {
            browserContext.close();
            browserContext = null;
        }
    }
}
