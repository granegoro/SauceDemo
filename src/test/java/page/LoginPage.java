package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static final SelenideElement usernameField = $("input[data-test=username]");
    private static final SelenideElement passwordField = $("input[data-test=password]");
    private static final SelenideElement loginButton = $("[data-test=login-button]");

    public ProductsPage validLogin(DataHelper.Auth.UserInfo userInfo) {
        usernameField.setValue(userInfo.getLogin());
        passwordField.setValue(userInfo.getPassword());
        loginButton.click();
        return new ProductsPage();
    }

}
