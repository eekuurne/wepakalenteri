package calendar.front;

import java.util.concurrent.TimeUnit;
import org.fluentlenium.adapter.FluentTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumLoginTest extends FluentTest {

    public WebDriver driver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return driver;
    }

    @LocalServerPort
    private Integer port;

    @Test
    public void ifYouAreUserYouCanLogInTest() {

        driver.get("http://localhost:" + port + "/login");
        WebElement id = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id.sendKeys("User1");
        pass.sendKeys("Password");
        button.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));

    }

    @Test
    public void ifYouAreNotUserYouCanNotLogInTest() {

        driver.get("http://localhost:" + port + "/login");
        WebElement id = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id.sendKeys("Miksi");
        pass.sendKeys("Enpaasesisaan");
        button.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/login?error"));

    }

    @Test
    public void registerationWorksTest() {

        driver.get("http://localhost:" + port + "/login");

        click(find("a").first());
        assertTrue(driver.getCurrentUrl().endsWith("/register"));

        WebElement id = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement passConf = driver.findElement(By.name("passwordConfirm"));
        WebElement button = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id.sendKeys("Hello");
        pass.sendKeys("MinaTaallaHei");
        passConf.sendKeys("MinaTaallaHei");
        button.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        WebElement id2 = driver.findElement(By.name("username"));
        WebElement pass2 = driver.findElement(By.name("password"));
        WebElement button2 = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id2.sendKeys("Hello");
        pass2.sendKeys("MinaTaallaHei");
        button2.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));

    }

    @Test
    public void canLogoutTest() {
        driver.get("http://localhost:" + port + "/login");

        click(find("a").first());
        assertTrue(driver.getCurrentUrl().endsWith("/register"));

        WebElement id = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement passConf = driver.findElement(By.name("passwordConfirm"));
        WebElement button = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id.sendKeys("Juuseri");
        pass.sendKeys("Salasana");
        passConf.sendKeys("Salasana");
        button.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        WebElement id2 = driver.findElement(By.name("username"));
        WebElement pass2 = driver.findElement(By.name("password"));
        WebElement button2 = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id2.sendKeys("Juuseri");
        pass2.sendKeys("Salasana");
        button2.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }
}
