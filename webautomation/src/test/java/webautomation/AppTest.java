package webautomation;

import static org.junit.Assert.assertTrue;
import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.support.ui.Wait;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {


    private static void impWait(WebDriver driver, long time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }
 

    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();
        // maximize the browser window
        //driver.manage().window().maximize();
        // open the browser and go to the url
        driver.get("http://www.beymen.com");
        // wait for the page to load
        impWait(driver, 15);
        //get cell values from the excel file
        String cellA1="", cellB1="";
        try {
            cellA1 = readingXL.readXL(0,0);
            cellB1 = readingXL.readXL(0, 1);
        } catch (Exception e) {
            }
        // get the title and url of the page
        String url = driver.getCurrentUrl();
        String title = driver.getTitle();

        // find the element(searchbox) by xpath
        WebElement searchbox = driver.findElement(By.xpath("//input[@class='default-input o-header__search--input']"));// xpath example = //tagname[@Attribute='Value']

        // send the search keyword to the searchbox
        searchbox.sendKeys(cellA1);

        // searchbox.clear(); // why didn't it work?

        searchbox.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        searchbox.sendKeys(cellB1, Keys.ENTER);
        impWait(driver, 15);

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@data-component-name='list']"));
        System.out.println("Number of search results: " + searchResults.size());
        String randomItem = searchResults.get((int) Math.floor(Math.random() * searchResults.size())).getAttribute("data-productid");
        
        driver.findElement(By.xpath("//div[@data-productid='%s']".formatted(randomItem))).click();

        String productName = driver.findElement(By.className("o-productDetail__description")).getText();
        String productPrice = driver.findElement(By.className("m-price__new")).getText();
        String productURL = driver.getCurrentUrl();
        System.out.println(productName);
        System.out.println(productPrice);
        System.out.println(productURL);

            
        try {
            writeTxt.txt(productName, productPrice, productURL);
        } catch (Exception e) {
          }

        List<WebElement> sizeOptions = driver.findElements(By.xpath("//*[@id='sizes']/div/*"));
        for (WebElement size : sizeOptions) {
            if (!size.getAttribute("class").equals("m-variation__item -disabled")) {
                size.click();
                break;
            }
        }
        driver.findElement(By.id("addBasket")).click();

        driver.findElement(
        // assertTrue(title.contains("Beymen"));

        // class = default-input o-header__search--input

        // driver.quit();

    }
}
