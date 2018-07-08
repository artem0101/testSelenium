import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AuthorizationTest {
    private static String firstResult = "Неверный логин или пароль";
    private static String secondResult = "Превышено максимальное количество попыток неудачного ввода логина и пароля, " +
            "пользователь был временно заблокирован. Разблокировка пользователя произойдет спустя 20 мин";
    private static String lastResult = "Превышено максимальное количество попыток неудачного ввода логина и пароля, " +
            "пользователь был заблокирован. Для разблокировки пользователя обратитесь, пожалуйста, в контактный центр Банка по телефону 8 (800)200-0-981";

    static void mainTest(WebDriver driver) {
        try {
            Thread.sleep(5000);
            WebElement elementLogin = driver.findElement(By.name("username"));
            elementLogin.sendKeys("qwerty1");

            WebElement elementPassword = driver.findElement(By.name("password"));
            elementPassword.sendKeys("qwerty1234");

            WebElement elementButton = driver.findElement(By.xpath("//button[@class='button button-lime']"));
            elementButton.click();

            Thread.sleep(1000);
            WebElement elementResultLabel = driver.findElement(By.cssSelector("div.empty-data-error__text"));

            System.out.println(elementResultLabel.getText());

            if (elementResultLabel.getText().equalsIgnoreCase(firstResult)) {
                System.out.println("Тест выполнен успешно, авторизации не произошло");
                Assert.assertEquals(firstResult, elementResultLabel.getText());
            } else if (elementResultLabel.getText().equalsIgnoreCase(secondResult)){
                System.out.println("Тест выполнен успешно, авторизации не произошло");
                Assert.assertEquals(secondResult, elementResultLabel.getText());
            } else if (elementResultLabel.getText().equalsIgnoreCase(lastResult)) {
                System.out.println("Тест выполнен успешно, авторизации не произошло");
                Assert.assertEquals(secondResult, elementResultLabel.getText());
            } else {
                System.out.println("Произошла авторизация");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void moreTest(WebDriver driver) {
        try {
            Thread.sleep(2000);

            WebElement elementMethod = driver.findElement(By.className("form"));
            elementMethod.submit();

            Thread.sleep(1000);

            WebElement elementPopup = driver.findElement(By.className("popup__inner"));
            Assert.assertEquals(elementPopup.getText(), "Неверный логин или пароль");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
