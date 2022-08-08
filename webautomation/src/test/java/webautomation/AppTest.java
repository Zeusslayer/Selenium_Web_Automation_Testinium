package webautomation;

import static org.junit.Assert.assertTrue;
import java.time.Duration;
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
        driver.manage().window().maximize();
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

        // assertTrue(title.contains("Beymen"));

        // class = default-input o-header__search--input

        // driver.quit();

    }
}
