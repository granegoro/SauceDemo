package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ItemPage {

    private static final SelenideElement addButton = $(".inventory_details_desc_container button");
    private static final SelenideElement removeButton = $(".inventory_details_desc_container button");

    private static final SelenideElement backToProductsButton = $("[data-test=back-to-products]");


    public void addItems() {
        addButton.click();
    }

    public void removeItems() {
        removeButton.click();
    }

    public ProductsPage backToProducts() {
        backToProductsButton.click();
        return new ProductsPage();
    }

}
