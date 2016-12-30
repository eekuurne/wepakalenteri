package calendar.front;

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
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumParticipationTest extends FluentTest {

    public WebDriver driver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return driver;
    }

    @LocalServerPort
    private Integer port;

    @Test
    public void FriendTest() {
        //create first user Number1
        driver.get("http://localhost:" + port + "/login");

        click(find("a").first());
        assertTrue(driver.getCurrentUrl().endsWith("/register"));

        WebElement id = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement passConf = driver.findElement(By.name("passwordConfirm"));
        WebElement button = driver.findElement(By.xpath("/html/body/div/form/button"));

        id.sendKeys("Number1");
        pass.sendKeys("Salasana");
        passConf.sendKeys("Salasana");
        button.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        WebElement id2 = driver.findElement(By.name("username"));
        WebElement pass2 = driver.findElement(By.name("password"));
        WebElement button2 = driver.findElement(By.xpath("/html/body/div/form/button"));

        id2.sendKeys("Number1");
        pass2.sendKeys("Salasana");
        button2.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        //create second user Number2
        driver.get("http://localhost:" + port + "/login");

        click(find("a").first());
        assertTrue(driver.getCurrentUrl().endsWith("/register"));

        WebElement id3 = driver.findElement(By.name("username"));
        WebElement pass3 = driver.findElement(By.name("password"));
        WebElement passConf3 = driver.findElement(By.name("passwordConfirm"));
        WebElement button3 = driver.findElement(By.xpath("/html/body/div/form/button"));

        id3.sendKeys("Number2");
        pass3.sendKeys("Salasana");
        passConf3.sendKeys("Salasana");
        button3.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        WebElement id4 = driver.findElement(By.name("username"));
        WebElement pass4 = driver.findElement(By.name("password"));
        WebElement button4 = driver.findElement(By.xpath("/html/body/div/form/button"));

        id4.sendKeys("Number2");
        pass4.sendKeys("Salasana");
        button4.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));
        driver.findElement(By.linkText("Profile")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/profile"));

        assertFalse(pageSource().contains("Number1"));

        //add a new friendrequest (Number1) from user Kayttaja2
        WebElement friend = driver.findElement(By.name("username"));
        WebElement addFriendReqButton = driver.findElement(By.xpath("//input[contains(@value, 'Add')]"));

        friend.sendKeys("Number1");
        addFriendReqButton.submit();

        assertTrue(pageSource().contains("Number1"));

        //logout as Number2 and login as Number1
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        WebElement id5 = driver.findElement(By.name("username"));
        WebElement pass5 = driver.findElement(By.name("password"));
        WebElement button5 = driver.findElement(By.xpath("/html/body/div/form/button"));

        id5.sendKeys("Number1");
        pass5.sendKeys("Salasana");
        button5.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));

        //logged in as Number1. Go to profile-page and see friendrequest from Number2
        driver.findElement(By.linkText("Profile")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/profile"));

        assertTrue(pageSource().contains("Number2"));

        //accept friendrequest from Number2
        WebElement acceptFriendReqButton = driver.findElement(By.xpath("//input[contains(@value, 'Accept')]"));
        acceptFriendReqButton.submit();

        assertTrue(pageSource().contains("Number2"));

        //create new event
        driver.findElement(By.id("navigation-new-event")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/events/create"));

        WebElement title = driver.findElement(By.name("title"));
        WebElement place = driver.findElement(By.name("place"));
        WebElement description = driver.findElement(By.name("description"));

        title.sendKeys("Kemut");
        place.sendKeys("Teilla");
        description.sendKeys("On varmasti hauskaa");

        WebElement addEventButton = driver.findElement(By.id("new-event-add"));
        addEventButton.submit();

        //check if we are on the event's page
        assertTrue(pageSource().contains("Kemut"));
        assertTrue(pageSource().contains("Teilla"));
        assertTrue(pageSource().contains("On varmasti hauskaa"));
        assertTrue(pageSource().contains("Invite a friend to participate"));

        //send user Number2 an invitation to participate
        WebElement name = driver.findElement((By.xpath("//input[contains(@name, 'user')]")));
        WebElement addParticipationButton = driver.findElement(By.xpath("//input[contains(@value, 'Add')]"));

        name.sendKeys("Number2");
        addParticipationButton.submit();

        assertTrue(pageSource().contains("Number2"));

        //delete participation invitation
        WebElement deleteParticipationButton = driver.findElement(By.linkText("Cancel"));
        deleteParticipationButton.click();
        assertFalse(pageSource().contains("Number2"));

        //try to send participation invitation to user (User1) who is not your friend
        WebElement name2 = driver.findElement((By.xpath("//input[contains(@name, 'user')]")));
        WebElement addParticipationButton2 = driver.findElement(By.xpath("//input[contains(@value, 'Add')]"));

        name2.sendKeys("User1");
        addParticipationButton2.submit();
        assertTrue(pageSource().contains("that user is not your friend"));

    }
}
