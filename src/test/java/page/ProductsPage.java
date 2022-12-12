package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {

    private static final SelenideElement heading = $(".title");

    private static final SelenideElement sortContainer = $("[data-test=product_sort_container]");
    private static final SelenideElement activeOption = $(".select_container .active_option");
    private static final SelenideElement sortingOptionZA = $("[value=za]");
    private static final SelenideElement sortingOptionAZ = $("[value=az]");
    private static final SelenideElement sortingOptionLowToHigh = $("[value=lohi]");
    private static final SelenideElement sortingOptionHighToLow = $("[value=hilo]");

    private static final ElementsCollection addItem = $$(".inventory_item button");
    private static final ElementsCollection removeItem = $$(".inventory_item button");

    private static final ElementsCollection inventoryItems = $$(".inventory_item_name");
    private static final ElementsCollection inventoryPrices = $$(".pricebar .inventory_item_price");

    private static final SelenideElement cart = $(".shopping_cart_container .shopping_cart_link");
    private static final SelenideElement cartBadge = $(".shopping_cart_badge");

    public ProductsPage() {
        heading.shouldHave(text("Products")).shouldBe(visible);
    }

    private int extractPrice(String text) {
        var currency = "$";
        var cents = ".99";
        var currencySign = text.indexOf(currency);
        var dotCents = text.indexOf(cents);
        var value = text.substring(currencySign + currency.length(), dotCents);
        return Integer.parseInt(value);
    }

    public int getItemPrice(int itemNum) {
        var text = inventoryPrices.get(itemNum).text();
        return extractPrice(text);
    }

    public void setSortingZA() {
        sortContainer.click();
        sortingOptionZA.click();
        activeOption.shouldHave(exactText("Name (Z to A)"));
        inventoryItems.get(0).shouldHave(exactText("Test.allTheThings() T-Shirt (Red)"));
    }

    public void setSortingAZ() {
        sortContainer.click();
        sortingOptionAZ.click();
        activeOption.shouldHave(exactText("Name (A to Z)"));
        inventoryItems.get(0).shouldHave(exactText("Sauce Labs Backpack"));
    }

    public void setSortingOptionLowToHigh() {
        sortContainer.click();
        sortingOptionLowToHigh.click();
        var firstItemPrice = getItemPrice(0);
        var lastItemPrice = getItemPrice(5);
        Assertions.assertTrue(firstItemPrice < lastItemPrice);
    }

    public void setSortingOptionHighToLow() {
        sortContainer.click();
        sortingOptionHighToLow.click();
        var firstItemPrice = getItemPrice(0);
        var lastItemPrice = getItemPrice(5);
        Assertions.assertTrue(firstItemPrice > lastItemPrice);
    }

    public void addItems(DataHelper.ItemInfo itemInfo) {
        addItem.findBy(attribute("data-test", itemInfo.getTestId())).click();
    }

    public void removeItems(DataHelper.ItemInfo itemInfo) {
        removeItem.findBy(attribute("data-test", itemInfo.getTestId())).click();
    }

    public void checkCartBadge(String badge) {
        cartBadge.shouldHave(exactText(badge));
    }

    public CartPage enterCart() {
        cart.click();
        return new CartPage();
    }

}
