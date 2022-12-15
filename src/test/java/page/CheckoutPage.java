package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage {

    private static final SelenideElement heading = $(".title");

    private static final SelenideElement firstName = $("input[data-test=firstName]");
    private static final SelenideElement lastName = $("input[data-test=lastName]");
    private static final SelenideElement postalCode = $("input[data-test=postalCode]");

    private static final SelenideElement continueButton = $(".checkout_buttons [data-test=continue]");
    private static final SelenideElement cancelButton = $(".checkout_buttons [data-test=cancel]");

    public CheckoutPage() {
        heading.shouldBe(Condition.visible).shouldHave(Condition.exactText("Checkout: Your Information"));
    }

    //  data-test=continue
    public OverviewPage continueCheckout(DataHelper.OrderInfo orderInfo) {
        firstName.setValue(orderInfo.getFirstName());
        lastName.setValue(orderInfo.getLastName());
        postalCode.setValue(orderInfo.getPostalCode());
        continueButton.click();
        return new OverviewPage();
    }

    //  data-test=cancel
    public CartPage cancelCheckout() {
        cancelButton.click();
        return new CartPage();
    }





}
