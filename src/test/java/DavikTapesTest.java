import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DavikTapesTest
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
        driver.navigate().to(HOME_PAGE_URL);
    }

    @ParameterizedTest
    @CsvSource({"Company,About us", "Products, Developing Customized Products", "Industries, Agriculture", "Knowledge, Articles", "CONTACT, CONTACT", "Home, "})
    public void topMenuOptionsTest(String menuName, String expectedSubMenu)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if(menuName.equalsIgnoreCase("Home"))
        {
            WebElement element = driver.findElement(By.xpath("//a[contains(text(),'" + menuName + "')]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
            wait.until(ExpectedConditions.urlContains(HOME_PAGE_URL));
        }
        else {
            WebElement element = driver.findElement(By.xpath("//a[contains(text(),'" + menuName + "')]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '" + expectedSubMenu + "')]"))));

            element.click();
            wait.until(ExpectedConditions.urlContains(menuName.toLowerCase()));
        }
    }


}
