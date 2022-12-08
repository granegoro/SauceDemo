package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Value;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.awt.SystemColor.text;

public class ProductsPage {

    private static final SelenideElement pageTitle = $(".title");

    private static final SelenideElement sortContainer = $("[data-test=product_sort_container]");
    private static final SelenideElement activeOption = $(".select_container .active_option");
    private static final SelenideElement sortingOptionZA = $("[value=za]");
    private static final SelenideElement sortingOptionAZ = $("[value=az]");
    private static final SelenideElement sortingOptionLowToHigh = $("[value=lohi]");
    private static final SelenideElement sortingOptionHighToLow = $("[value=hilo]");

    private static final ElementsCollection items = $$(".inventory_item");
    private static final ElementsCollection addItem = $$(".inventory_item button");

    private static final SelenideElement addItem0 = $("[data-test=add-to-cart-sauce-labs-bike-light]");
    private static final SelenideElement removeItem0 = $("[data-test=remove-sauce-labs-bike-light]");

    private static final SelenideElement addItem1 = $("[data-test=add-to-cart-sauce-labs-bolt-t-shirt]");
    private static final SelenideElement removeItem1 = $("[data-test=add-to-cart-sauce-labs-bolt-t-shirt]");

    private static final SelenideElement addItem2 = $("[data-test=add-to-cart-sauce-labs-onesie]");
    private static final SelenideElement removeItem2 = $("[data-test=remove-sauce-labs-onesie]");

    private static final SelenideElement addItem3 = $("[data-test=add-to-cart-sauce-labs-bike-ligh]");
    private static final SelenideElement removeItem3 = $("[data-test=remove-sauce-labs-bike-ligh]");

    private static final SelenideElement addItem4 = $("[data-test=add-to-cart-sauce-labs-backpack]");
    private static final SelenideElement removeItem4 = $("[data-test=remove-sauce-labs-backpack]");

    private static final SelenideElement addItem5 = $("[data-test=add-to-cart-sauce-labs-fleece-jacket]");
    private static final SelenideElement removeItem5 = $("[data-test=remove-sauce-labs-fleece-jacket]");

    private static final ElementsCollection inventoryItems = $$(".inventory_item_name");

    private static final ElementsCollection inventoryPrices = $$(".pricebar .inventory_item_price");

    private static final SelenideElement cart = $(".shopping_cart_container .shopping_cart_link");
    private static final SelenideElement cartBadge = $(".shopping_cart_badge");

    private static final SelenideElement terms = $(byText("Terms of Service"));
    private static final SelenideElement policy = $(byText("Privacy Policy"));


    public void findPageTitle() {
        pageTitle.shouldHave(text("Products")).shouldBe(visible);
    }

    public void checkTerms() {
        terms.click();
    }

    public void checkPolicy() {
        policy.click();
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

    public void checkCartBadge(String badge) {
        cartBadge.shouldHave(exactText(badge));
    }

    public void addAllItems() {
        addItem0.click();
        addItem1.click();
        addItem2.click();
        addItem3.click();
        addItem4.click();
        addItem5.click();
        cartBadge.shouldHave(exactText("6"));
    }

    public void removeAllItems() {
        removeItem0.click();
        removeItem1.click();
        removeItem2.click();
        removeItem3.click();
        removeItem4.click();
        removeItem5.click();
        cartBadge.shouldNotBe(visible);
    }

    public CartPage enterCart() {
        cart.click();
        return new CartPage();
    }

}
