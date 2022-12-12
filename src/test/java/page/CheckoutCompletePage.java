package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {

    private static final SelenideElement heading = $(".title");
    private static final SelenideElement completeHeader = $(".complete-header");
    public static final SelenideElement backHome = $("[data-test=back-to-products]");

    public CheckoutCompletePage() {
        heading.shouldBe(Condition.visible).shouldHave(Condition.exactText("Checkout: Complete!"));
        completeHeader.shouldBe(Condition.visible);
    }

    public ProductsPage backToProducts() {
        backHome.click();
        return new ProductsPage();
    }


}
