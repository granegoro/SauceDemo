package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage {

    private static final SelenideElement heading = $(".title");

    private static final SelenideElement firstName = $("input[data-test=firstName]");
    private static final SelenideElement lastName = $("input[data-test=lastName]]");
    private static final SelenideElement postalCode = $("input[data-test=postalCode]");

    private static final ElementsCollection checkoutButtons = $$(".checkout_buttons");

    public CheckoutPage() {
        heading.shouldBe(Condition.visible).shouldHave(Condition.exactText("Checkout: Your Information"));
    }

    public CartPage cancelCheckout(String dataTest) {
        checkoutButtons.findBy(Condition.attribute("data-test", dataTest)).click();
        return new CartPage();
    }

    public CartPage continueCheckout(String dataTest) {
        checkoutButtons.findBy(Condition.attribute("data-test", dataTest)).click();
        return new CartPage();
    }






//    private static final SelenideElement cancelButton = $("[data-test=cancel]");
//    private static final SelenideElement postalCode = $("input[data-test=postalCode]");




}
