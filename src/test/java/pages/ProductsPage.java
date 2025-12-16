package pages;

import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage{

    private final String pageTitle = "[data-test='title']";
    private final String inventoryItem = "[data-test='inventory-item']";
    private final String addToCart = "(//button[text()='Add to cart'])[%d]"; // String.format() kullanicaz
    private final String cartItem = "[data-test='shopping-cart-link']";
    private final String cartBadge = "[data-test='shopping-cart-badge']";
    private final String productSortDropDown = "[data-test='product-sort-container']";
    private final String inventoryItemNames = "[data-test='inventory-item-name']";
    private final String inventoryItemPrices = "[data-test='inventory-item-price']";

    public ProductsPage(Page page) {
        super(page);
    }

    public boolean isOpenPage() {
        return isVisible(pageTitle);
    }

    public int getInventoryCount() {
        return page.locator(inventoryItem).count(); // Selenium daki List mantigi
    }

    // dönüs tipi void olsa da olur, ama Playwright mantigindan dolayi sayfada yapilan degisiklik sonunda
    // sayfa degismedigi dönüs tipini ProductsPage olarak tanimladik.
    public ProductsPage addProductToCart(int index){
        click(String.format(addToCart, index));
        // String.format() metodunda -> String olan ifade icerisinde rakamlar icin %d, yazisal degerler icin %s
        // kullanilir. format metodu icerisinde sol tarafa string ifade yazilir, virgülden sonra ise atanmak
        // istenen deger yazilir.
        return this;
    }

    public int getCartBadgeCount(){

        if (!isVisible(cartBadge)) {
            return 0; // Eger cart a ekleme yapilmadiysa deger sifir olur
        }
        return Integer.parseInt(getText(cartBadge)); // ekleme yapildiysa deger getText ten String geldigi icin
        // integer a dönüstürdük
    }

    public ProductsPage selectSort(String value) {
        page.locator(productSortDropDown).selectOption(value);
        return this;
    }

    public CartPage clickCartIcon() {
        click(cartItem);
        return new CartPage(page);
    }

    public List<String> getAllProductNames() {
        return page.locator(inventoryItemNames).allInnerTexts();
    }

    public List<Double> getAllProductPrices() {

        List<String> prices = page.locator(inventoryItemPrices).allInnerTexts();
        List<Double> itemPrices = new ArrayList<>();

        for(String price: prices) {
            itemPrices.add(Double.parseDouble(price.replace("$", "").trim()));
        }

        return itemPrices;
    }
}
