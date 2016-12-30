package calendar.front;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
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
public class SeleniumEventTest extends FluentTest {
    
    public WebDriver driver = new HtmlUnitDriver();
    
    @Override
    public WebDriver getDefaultDriver() {
        return driver;
    }
    
    @LocalServerPort
    private Integer port;
    
    @Test
    public void canAddEventTest() {
        //create user
        driver.get("http://localhost:" + port + "/login");
        
        click(find("a").first());
        assertTrue(driver.getCurrentUrl().endsWith("/register"));
        
        WebElement id = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement passConf = driver.findElement(By.name("passwordConfirm"));
        WebElement button = driver.findElement(By.xpath("/html/body/div/form/button"));
        
        id.sendKeys("Nallekarhu");
        pass.sendKeys("Salasana");
        passConf.sendKeys("Salasana");
        button.submit();
        
        assertTrue(driver.getCurrentUrl().endsWith("/login"));
        
        WebElement id2 = driver.findElement(By.name("username"));
        WebElement pass2 = driver.findElement(By.name("password"));
        WebElement button2 = driver.findElement(By.xpath("/html/body/div/form/button"));
        
        id2.sendKeys("Nallekarhu");
        pass2.sendKeys("Salasana");
        button2.submit();
        
        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));

        //add a new event
        driver.findElement(By.linkText("New event")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/events/create"));
        
        WebElement title = driver.findElement(By.name("title"));
        WebElement place = driver.findElement(By.name("place"));
        WebElement description = driver.findElement(By.name("description"));

        title.sendKeys("Otsikko");
        place.sendKeys("Paikka");
        description.sendKeys("Kuvaus");
        
        WebElement addEventButton = driver.findElement(By.xpath("/html/body/div/form/button"));
        addEventButton.submit();

        //check if we are on the event's page
        assertTrue(pageSource().contains("Otsikko"));
        assertTrue(pageSource().contains("Paikka"));
        assertTrue(pageSource().contains("Kuvaus"));
        assertTrue(pageSource().contains("Invite a friend to participate"));

        //add new comment
        WebElement comment = driver.findElement(By.name("message"));
        WebElement addCommentButton = driver.findElement(By.xpath("/html/body/div/div/form/button"));
        
        comment.sendKeys("Odotan innolla tapaamista!");
        addCommentButton.submit();
        
        assertTrue(pageSource().contains("Odotan innolla tapaamista!"));        
        
        
        //go to frontpage and see if the event's link works
        driver.findElement(By.linkText("Calendar")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));
        
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/a")).click();
        assertTrue(pageSource().contains("Otsikko"));
        assertTrue(pageSource().contains("Paikka"));
        assertTrue(pageSource().contains("Kuvaus"));
        assertTrue(pageSource().contains("Invite a friend to participate"));
        assertTrue(pageSource().contains("Odotan innolla tapaamista!"));
        
        
        //can edit event page
        WebElement editButton = driver.findElement(By.xpath("/html/body/div/div/div/a"));
        editButton.click();
        
        assertTrue(driver.getCurrentUrl().endsWith("/edit"));
        
        WebElement newTitle = driver.findElement(By.id("title"));
        WebElement newPlace = driver.findElement(By.id("place"));
        WebElement newDescription = driver.findElement(By.id("description"));
        WebElement saveChangesButton = driver.findElement(By.id("edit-event-add"));
        
        
        newTitle.sendKeys("uusi otsikko");
        newPlace.sendKeys("uusi paikka");
        newDescription.sendKeys("uusi kuvaus");
        saveChangesButton.submit();
        
        assertTrue(pageSource().contains("uusi otsikko"));
        assertTrue(pageSource().contains("uusi paikka"));
        assertTrue(pageSource().contains("uusi kuvaus"));
        assertTrue(pageSource().contains("Invite a friend to participate"));
        assertTrue(pageSource().contains("Odotan innolla tapaamista!"));
        
        
        
        
    }

}
