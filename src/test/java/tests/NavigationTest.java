package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class NavigationTest extends BaseTest {
    private final String EMAIL = "user432@yandex.ru";
    private final String PASSWORD = "qwerty123";

    @Before
    public void loginBeforeEachTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);

        mainPage.clickPersonalAccountButton();
        loginPage.login(EMAIL, PASSWORD);

        // Даем драйверу время дождаться успешного ответа сервера
        var wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> mainPage.isOrderButtonDisplayed());
        mainPage.clickPersonalAccountButton();
    }

    @Test
    public void navigateFromProfileToConstructorClickTest() {
        var mainPage = new MainPage(driver);
        var profilePage = new ProfilePage(driver);

        mainPage.clickPersonalAccountButton();
        profilePage.clickConstructorLink();
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    public void navigateFromProfileToLogoClickTest() {
        var mainPage = new MainPage(driver);
        var profilePage = new ProfilePage(driver);

        mainPage.clickPersonalAccountButton();
        profilePage.clickLogo();
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    public void navigateToProfileTest() {
        var mainPage = new MainPage(driver);
        var profilePage = new ProfilePage(driver);

        mainPage.clickPersonalAccountButton();
        Assert.assertTrue(profilePage.isProfileContentDisplayed());
    }

    @Test
    public void logoutTest() {
        var mainPage = new MainPage(driver);
        var profilePage = new ProfilePage(driver);
        var loginPage = new LoginPage(driver);

        mainPage.clickPersonalAccountButton();
        profilePage.clickLogoutButton();

        Assert.assertTrue(loginPage.isLoginButtonDisplayed());

    }
}