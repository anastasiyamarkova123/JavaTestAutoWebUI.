package Homework_5;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
public abstract class AbstractTest {
    private static WebDriver driver;
    static Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeAll

    static void  init(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeEach

    void getStartPage(){
        Assertions.assertDoesNotThrow( ()-> driver.get("https://www.saucedemo.com/"),"Стартовая страница не доступна!!!");
    }

    @AfterAll

    static void closeAll() throws InterruptedException {

        driver.quit();
        System.out.println( "Все ТК выполнены." );
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void authorisation(){
        Actions LoginIn = new Actions(getDriver());
        LoginIn.sendKeys(getDriver().findElement((By.id("user-name"))),"standard_user")
                .click(getDriver().findElement(By.id("password")))
                .sendKeys("secret_sauce")
                .click(getDriver().findElement(By.id("login-button")))
                .build()
                .perform();
    }
}
