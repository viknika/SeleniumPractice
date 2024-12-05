import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SharedDriver
{
    private static WebDriver webDriver;

    public static WebDriver getWebDriver()
    {
        if(webDriver == null) {
            WebDriverManager.chromedriver().setup();
            //System.setProperty("webdriver.chrome.driver","C:/Users/Veronika/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
           // webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        return webDriver;
    }

    public static void closeDriver()
    {
        if(webDriver != null){
        webDriver.close();}
    }

}
