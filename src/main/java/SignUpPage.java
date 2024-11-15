import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

public class SignUpPage
{
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebDriver driver;


    public static void classSetup()
    {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    private static boolean isAlpha(String name)
    {
        if(name == null || name.isBlank())
        {
            throw new IllegalArgumentException("All fields are required.");
        }
        return name.matches("[a-zA-Z]+");
    }


    public static void enterFirstName(String firstName,WebDriver driver)
    {
        if(isAlpha(firstName) && firstName.length()>=2) {
            driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
        }
        else
        {
            throw new IllegalArgumentException("The name can contain letters only and length should be more then 1 letter");
        }
    }

    public static void enterLastName(String lastName,WebDriver driver)
    {
        if(isAlpha(lastName)) {
            driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
        }
        else
        {
            throw new IllegalArgumentException("The name can contain letters only");
        }
    }

    public static void enterEmailOrPhone(String emailOrPhone, WebDriver driver)
    {
        if(emailOrPhone != null && !emailOrPhone.isBlank() && emailOrPhone.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$|^(\\+1)?[0-9]{10}$") ) {
            driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys(emailOrPhone);
        }
        else
        {
            throw new IllegalArgumentException("Please enter a valid mobile number or email address");
        }
    }

    public static void enterNewPassword(String password, WebDriver driver)
    {
        if(password ==null || password.isBlank())
        {
            throw new IllegalArgumentException("All fields are required. Enter a password ");
        }
        if(password.length()>=6 && password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*\\W)[A-Za-z\\d\\W]{6,}$")) {
            driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys(password);
        }
        else
            throw new IllegalArgumentException("The password combination must be at least six numbers. letters and punctuation marks");
    }

    public static void chooseGender(String gender, WebDriver driver){
        if(gender == null || gender.isBlank())
        {
            throw new IllegalArgumentException("All fields are required. Choose the gender");
        }
        if (gender.equalsIgnoreCase("Female"))
        {
            driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][1]")).click();
        }
        else if (gender.equalsIgnoreCase("Male"))
        {
            driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][2]")).click();
        }
    }

    public static void customGender(String gender, String custGender, WebDriver driver)
    {
       //Thread.sleep(1000);
        if(gender == null || gender.isBlank())
        {
            throw new IllegalArgumentException("Please select gender");
        }

        driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][3]")).click();

        if(driver.findElement(By.xpath("//select/option[text()='Select your pronoun']")).isDisplayed() && driver.findElement(By.xpath("//input[@name='custom_gender']")).isDisplayed())
        {
            driver.findElement(By.xpath("//select/option[text()='Select your pronoun']")).click();
        }

        if(custGender == null || custGender.isBlank())
        {
            throw new IllegalArgumentException("Please select your pronoun");
        }
        switch ((custGender.toLowerCase())) {
            case "she":
                driver.findElement(By.xpath("//select[@id='preferred_pronoun']/option[@value='1']")).click();
                break;
            case "he":
                driver.findElement(By.xpath("//select[@id='preferred_pronoun']/option[@value='2']")).click();
                break;
            case "they":
                driver.findElement(By.xpath("//select[@id='preferred_pronoun']/option[@value='6']")).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid pronoun");

        }
    }

    public static void enterOptionalGender(String optionalGender,WebDriver driver) {
        driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][3]")).click();
        if(driver.findElement(By.xpath("(//input[@id='sex'])[3]")).isSelected())
        {
            if (optionalGender != null)
            {
                driver.findElement(By.xpath("//input[@id='custom_gender']")).sendKeys(optionalGender);
            }
            else {
                throw new IllegalArgumentException(" null not allowed");
            }
        }

    }

    public static void clickSignUpButton(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        Thread.sleep(20000);
    }


}
