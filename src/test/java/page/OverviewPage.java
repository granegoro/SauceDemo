package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OverviewPage {

    private static final SelenideElement heading = $(".title");

    private static final ElementsCollection cartItems = $$(".inventory_item_name");
    private static final ElementsCollection checkoutButtons = $$(".cart_footer button");

    public OverviewPage() {
        heading.shouldHave(text("Checkout: Overview")).shouldBe(visible);
    }

    //data-test=cancel
    public ProductsPage cancelCheckout(String dataTest) {
        checkoutButtons.findBy(Condition.attribute("data-test", dataTest)).click();
        return new ProductsPage();
    }

    //data-test=finish
    public CompletePage finishCheckout(String dataTest) {
        checkoutButtons.findBy(Condition.attribute("data-test", dataTest)).click();
        return new CompletePage();
    }

    public ItemPage openItemPage(int index) {
        cartItems.get(index).click();
        return new ItemPage();
    }

}
