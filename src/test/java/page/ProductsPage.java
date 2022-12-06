package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {

    private static final SelenideElement pageTitle = $(".title");

    public void findPageTitle() {
        pageTitle.shouldHave(text("Products")).shouldBe(visible);
    }
}
