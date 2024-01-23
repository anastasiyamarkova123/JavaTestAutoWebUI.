package Homework_6;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class LoginTest extends AbstractTest {

    @Test
    @DisplayName("TK-1. Авторизация на сайте магазина")
    void authorisationTest() {
        logger.info("TK-1. Авторизация на сайте магазина");
        new LoginPage(getDriver()).setLogin("standard_user").setPassword("secret_sauce").loginIn();
        logger.info("TK-1. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");
    }



    @AfterEach
    void logout() throws InterruptedException {
        logger.info("Разлогинивание");
        new StartPage(getDriver()).logOut();
        logger.info("Проверка разлогинивания");
        new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/"), "Авторизация не прошла!");
    }
}
