package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private static final SelenideElement heading = $(".title");

    private static final ElementsCollection quantity = $$(".cart_quantity");
    private static final ElementsCollection removeItem = $$(".cart_item button");
    private static final ElementsCollection cartItems = $$(".inventory_item_name");

    private static final SelenideElement continueShoppingButton = $("[data-test=continue-shopping]");
    private static final SelenideElement checkoutButton = $("[data-test=checkout]");

    public CartPage() {
        heading.shouldHave(text("Your Cart")).shouldBe(visible);
    }

    public CheckoutPage checkout() {
        checkoutButton.click();
        return new CheckoutPage();
    }

    public ProductsPage continueShopping() {
        continueShoppingButton.click();
        return new ProductsPage();
    }

    public ItemPage enterItemPage(int index) {
        cartItems.get(index).click();
        return new ItemPage();
    }

    public void changeQuantity(int index) {
        quantity.get(index).click();
        quantity.get(index).shouldBe(Condition.interactable);
    }

    public void removeItem(int index) {
        removeItem.get(index).click();
    }
}