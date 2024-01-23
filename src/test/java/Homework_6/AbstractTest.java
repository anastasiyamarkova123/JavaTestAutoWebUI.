package Homework_6;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
public abstract class AbstractTest {
    private static WebDriver driver;
    static Logger logger = LoggerFactory.getLogger(Homework_5.AbstractTest.class);

    @BeforeAll

    static void init() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        options.setPageLoadTimeout(Duration.ofSeconds(10));
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach

    void getStartPage() {
        Assertions.assertDoesNotThrow(() -> driver.get("https://www.saucedemo.com/"), "Стартовая страница не доступна!!!");
        logger.info("Переход на стартовую страницу");
        Assertions.assertTrue(true);
    }

    @AfterAll

    static void closeAll() throws InterruptedException {

        logger.info("Закрытие браузера. Все ТК выполнены.");
        if (driver != null) driver.quit();

    }

    void authorisation() {
        logger.info("Авторизация на сайте магазина");
        new LoginPage(getDriver()).loginIn("standard_user", "secret_sauce");
        logger.info("Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");
    }

    public WebDriver getDriver() {
        return this.driver;
    }

}
