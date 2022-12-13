package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.ProductsPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;

public class SwagLabsTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    //HappyPath
    @Test
    void shouldAddItemstoCart() {
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
        productsPage.removeItems(removeItem2);
        productsPage.removeItems(removeItem3);
        productsPage.checkCartBadge("2");
        var cartPage = productsPage.enterCart();
        cartPage.removeItem(1);
        var checkoutPage = cartPage.checkout();
        var overviewPage = checkoutPage.continueCheckout("continue");
        var completePage = overviewPage.finishCheckout("finish");
        completePage.backToProducts();
    }

    //Сортировка товаров

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
        var productsPage=loginPage.validLogin(user);
        productsPage.setSortingOptionLowToHigh();


    }


}
