import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest
{
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static  WebDriver driver;

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
    public void homePageURLTest()
    {
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "URLs do not match");
    }

    @Test
    public void findEmailFieldTest()
    {
        //WebElement element = driver.findElement(By.id("email"));
        //driver.findElement(By.id("email")).sendKeys("aaa@gmail.com");
        //WebElement element = driver.findElement(By.linkText("Create a Page"));
        //WebElement element = driver.findElement(By.partialLinkText("a Page"));
        //WebElement element = driver.findElement(By.cssSelector("input[name = 'email']"));
        List<WebElement> element = driver.findElements(By.className("inputtext"));
        System.out.println(element.size());
        assertNotNull(element);
    }

    @Test
    public void  findElementsByXpathTest()
    {
        WebElement emailElement = driver.findElement(By.xpath("//input[@placeholder='Email or phone number']"));
        assertNotNull(emailElement);
        WebElement passwordElement = driver.findElement(By.xpath("//input[@type='password']"));
        assertNotNull(passwordElement);

        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);


        WebElement forgotPassElement = driver.findElement(By.xpath("//a[text()='Forgot password?']"));
        assertNotNull(forgotPassElement);
        WebElement createNewAccButton = driver.findElement(By.xpath("//*[text()='Create new account']"));
        assertNotNull(createNewAccButton);
    }

    @Test
    public void loginScreenTest()
    {
        WebElement emailElement = driver.findElement(By.xpath("//input[@placeholder='Email or phone number']"));
        assertNotNull(emailElement);
        emailElement.sendKeys("aaa@gmail.com");
        String emailValue = emailElement.getAttribute("value");
        assertEquals("aaa@gmail.com", emailValue);

        WebElement passwordElement = driver.findElement(By.xpath("//input[@type='password']"));
        assertNotNull(passwordElement);
        passwordElement.sendKeys("1234567");
        String passValue = passwordElement.getAttribute("value");
        assertEquals("1234567", passValue);

        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);
        loginButtonElement.click();
    }

    @Test
    public void signupTest()
    {
        driver.findElement(By.xpath("//*[text()= 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
    }

    @Test
    public void genderTest() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";

        // parent-siblings xpath:
        //$x("//*[text()='Female']//following-sibling::*[@type='radio']");

        // siblings-parent xpath:
        //$x("//input[@type='radio']//preceding-sibling::*[text()='Female']")

        String maleXpath = "//*[@name='sex' and @value=2]";

        driver.findElement(By.xpath("//*[text()= 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);

        // verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        // verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);
    }

    @Test
    public void errorMessageTest() throws InterruptedException {

        driver.findElement(By.xpath("//*[text()= 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@name='websubmit']")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@name='reg_email__']")).click();
        //driver.findElement(By.xpath("//*[contains(text(),'Mobile number or')]")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(error);

    }

    @Test
    public void yearOfBirthTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()= 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);


        //descendant xpath , parent-descendant
        //$x("//*[@data-name='birthday_wrapper']//descendant::*[@id='year']")
        // ancestor-parent
        //$x("//*[@id='year']//ancestor::*[@data-name='birthday_wrapper']")
        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text()='1990']")).click();

        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");
        assertEquals("1990", yearValue);

    }

    @ParameterizedTest
    @ValueSource(strings = {"1905","1950", "2020", "2024"})
    public void yearOfBirthTestParametrized(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text()= 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text()='"+ yearInput + "']")).click();

        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");
        assertEquals(yearInput, yearValue);

    }

    @Test
    public void actionTest()
    {
        driver.get("https://daviktapes.com/");
        pause();
        WebElement element = driver.findElement(By.xpath("//a[text()='Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
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

    public void smallPause()
    {
        try {
            Thread.sleep(1000);
        } catch (Exception err)
        {
            System.out.println("Something went wrong");
        }
    }

    public void largePause()
    {
        try {
            Thread.sleep(10000);
        } catch (Exception err)
        {
            System.out.println("Something went wrong");
        }
    }




}
