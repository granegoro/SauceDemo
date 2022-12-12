package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ItemPage {

    private static final ElementsCollection addButton = $$(".inventory_details_container button");
    private static final ElementsCollection removeButton = $$(".inventory_details_container button");

    private static final SelenideElement backToProductsButton = $("[data-test=back-to-products]");


    public void addItems(DataHelper.ItemInfo itemInfo) {
        addButton.findBy(attribute("data-test", itemInfo.getTestId())).click();
    }

    public void removeItems(DataHelper.ItemInfo itemInfo) {
        removeButton.findBy(attribute("data-test", itemInfo.getTestId())).click();
    }

    public ProductsPage backToProducts() {
        backToProductsButton.click();
        return new ProductsPage();
    }

}
