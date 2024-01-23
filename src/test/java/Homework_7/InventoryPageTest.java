package Homework_7;
import io.qameta.allure.*;
import Homework_6.InventoryPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class InventoryPageTest extends AbstractTest {

    @Test
    @DisplayName("TK-2. ")
    @Description("Добавление товаров в корзину кликом на заголовок товара")
    @Link("http://google.com")
    @Severity(SeverityLevel.NORMAL)
    void addGoodClickTitleTest() throws InterruptedException, IOException {
        authorisation();
        logger.info("TK-2. Добавление товаров в корзину кликом на заголовок товара");

        new InventoryPage(getDriver()).goodPageClick();

        new InventoryPage(getDriver()).addGoodButton();


        logger.info("TK-2. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");


        File file = MyUtils.makeScreenshot(getDriver(), "org.example.HW-7.InventoryPageTest.TC2_" + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));


        logger.info("TK-2. Проверка на добавление товара в корзину (+1)");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals("1"), "Что-то пошло не так, товар не добавлен.");
    }

    @Test
    @DisplayName("TK-3. ")
    @Description("Добавление товаров в корзину кликом на картинку товара")
    @Link("http://google.com")
    @Severity(SeverityLevel.NORMAL)
    void addGoodClickImageTest() throws InterruptedException, IOException {
        authorisation();
        logger.info("TK-3. Добавление товаров в корзину кликом на картинку товара");

        new InventoryPage(getDriver()).goodImageClick();

        new InventoryPage(getDriver()).addGoodButton();


        logger.info("TK-3. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");


        logger.info("TK-3. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");


        File file = MyUtils.makeScreenshot(getDriver(),"org.example.HW-7.InventoryPageTest.TC3_" + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
    }

    @Test
    @DisplayName("TK-4. ")
    @Description("Добавление товаров в корзину без перехода на страницу товара")
    @Link("http://google.com")
    @Severity(SeverityLevel.CRITICAL)
    void addGoodClickButtonTest() throws InterruptedException {
        authorisation();
        logger.info("TK-4. Добавление товаров в корзину без перехода на страницу товара");

        new InventoryPage(getDriver()).addGoodButtonOnPage();


        logger.info("TK-4. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");


        logger.info("TK-4. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");
    }

    @Test
    @DisplayName("TK-5. ")
    @Description("Удаление товара из корзины")
    @Link("http://google.com")
    @Severity(SeverityLevel.CRITICAL)
    void removeGoodTest() throws InterruptedException, IOException {
        authorisation();
        logger.info("TK-5. Добавление товаров в корзину");

        new InventoryPage(getDriver()).addGoodButtonOnPage();


        String count = getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText();


        logger.info("TK-5. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");


        logger.info("TK-5. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");


        new InventoryPage(getDriver()).cart();

        logger.info("TK-5. Проверка открытия корзины");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/cart.html"), "Корзина не открылась!");


        File file = MyUtils.makeScreenshot(getDriver(),"org.example.HW-7.InventoryPageTest.TC5_" + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));


        logger.info("TK-5. Убираем товар из корзины");
        new InventoryPage(getDriver()).delGoodButton();


        logger.info("TK-5. Проверка на удаление товара из корзины (-1)");

        if (Integer.parseInt(count) == 1) {
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@class='shopping_cart_link']")).getText().equals(""), "Что-то пошло не так, товар не удален.");
        } else {
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(String.valueOf(Integer.parseInt(count) - 1)), "Что-то пошло не так, товар не удален.");
        }
    }

    @Test
    @DisplayName("TK-6. ")
    @Description("Удаление товара из корзины без перехода на страницу корзины")
    @Link("http://google.com")
    @Severity(SeverityLevel.CRITICAL)
    void removeGoodNoChangePageTest() throws InterruptedException, IOException {
        authorisation();
        logger.info("TK-6. Добавление товаров в корзину");

        new Homework_7.InventoryPage(getDriver()).addGoodButtonOnPage();


        String count = getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText();


        logger.info("TK-6. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");


        logger.info("TK-6. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");


        logger.info("TK-6. Убираем товар из корзины");
        new InventoryPage(getDriver()).delGoodButtonOnPage();


        logger.info("TK-6. Проверка на удаление товара из корзины (-1)");
        if (Integer.parseInt(count) == 1) {
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@class='shopping_cart_link']")).getText().equals(""), "Что-то пошло не так, товар не удален.");
        } else {
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(String.valueOf(Integer.parseInt(count) - 1)), "Что-то пошло не так, товар не удален.");
        }


        File file = MyUtils.makeScreenshot(getDriver(),"org.example.HW-7.InventoryPageTest.TC6_" + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
    }

    @Step("Создание скриншота")
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
