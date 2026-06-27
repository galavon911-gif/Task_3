package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[@href='/register']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Авторизоваться в системе")
    public void login(String email, String password) {
        // Ждем поле ввода, очищаем, вводим email и нажимаем TAB для триггера React
        var emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
        emailField.sendKeys(Keys.TAB);

        // Находим пароль, очищаем, вводим и нажимаем TAB для активации кнопки "Войти"
        var passwordField = driver.findElement(passwordInput);
        passwordField.clear();
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.TAB);

        // Кнопка гарантированно активна. Нажимаем её
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Кликнуть на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Проверить видимость кнопки 'Войти'")
    public boolean isLoginButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        try {
            // Ждем до 5 секунд, пока кнопка оформления заказа физически отрисуется на экране
            var wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(".//button[text()='Оформить заказ']"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}