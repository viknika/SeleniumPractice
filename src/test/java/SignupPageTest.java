import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SignupPageTest
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

    @Test
    public void createNewAccountButtonTest() throws InterruptedException
    {
        WebElement newAccButton = driver.findElement(By.xpath("//a[@data-testid = 'open-registration-form-button']"));
        assertNotNull(newAccButton);
        newAccButton.click();
        Thread.sleep(1000);

        assertEquals("https://www.facebook.com/r.php", driver.getCurrentUrl(), "URLs do not match");
    }

    @Test
    public void  findElementsByXpathTest() throws InterruptedException {

        createNewAccountButtonTest();

        WebElement firstNameTextBox = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameTextBox);

        WebElement lastNameTextBox = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameTextBox);

        //driver.findElement(By.xpath("//select[@name='birthday_month']/option[@value='10']")).click();
        WebElement birthMonthTextBox = driver.findElement(By.xpath("//select[@name='birthday_month']"));
        assertNotNull(birthMonthTextBox);

        WebElement birthDayTextBox = driver.findElement(By.xpath("//select[@name='birthday_day']"));
        assertNotNull(birthDayTextBox);

        WebElement birthYearTextBox = driver.findElement(By.xpath("//select[@name='birthday_year']"));
        assertNotNull(birthYearTextBox);

        WebElement genderFemaleTextBox = driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][1]"));
        assertNotNull(genderFemaleTextBox);
        assertEquals("Female", driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][1]")).getText());

        WebElement genderMaleTextBox = driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][2]"));
        assertNotNull(genderMaleTextBox);
        assertEquals("Male", driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][2]")).getText());

        WebElement genderCustomTextBox = driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][3]"));
        assertNotNull(genderCustomTextBox);
        assertEquals("Custom", driver.findElement(By.xpath("//span[@class='_5k_2 _5dba'][3]")).getText());

        WebElement emailOrPhoneTextBox = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(emailOrPhoneTextBox);

        WebElement newPassTextBox = driver.findElement(By.xpath("//input[@id='password_step_input']"));
        assertNotNull(newPassTextBox);

        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton);
    }
