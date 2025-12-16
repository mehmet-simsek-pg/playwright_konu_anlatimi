package pages;

import com.microsoft.playwright.Page;

public class CartPage extends BasePage {

    private final String pageTitle = "[data-test='title']";
    private final String cartItem = "[data-test='inventory-item']";
    private final String removeBtn = "(//button[text()='Remove'])[%d]";
    private final String continueCheckout = "[data-test='continue-shopping']";
    private final String checkout = "[data-test='checkout']";

    public CartPage(Page page) {
        super(page);
    }

    public boolean isOpenPage() {
        return isVisible(pageTitle);
    }

    public int getCartItemCount() {
        return page.locator(cartItem).count();
    }

    public CartPage removeItem(int index) {
         click(String.format(removeBtn, index));
         return this;
    }

    public ProductsPage clickContinue(){
        click(continueCheckout);
        return new ProductsPage(page);
    }

    public CheckoutPage clickCheckout() {
        click(checkout);
        return new CheckoutPage(page);
    }

}
