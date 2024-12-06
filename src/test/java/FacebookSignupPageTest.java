import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacebookSignupPageTest
{
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

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


    public void setupPage()
    {
        driver.findElement(By.xpath("//*[text()= 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
    }

    @Test
    public void errorMessagesTest() throws InterruptedException {
        setupPage();

        driver.findElement(By.xpath("//button[@name='websubmit']")).click();

        Thread.sleep(1000);
        //First name empty field error message
        driver.findElement(By.xpath("//input[@name='firstname']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'your name')]")));

        //Last name empty field error message
        driver.findElement(By.xpath("//input[@name='lastname']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'your name')]")));


        Thread.sleep(1000);

        //DOB error message
        driver.findElement(By.xpath("//div[@id='birthday_wrapper']//i[@class='_5dbc _5k_6 img sp_98fCI7IVTTz sx_54513f']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'real birthday')]")));

        //Gender error message
        driver.findElement(By.xpath("//i[@class='_5dbc _8esb img sp_98fCI7IVTTz sx_54513f']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'choose a gender')]")));


        //Mobile number or email empty field error message
        driver.findElement(By.xpath("//input[@name='reg_email__']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'to reset')]")));

        Thread.sleep(1000);
        //Password empty field error message
        driver.findElement(By.xpath("//input[@name='reg_passwd__']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'combination of at least')]")));


    }


}
