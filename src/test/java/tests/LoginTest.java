package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;
import pageobject.ProfilePage;

public class LoginTest extends BaseTest {
    // Константные тестовые данные существующего пользователя
    private final String EMAIL = "user432@yandex.ru";
    private final String PASSWORD = "qwerty123";

    @Before
    public void prepareStartPage() {
        // Каждый тест авторизации начинается с чистой главной страницы
        driver.get(BASE_URL);
    }

    @Test
    public void loginFromMainPageButtonTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);

        // 1. Вход по кнопке «Войти в аккаунт» на главной
        mainPage.clickLoginAccountButton();
        loginPage.login(EMAIL, PASSWORD);

        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    public void loginFromPersonalAccountButtonTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);

        // 2. Вход через кнопку «Личный кабинет»
        mainPage.clickPersonalAccountButton();
        loginPage.login(EMAIL, PASSWORD);

        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    public void loginFromRegisterPageTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        var registerPage = new RegisterPage(driver);

        // 3. Вход через кнопку в форме регистрации
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();

        loginPage.login(EMAIL, PASSWORD);

        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    public void loginFromForgotPasswordPageTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        var forgotPasswordPage = new ForgotPasswordPage(driver);

        // 4. Вход через кнопку в форме восстановления пароля
        driver.get(BASE_URL + "forgot-password");
        forgotPasswordPage.clickLoginLink();

        loginPage.login(EMAIL, PASSWORD);

        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @After
    public void cleanUp() {
        // Удаляем куки, чтобы сбросить авторизацию
        driver.manage().deleteAllCookies();
        // Если сайт использует LocalStorage, очищаем и его (через JavaScript)
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }
}