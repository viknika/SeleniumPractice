import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DavikTest
{

    private static final String HOME_PAGE_URL = "https://daviktapes.com/";

    private static WebDriver driver;


    @BeforeAll
    public static void classSetup()
    {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);

    }

    @AfterAll
    public static void classTearDown()
    {
        SharedDriver.closeDriver();
    }



    @AfterEach
    public void testTeatDown()
    {
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void actionTest()
    {
        // pause();
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Company']")));

        //waiting for element to no longer be visible
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[text()='Company']")));


        WebElement element = driver.findElement(By.xpath("//a[text()='Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void waitAndClickTest()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Company']"))).click();
        pause();
    }

    public void pause()
    {
        try {
            Thread.sleep(5000);
        } catch (Exception err)
        {
            System.out.println("Something went wrong");
        }
    }
}
