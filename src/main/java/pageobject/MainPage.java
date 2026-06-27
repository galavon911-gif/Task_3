package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Кликнуть по кнопке 'Войти в аккаунт' на главной")
    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    @Step("Кликнуть по кнопке 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Кликнуть на вкладку 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Кликнуть на вкладку 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Кликнуть на вкладку 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверить активность вкладки 'Булки'")
    public boolean isBunsTabActive() {
        return driver.findElement(bunsTab).getAttribute("class").contains("tab_tab_type_current");
    }

    @Step("Проверить активность вкладки 'Соусы'")
    public boolean isSaucesTabActive() {
        return driver.findElement(saucesTab).getAttribute("class").contains("tab_tab_type_current");
    }

    @Step("Проверить активность вкладки 'Начинки'")
    public boolean isFillingsTabActive() {
        return driver.findElement(fillingsTab).getAttribute("class").contains("tab_tab_type_current");
    }

    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        try {
            // Создаем локальное ожидание на 5 секунд
            var wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));

            // Ждем, пока элемент физически появится в структуре страницы
            var elements = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                    .presenceOfAllElementsLocatedBy(orderButton));

            // Если элемент найден и он отображается — возвращаем true
            if (elements != null && !elements.isEmpty()) {
                return elements.get(0).isDisplayed();
            }
        } catch (Exception e) {
            // Перехватываем NoSuchElementException, TimeoutException и любые другие ошибки
            System.out.println("Кнопка 'Оформить заказ' не найдена или еще не отрисовалась.");
        }
        return false;
    }
}