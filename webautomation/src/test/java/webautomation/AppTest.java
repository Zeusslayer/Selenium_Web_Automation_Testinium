package webautomation;

import static org.junit.Assert.assertTrue;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

    private static void impWait(WebDriver driver, long time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    private static void sleepingTime(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e1) {
        }    
    }

    public static void main(String[] args) {
        // open browser
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();
        driver.manage().window().maximize(); // maximize the browser window

        driver.get("http://www.beymen.com"); // Go to the url
        impWait(driver, 15); // wait for the page to load

        // get cell values from the excel file
        String cellA1 = "", cellB1 = "";
        try {
            cellA1 = readingXL.readXL(0, 0);
            cellB1 = readingXL.readXL(0, 1);
        } catch (Exception e) {
        }

        String url = driver.getCurrentUrl(); // get the url of the page
        assertTrue(url.equals("https://www.beymen.com/")); // assert that the title and url are correct
        sleepingTime(1);
        // find the element(searchbox) by xpath
        WebElement searchbox = driver.findElement(By.xpath("//input[@class='default-input o-header__search--input']"));// xpath example = //tagname[@Attribute='Value']
        searchbox.sendKeys(cellA1); // send the search keyword to the searchbox
        sleepingTime(1);

        // searchbox.clear(); // why didn't it work?
        searchbox.sendKeys(Keys.CONTROL + "a", Keys.DELETE); // clear the searchbox
        sleepingTime(1);
        searchbox.sendKeys(cellB1, Keys.ENTER); // send the search keyword to the searchbox and press enter
        sleepingTime(5);

        // find the list of productIDs by xpath
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@data-component-name='list']"));
        System.out.println("Number of search results: " + searchResults.size());
        // get a random productID from the list
        String randomItem = searchResults.get((int) Math.floor(Math.random() * searchResults.size())).getAttribute("data-productid");
        // click on the random productID
        driver.findElement(By.xpath("//div[@data-productid='%s']".formatted(randomItem))).click();
        sleepingTime(2);

        // get Name, Price and URL of the product
        String productName = driver.findElement(By.className("o-productDetail__description")).getText();
        String productPrice = driver.findElement(By.className("m-price__new")).getText();
        String productURL = driver.getCurrentUrl();

        // write the product details to the txt file
        try {
            writingTxt.txt(productName, productPrice, productURL);
        } catch (Exception e) {
        }

        // choose the first (among available sizes) size from the list of sizes
        try {
            List<WebElement> sizeOptions = driver.findElements(By.xpath("//*[@id='sizes']/div/*"));
            for (WebElement size : sizeOptions) {
                if (!size.getAttribute("class").equals("m-variation__item -disabled")) {
                    size.click();
                    sleepingTime(1);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("There are no sizes left for this product!");
        }

        driver.findElement(By.id("addBasket")).click(); // click on the add to cart button
        sleepingTime(1);
        driver.navigate().to("https://www.beymen.com/cart"); // go to the cart page
        sleepingTime(2);
        
        // get the price of the product from the cart page
        String cartPrice = driver.findElement(By.className("m-productPrice__salePrice")).getText();

        // compare the price of the product from the cart page with the price of the product from the search page
        assertTrue(cartPrice.equals(productPrice));

        try {
            driver.findElement(By.xpath("//*[@id='quantitySelect0-key-0']/option[2]")).click(); // click on the quantity dropdown and select 2
            sleepingTime(2);
        } catch (Exception e) {
            System.out.println("There is only 1 item left in the stocks, consider yourself lucky!");
        }
 
        driver.findElement(By.id("removeCartItemBtn0-key-0")).click(); // click on the remove the item button
        sleepingTime(1);

        // checks if the cart is empty
        List<WebElement> cartList = driver.findElements(By.className("m-basket__item"));
        if (cartList.size() > 0) {
            System.out.println("There are " + cartList.size() + " items in the cart.");
        } else {
            System.out.println("There are no items in the cart");
        }

        // close the browser
        sleepingTime(3);
        driver.quit();
    }
}