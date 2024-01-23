package Homework_3;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
public class TC_3 {
    public static void main( String[] args ) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        System.out.println("TK-3. Добавление товаров в корзину кликом на картинку товара");
        driver.get("https://www.saucedemo.com/");


        driver.findElement((By.id("user-name"))).sendKeys("standard_user");
        driver.findElement((By.id("password"))).sendKeys("secret_sauce");
        driver.findElement((By.id("login-button"))).submit();


        driver.findElement(By.xpath(".//div[@class='inventory_item_img']/a[@id='item_1_img_link']")).click();

        driver.findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory']")).click();

        String textButton = driver.findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory']")).getText();
        System.out.println((textButton.equals("REMOVE")) ? "ОК, название кнопки изменилось." : "Проблема с кнопкой!");


        String count = driver.findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText();
        System.out.println((Integer.parseInt(count) > 0) ? "Товар добавлен." : "Что-то пошло не так, товар не добавлен.");


        Thread.sleep(2000);
        driver.quit();

        System.out.println("Товаров в корзине - " + count);
        System.out.println( "Успешное выполнение TK-3." );
    }
}

