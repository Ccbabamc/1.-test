import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginTests {

    @Test
    @Description("Login test with invalid credentials")
    public void login() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 saniye bekleme süresi

        driver.get("https://www.trendyol.com/");

        closePopup(driver);
        clickLoginButton(driver);
        enterCredentials(driver, "test@gmail.com", "deneme");
        submitLogin(driver);

        // Hata mesajını bekleme ve doğrulama
        WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='error-box-wrapper']/span[2]")));
        String text = errorBox.getText();
        System.out.println(text);

        Assert.assertEquals("E-posta adresiniz ve/veya şifreniz hatalı.", text);

        driver.quit();
    }

    @Step("Close popup")
    public void closePopup(WebDriver driver) {
        driver.findElement(By.className("modal-close")).click();
    }

    @Step("Click login button")
    public void clickLoginButton(WebDriver driver) {
        driver.findElement(By.className("link-text")).click();
    }

    @Step("Enter credentials: {email}, {password}")
    public void enterCredentials(WebDriver driver, String email, String password) {
        driver.findElement(By.name("login email")).sendKeys(email);
        driver.findElement(By.name("login-password")).sendKeys(password);
    }

    @Step("Submit login")
    public void submitLogin(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id='login-register']/div[3]/div[1]/form/button")).click();
    }
}