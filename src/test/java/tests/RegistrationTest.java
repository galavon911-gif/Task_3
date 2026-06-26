package tests;

import org.junit.Test;
import org.junit.Assert;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

public class RegistrationTest extends BaseTest {

    @Test
    public void successRegistrationTest() {
        int randomId = (int) (Math.random() * 1000000);
        String dynamicEmail = "burger" + randomId + "@yandex.ru";

        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        var registerPage = new RegisterPage(driver);

        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        registerPage.register("TestName"+ (int)(Math.random()*10), "truepass" + (int)(Math.random()*1000) + "@yandex.ru", "qwerty123124");

        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void registrationWithShortPasswordErrorTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        var registerPage = new RegisterPage(driver);

        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        registerPage.register("TestName", "badpass" + (int)(Math.random()*1000) + "@yandex.ru", "12345");

        Assert.assertTrue(registerPage.isPasswordErrorDisplayed());
    }
}