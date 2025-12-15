import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LocatorExample {
    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()){
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(300)
            );


            Page page = browser.newPage();

            page.navigate("https://www.saucedemo.com/");

            /**
             * ID ile locator
             * page.locator("#user-name"); -> CSS ve id ile locator bulma
             * Her zaman id olmayabilir
             * */

            /**
             * page.locator(".input_error form_input") -> class ile locator bulma
             * class isimlerini css icin kullaniyor frontend devler
             * o yüzden sürekli degisir. bizim icin kullanimi avantaj saglamaz
             * */

            /**
             * Attribute ile locator bulma
             * page.locator("[data-test='login-button']")
             * page.locator("name='login-button'")
             * page.locator("id='login-button'")
             *
             * her kullanimda sonuc ayni olur, hepsi attribute ile locator
             * bulmaya örnektir.
             * */

            /**
             * Text ile locator bulma
             *
             * page.locator("text=Login")
             * page.getByText("Login")
             *
             * Eger sayfada dil degisirse bu locator kullanilamaz hale gelir
             * */

            /**
             * Role ile locator bulma
             * Bu yapiyi kullanmakta bir sorun yok.
             * page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"))
             * */

            /**
             * Placeholder ile locator bulma
             *
             * page.getByPlaceholder("Username")
             * page.getByPlaceholder("Password")
             * */

            /**
             * Form yapilarinda Label ile locator bulma
             * <label>Username</label>
             *
             * page.getByLabel("Username")
             **/

            /**
             * Test id ile locator bulma
             * page.getByTestId("login-button")
             * */

            /**
             * CSS (Parent-child)
             * page.locator("form#login-form input#user-name")
             * */

            /**
             * nth() kullanarak locator bulma
             * page.locator("input") -> tag ismiyle aratirsam sayfadaki
             * tüm input alanlarinin locator i gelir
             *
             * page.locator("input").nth(0)
             * page.locator().first()
             * page.locator().last()
             *
             * */

            /**
             * Filtreleme ile locator bulma
             *
             * page.locator("button").filter(
             *   new Locator.FilterOptions().setHasText("Login"))
             *
             * karmasik DOM yapisinda bu kullanim büyük avantaj saglar
             * */

            /**
             * Son care XPath
             * page.locator("//input[@id='user-name']")
             * */

            /**
             * Locator secerken kullanilacak sira
             * getByTestId -> getByRole -> CSS -> XPath
             * */
        }
    }
}
