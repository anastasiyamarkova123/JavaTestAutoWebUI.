package Homework_3;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TC_1 {
    public static void main( String[] args ) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        System.out.println("TK-1. Авторизация на сайте магазина");
        driver.get("https://www.saucedemo.com/");


        driver.findElement((By.id("user-name"))).sendKeys("standard_user");
        driver.findElement((By.id("password"))).sendKeys("secret_sauce");
        driver.findElement((By.id("login-button"))).submit();


        Thread.sleep(2000);
        driver.quit();

        System.out.println( "Успешное выполнение TK-1." );
    }
}
