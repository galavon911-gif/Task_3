package tests;

import org.junit.Test;
import org.junit.Assert;
import pageobject.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    public void constructorBunsTabTest() {
        MainPage mainPage = new MainPage(driver);

        //Кликаем на соусы, затем обратно на булки
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        Assert.assertTrue(mainPage.isBunsTabActive());
    }

    @Test
    public void constructorSaucesTabTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();

        Assert.assertTrue(mainPage.isSaucesTabActive());
    }

    @Test
    public void constructorFillingsTabTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsTab();

        Assert.assertTrue(mainPage.isFillingsTabActive());
    }
}