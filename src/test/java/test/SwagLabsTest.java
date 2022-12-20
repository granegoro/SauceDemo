package test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.ProductsPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static data.DataHelper.Order.*;

public class SwagLabsTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void resetApp() {
        $(".bm-burger-button").click();
        $("#reset_sidebar_link").click();
        $("#logout_sidebar_link").click();
        Selenide.refresh();
    }
    @Test
    void shouldAddItemsToCartAndCompleteOrder() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getValidOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        var overviewPage = checkoutPage.continueCheckout(orderInfo);
        var completePage = overviewPage.finishCheckout("finish");
        completePage.backToProducts();
    }

    @Test
    void shouldAddItemsToCartAndCompleteOrderIfCyrillicData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getCyrillicOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        var overviewPage = checkoutPage.continueCheckout(orderInfo);
        var completePage = overviewPage.finishCheckout("finish");
        completePage.backToProducts();
    }

    @Test
    void shouldNotCompleteOrderIfEmptyFieldsInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckoutWithEmptyFields();
        checkoutPage.findErrorMessage();
    }

    @Test
    void shouldNotCompleteOrderIfBlankSpacesInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getSpacesOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckout(orderInfo);
        checkoutPage.findErrorMessage();
    }

    @Test
    void shouldNotCompleteOrderIfNumericFirstNameInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getNumericFirstNameOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckout(orderInfo);
        checkoutPage.findErrorMessage();
    }

    @Test
    void shouldNotCompleteOrderIfNumericLastNameInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getNumericLastNameOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckout(orderInfo);
        checkoutPage.findErrorMessage();
    }

    @Test
    void shouldNotCompleteOrderIfLettersInPostalCodeInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getLettersInPostalCodeOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckout(orderInfo);
        checkoutPage.findErrorMessage();
    }

    @Test
    void shouldNotCompleteOrderIfSymbolicFirstNameInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getSymbolicFirstNameOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckout(orderInfo);
        checkoutPage.findErrorMessage();
    }

    @Test
    void shouldNotCompleteOrderIfSymbolicLastNameInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getSymbolicLastNameOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckout(orderInfo);
        checkoutPage.findErrorMessage();
    }

    @Test
    void shouldNotCompleteOrderIfSymbolicPostalCodeInOrderData() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getSymbolicPostalCodeOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        checkoutPage.continueCheckout(orderInfo);
        checkoutPage.findErrorMessage();
    }


    @Test
    void shouldNotCompleteOrderWithEmptyCart() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var cartPage = productsPage.enterCart();
        cartPage.findEmptyCartMessage();
    }

    @Test
    void shouldRemoveItemsFromCart() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem0 = get0ItemAddInfo();
        var addItem1 = get1ItemAddInfo();
        var addItem2 = get2ItemAddInfo();
        var addItem3 = get3ItemAddInfo();
        var removeItem2 = get2ItemRemoveInfo();
        var removeItem3 = get3ItemRemoveInfo();
        productsPage.addItems(addItem0);
        productsPage.addItems(addItem1);
        productsPage.addItems(addItem2);
        productsPage.addItems(addItem3);
        productsPage.checkCartBadge("4");
        productsPage.removeItems(removeItem2);
        productsPage.removeItems(removeItem3);
        productsPage.checkCartBadge("2");
        var cartPage = productsPage.enterCart();
        cartPage.removeItem(1);
        cartPage.findRemovedItem();
        productsPage.checkCartBadge("1");
    }

    @Test
    void shouldReturnStepwiseFromOrderConfirmation() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getValidOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        var overviewPage = checkoutPage.continueCheckout(orderInfo);
        var backToCheckoutPage = overviewPage.cancelCheckout("cancel");
        var backToCartPage = backToCheckoutPage.cancelCheckout();
        backToCartPage.continueShopping();
    }

    @Test
    void shouldChangeItemQuantityInCart() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        cartPage.changeQuantity(0);
    }

    @Test
    void shoulEnterItemPageFromCart() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var itemPage = cartPage.enterItemPage(0);
        itemPage.backToProducts();
    }

    @Test
    void shouldEnterItemPageFromProductsPage() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var itemPage = productsPage.enterItemPage(0);
        itemPage.backToProducts();
    }

    @Test
    void shouldEnterItemPageFromOverviewPage() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem = get0ItemAddInfo();
        var orderInfo = getValidOrderInfo();
        productsPage.addItems(addItem);
        var cartPage = productsPage.enterCart();
        var checkoutPage = cartPage.checkout();
        var overviewPage = checkoutPage.continueCheckout(orderInfo);
        var itemPage = overviewPage.enterItemPage(0);
        itemPage.backToProducts();
    }

    @Test
    void shouldAddAndRemoveItemsViaItemPage() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var itemPage = productsPage.enterItemPage(0);
        itemPage.addItems();
        productsPage.checkCartBadge("1");
        itemPage.removeItems();
    }

    @Test
    void shouldSetSortingAZ() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        productsPage.setSortingAZ();
    }

    @Test
    void shouldSetSortingZA() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        productsPage.setSortingZA();
    }

    @Test
    void shouldSetSortingHiLo() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        productsPage.setSortingOptionHighToLow();
    }

    @Test
    void shouldSetSortingLoHi() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        productsPage.setSortingOptionLowToHigh();
    }


}
