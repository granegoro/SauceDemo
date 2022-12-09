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
import static data.DataHelper.get0ItemAddInfo;
import static data.DataHelper.get1ItemAddInfo;
/*import static data.DataHelper.get0ItemInfo;*/

public class SwagLabsTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldAddItemstoCart() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        var addItem0 = get0ItemAddInfo();
        var addItem1 = get1ItemAddInfo();
        productsPage.findPageTitle();
        productsPage.addItems(addItem0);
        productsPage.addItems(addItem1);
        productsPage.checkCartBadge("2");
        /*var cartPage = productsPage.enterCart();*/
    }

    //Сортировка товаров

    @Test
    void shouldSetSortingAZ() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        productsPage.findPageTitle();
        productsPage.setSortingAZ();
    }

    @Test
    void shouldSetSortingZA() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        productsPage.findPageTitle();
        productsPage.setSortingZA();
    }

    @Test
    void shouldSetSortingHiLo() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage = loginPage.validLogin(user);
        productsPage.findPageTitle();
        productsPage.setSortingOptionHighToLow();
    }

    @Test
    void shouldSetSortingLoHi() {
        var loginPage = open(System.getProperty("sut.url"), LoginPage.class);
        var user = DataHelper.Auth.getStandardUser();
        var productsPage=loginPage.validLogin(user);
        productsPage.findPageTitle();
        productsPage.setSortingOptionLowToHigh();


    }


}
