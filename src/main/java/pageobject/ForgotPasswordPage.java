package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    private final By loginLink = By.xpath(".//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на ссылку 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}