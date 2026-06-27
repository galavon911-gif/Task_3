package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();
        // ОТКЛЮЧАЕМ КЭШ: эти аргументы запрещают использовать дисковый и сетевой кэш браузера
        options.addArguments("--disable-cache");
        options.addArguments("--disk-cache-size=1");
        options.addArguments("--media-cache-size=1");
        options.addArguments("--remote-allow-origins=*");

        if (browser.equalsIgnoreCase("yandex")) {
            WebDriverManager.chromedriver().driverVersion("146.0.7680.0").setup();
            //Указан путь до исполнительного файла браузера Яндекс на моем компьютере
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else {
            WebDriverManager.chromedriver().setup();}

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
