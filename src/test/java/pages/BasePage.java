package pages;

import com.microsoft.playwright.Page;

public class BasePage {

    /**
     * Playwright ta wait kullanmiyoruz. Cünkü kendi icerisinde auto-wait var.
     * */
    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void type(String selector, String text) {
        page.locator(selector).fill(text);
    }

    public void click(String selector) {
        page.locator(selector).click();
    }

    public String getText(String selector) {
        return page.locator(selector).innerText();
    }

    public boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }
}
