import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class TestAuthorizationSeleniumFirefox {
    private String testUrl;
    private WebDriver driver;

    @BeforeTest
    public void prepare() {
        WebDriverManager.firefoxdriver().setup();
        testUrl = "https://ib.rencredit.ru/#/login";
        driver = new FirefoxDriver();
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