// First Name input field test
    @ParameterizedTest
    @MethodSource("validFirstName")
    public void validFirstNameTest(String firstName) throws InterruptedException {
        createNewAccountButtonTest();

        WebElement firstNameTextBox = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameTextBox);
        SignUpPage.enterFirstName(firstName,driver);
        assertEquals(firstName,firstNameTextBox.getAttribute("value") );
    }

    static Stream<String> validFirstName()
    {
        return Stream.of("Nika", "VeryLongNameneneneneneneneneneneneen", "AB","kuhsbcDDDD");
    }

    @ParameterizedTest
    @MethodSource("invalidFirstName")
    public void invalidFirstNameTest(String firstName) throws InterruptedException {
        createNewAccountButtonTest();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SignUpPage.enterFirstName(firstName, driver);
        });
    }

    static Stream<String> invalidFirstName()
    {
        return Stream.of("A", "", null, "   ","1235456DDDD", "$n*23Hnn", "123456", "%%%^&*((&^&");
    }


    // Last Name input field test

    @ParameterizedTest
    @MethodSource("validLastName")
    public void validLastNameTest(String lastName) throws InterruptedException {
        createNewAccountButtonTest();

        WebElement lastNameTextBox = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameTextBox);
        SignUpPage.enterLastName(lastName,driver);
        assertEquals(lastName,lastNameTextBox.getAttribute("value") );
    }

    static Stream<String> validLastName()
    {
        return Stream.of("Smith", "VeryLongNameneneneneneneneneneneneen", "A","kuhsbcDDDD");
    }

    @ParameterizedTest
    @MethodSource("invalidLastName")
    public void invalidLastNameTest(String lastName) throws InterruptedException {
        createNewAccountButtonTest();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SignUpPage.enterFirstName(lastName, driver);
        });
    }

    static Stream<String> invalidLastName()
    {
        return Stream.of("", null, "   ","1235456DDDD", "$n*23Hnn", "123456", "%%%^&*((&^&");
    }

    // Mobile number or email input field test

    @ParameterizedTest
    @MethodSource("validEmailOrPhone")
    public void validEmailOrPhoneTest(String emailOrPhone) throws InterruptedException
    {
        createNewAccountButtonTest();

        WebElement emailOrPhoneTextBox = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(emailOrPhoneTextBox);
        SignUpPage.enterEmailOrPhone(emailOrPhone, driver);
        assertEquals(emailOrPhone,emailOrPhoneTextBox.getAttribute("value") );
    }

    static Stream<String> validEmailOrPhone()
    {
        return Stream.of("aaa@rtyhj.com", "Ag1@gmail.com", "12357@DRF.CA", "2562289626", "+12569998566");
    }


    @ParameterizedTest
    @MethodSource("inValidEmailOrPhone")
    public void inValidEmailOrPhoneTest(String emailOrPhone) throws InterruptedException
    {
        createNewAccountButtonTest();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SignUpPage.enterEmailOrPhone(emailOrPhone, driver);
        });
    }

    static Stream<String> inValidEmailOrPhone()
    {
        return Stream.of("aaartyhj.com", "Ag1@gmailcom", "12357$%^1ggg@DRF.CA", "", null, "  ", "+2589", "1", "555555555555", "fgtfhyfgtr");
    }

    // Password input field test

    @ParameterizedTest
    @MethodSource("validPassword")
    public void validPasswordTest(String password) throws InterruptedException
    {
        createNewAccountButtonTest();

        WebElement newPassTextBox = driver.findElement(By.xpath("//input[@id='password_step_input']"));
        assertNotNull(newPassTextBox);
        SignUpPage.enterNewPassword(password,driver);

    }

    static Stream<String> validPassword()
    {
        return Stream.of("ggAA23%%", "jhg123@", "12357$%^1ggg@DRF.CA");
    }

    @ParameterizedTest
    @MethodSource("invalidPassword")
    public void invalidPasswordTest(String password) throws InterruptedException
    {
        createNewAccountButtonTest();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SignUpPage.enterNewPassword(password, driver);
        });

    }

    static Stream<String> invalidPassword()
    {
        return Stream.of("ggAA23", "jhg", "123569", null, "", " ", "$%^&*(&)", "ghyttt");
    }

    // gender tests

    @ParameterizedTest
    @CsvSource({"Female,,", "Male,,", "Custom, She,", "Custom, He,", "Custom, They,",  "Custom, She, Girl", "Custom, He, Boy", "Custom, They, They" })
    public void chooseGenderTest(String gender, String custGender, String optionalGender) throws InterruptedException
    {
        createNewAccountButtonTest();

        if(gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Male")) {
            SignUpPage.chooseGender(gender, driver);
        }
        else
        {
            if(optionalGender ==null || optionalGender.isBlank()) {
                SignUpPage.customGender(gender, custGender, driver);
            }
            else {
                SignUpPage.enterOptionalGender(optionalGender, driver);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("invalidGender")
    public void invalidGenderTest(String gender, String custGender, String optionalGender) throws InterruptedException {
        createNewAccountButtonTest();

        if(gender == null || gender.isBlank()) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                SignUpPage.chooseGender(gender, driver);
            });
        }
        else if(custGender == null || custGender.isBlank()) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SignUpPage.customGender(gender,custGender, driver);
        });
        }
        else if(optionalGender == null) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                SignUpPage.enterOptionalGender(null, driver);
            });
        }
    }

    static Stream<Arguments> invalidGender()
    {
        return Stream.of(
                Arguments.of(" ", null, null ),
                Arguments.of(null, "She", "Girl"),
                Arguments.of(null, null, "Girl"),
                Arguments.of("Custom", null, "Boy"),
                Arguments.of("Custom", " " , "Boy"),
                Arguments.of("Custom", null, null),
                Arguments.of("Custom", "They" ,null),
                Arguments.of("Custom", "They" ," ")


        );
    }

    @ParameterizedTest
    @MethodSource("signUpFormData")
    public void successSignUpFormTest(String firstName, String lastName, String gender, String custGender, String optionalGender, String emailOrPhone, String password) throws InterruptedException
    {
    createNewAccountButtonTest();

    SignUpPage.enterFirstName(firstName, driver);
    SignUpPage.enterLastName(lastName,driver);

        WebElement birthMonthTextBox = driver.findElement(By.xpath("//select[@name='birthday_month']"));
        birthMonthTextBox.click();
        driver.findElement(By.xpath("//select[@id='month']/option[@value='4']")).click();


        WebElement birthDayTextBox = driver.findElement(By.xpath("//select[@name='birthday_day']"));
        birthDayTextBox.click();
        driver.findElement(By.xpath("//select[@id='day']/option[@value='10']")).click();


        WebElement birthYearTextBox = driver.findElement(By.xpath("//select[@name='birthday_year']"));
        birthYearTextBox.click();
        driver.findElement(By.xpath("//select[@id='year']/option[@value='1980']")).click();


    SignUpPage.chooseGender(gender,driver);
    if(gender.equalsIgnoreCase("Custom"))
    {
        SignUpPage.customGender(gender,custGender,driver);
        if(optionalGender != null || !optionalGender.isBlank())
        {
            SignUpPage.enterOptionalGender(optionalGender,driver);
        }
    }
    SignUpPage.enterEmailOrPhone(emailOrPhone,driver);
    SignUpPage.enterNewPassword(password,driver);


    SignUpPage.clickSignUpButton(driver);

    }

    static Stream<Arguments> signUpFormData()
    {
        return Stream.of(
                //Arguments.of("Klava", "Petrova", "Custom","She", "Girl", "bjhjf159gh@gmail.com"," wert123!!")
                Arguments.of("Fedya", "Ivanovich", "Male", " ", " ","bbb1235@yahoo.com", "123ggg%")
        );
    }

