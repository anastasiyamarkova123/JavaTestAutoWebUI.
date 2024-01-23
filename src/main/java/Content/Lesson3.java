package Content;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class Lesson3 {
    public static void main( String[] args ) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://www.bbc.com/");

        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"orb-nav-links\"]/ul/li[3]/a"));
        webElement.click();
        driver.findElement(By.xpath("//*[@id=\"u4674795905908347\"]/div/nav/div[1]/div/ul/li[10]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sp-nav-secondary\"]/li[4]/a")).click();

        driver.findElement(By.id("orb-search-q")).sendKeys("ROC");
        driver.findElement(By.id("orb-search-q")).submit();

//        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(webElement)); // явное ожиданиие не более 5 секунд
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 5); // явное ожиданиие не более 5 секунд
        Thread.sleep(1000); // принудительное ожидание 1000 милисекунд
        driver.quit();
        System.out.println( "Успешное  выполнения  урока!!!" );
    }
}
