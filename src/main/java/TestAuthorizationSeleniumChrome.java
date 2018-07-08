import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAuthorizationSeleniumChrome {
    private String testUrl;
    private WebDriver driver;

    @BeforeTest
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        testUrl = "https://ib.rencredit.ru/#/login";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(testUrl);
    }

    @Test
    public void titleTest() {
        AuthorizationTest.mainTest(driver);
    }

    @Test
    public void secondTest() {
        AuthorizationTest.moreTest(driver);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