//    @ParameterizedTest
//    @MethodSource("invalidSignUpFormData")
//    public void invalidSignUpFormTest(String firstName, String lastName, String gender, String custGender, String optionalGender, String emailOrPhone, String password) throws InterruptedException
//    {
//        createNewAccountButtonTest();
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//        SignUpPage.enterFirstName(firstName, driver);
//        });
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            SignUpPage.enterLastName(lastName, driver);
//        });
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            SignUpPage.enterEmailOrPhone(emailOrPhone, driver);
//        });
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            SignUpPage.enterNewPassword(password, driver);
//        });
//
//        if(gender == null || gender.isBlank()) {
//            Assertions.assertThrows(IllegalArgumentException.class, () -> {
//                SignUpPage.chooseGender(gender, driver);
//            });
//        }
//        else if(custGender == null || custGender.isBlank()) {
//            Assertions.assertThrows(IllegalArgumentException.class, () -> {
//                SignUpPage.customGender(gender,custGender, driver);
//            });
//        }
//        else if(optionalGender == null) {
//            Assertions.assertThrows(IllegalArgumentException.class, () -> {
//                SignUpPage.enterOptionalGender(optionalGender, driver);
//            });
//
//            SignUpPage.clickSignUpButton(driver);
//        }
//
//    }
//
//    static Stream<Arguments> invalidSignUpFormData()
//    {
//        return Stream.of(
//
//                Arguments.of(null, "Ivanovich", "Male", " ", " ","bbb1235@yahoo.com", "123ggg%"),
//                Arguments.of("Stiv", "Ivanovich", "Male", " ", " ",null, "123ggg%"),
//                Arguments.of("Stiv", null, "Male", " ", " ","agdtr$$@nat.com", "123ggg%"),
//                Arguments.of("Stiv", "Ivanovich", "Custom", " ", " ","agdtr$$@nat.com", "123ggg%")
//        );
//    }
}
