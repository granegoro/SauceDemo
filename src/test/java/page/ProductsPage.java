package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
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

    private static final SelenideElement addItem0 = $("[data-test=add-to-cart-sauce-labs-bike-light]");
    private static final SelenideElement removeItem0 = $("[data-test=remove-sauce-labs-bike-light]");

    private static final SelenideElement addItem1 = $("[data-test=add-to-cart-sauce-labs-bolt-t-shirt]");
    private static final SelenideElement removeItem1 = $("[data-test=add-to-cart-sauce-labs-bolt-t-shirt]");

    private static final SelenideElement addItem2 = $("[data-test=add-to-cart-sauce-labs-onesie]");
    private static final SelenideElement removeItem2 = $("[data-test=remove-sauce-labs-onesie]");

    private static final SelenideElement addItem4 = $("[data-test=add-to-cart-sauce-labs-backpack]");
    private static final SelenideElement removeItem4 = $("[data-test=remove-sauce-labs-backpack]");

    private static final SelenideElement addItem5 = $("[data-test=add-to-cart-sauce-labs-fleece-jacket]");
    private static final SelenideElement removeItem5 = $("[data-test=remove-sauce-labs-fleece-jacket]");

    private static final ElementsCollection inventoryItems = $$(".inventory_item_name");

    private static final ElementsCollection inventoryPrices = $$(".pricebar .inventory_item_price");
    private final String currency = "$";
    private final String cents = ".99";

    private static final SelenideElement cart = $(".shopping_cart_container .shopping_cart_link");
    private static final SelenideElement cartBadge = $(".shopping_cart_badge");

    public void findPageTitle() {
        pageTitle.shouldHave(text("Products")).shouldBe(visible);
    }

    private int extractPrice(String text) {
        var currencySign = text.indexOf(currency);
        var dotCents = text.indexOf(cents);
        var value = text.substring(currencySign + currency.length(), dotCents);
        return Integer.parseInt(value);
    }

    public int getFirstItemPrice() {
        var text = inventoryPrices.first().text();
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
        activeOption.shouldHave(exactText("Price (low to high)"));
        inventoryItems.get(0).shouldHave(exactText("Sauce Labs Backpack"));
    }

    public void addThreeItemsToCart() {
        addItem0.click();
        addItem1.click();
        addItem4.click();
        cartBadge.shouldHave(exactText("3"));
    }

    public void removeItem1FromCart() {
        removeItem0.click();
        cartBadge.shouldHave(exactText("2"));
    }

    public CartPage enterCart() {
        cart.click();
        return new CartPage();
    }

   /* public class DashboardPage {
        // к сожалению, разработчики не дали нам удобного селектора, поэтому так
        private ElementsCollection cards = $$(".list__item div");
        private final String balanceStart = "баланс: ";
        private final String balanceFinish = " р.";

        public Dashboard() {
        }

        public int getCardBalance(String id) {
            //перебрать все карты и найти по атрибуту data-test-id
            return extractBalance(text);
        }

        private int extractBalance(String text) {
            val start = text.indexOf(balanceStart);
            val finish = text.indexOf(balanceFinish);
            val value = text.substring(start + balanceStart.length(), finish);
            return Integer.parseInt(value);
        }
    }*/

}
