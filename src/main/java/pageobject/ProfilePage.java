package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']");
    private final By logoLink = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By profileInstruction = By.xpath(".//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть кнопку 'Выход'")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Кликнуть на ссылку 'Конструктор'")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    @Step("Кликнуть на логотип Stellar Burgers")
    public void clickLogo() {
        driver.findElement(logoLink).click();
    }

    @Step("Проверить отображение контента личного кабинета")
    public boolean isProfileContentDisplayed() {
        return driver.findElement(profileInstruction).isDisplayed();
    }
}
