package webautomation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class AppTest 
{
    public static void main(String[] args) {
        System.out.println("Hello World!");
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();

        driver.get("http://www.beymen.com");

    }





    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
