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

    @Test@Step
    public void login() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 saniye bekleme süresi

        driver.get("https://www.trendyol.com/");

        // Pop-up'ı kapatma
        driver.findElement(By.className("modal-close")).click();

        // Giriş butonuna tıklama
        driver.findElement(By.className("link-text")).click();

        // Giriş bilgilerini girme
        driver.findElement(By.name("login email")).sendKeys("test@gmail.com");
        driver.findElement(By.name("login-password")).sendKeys("deneme");

        // Giriş butonuna tıklama
        driver.findElement(By.xpath("//*[@id='login-register']/div[3]/div[1]/form/button")).click();

        // Hata mesajını bekleme ve doğrulama
        WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='error-box-wrapper']/span[2]")));
        String text = errorBox.getText();
        System.out.println(text);


        Assert.assertEquals("E-posta adresiniz ve/veya şifreniz hatalı.", text);

        driver.quit();
    }
}