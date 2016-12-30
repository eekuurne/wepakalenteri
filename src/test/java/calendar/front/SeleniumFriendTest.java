
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
public class SeleniumFriendTest extends FluentTest{
    
   public WebDriver driver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return driver;
    }

    @LocalServerPort
    private Integer port;



    @Test
    public void FriendTest() {
        //create first user Kayttaja1
        driver.get("http://localhost:" + port + "/login");

        click(find("a").first());
        assertTrue(driver.getCurrentUrl().endsWith("/register"));

        WebElement id = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement passConf = driver.findElement(By.name("passwordConfirm"));
        WebElement button = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id.sendKeys("Kayttaja1");
        pass.sendKeys("Salasana");
        passConf.sendKeys("Salasana");
        button.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        WebElement id2 = driver.findElement(By.name("username"));
        WebElement pass2 = driver.findElement(By.name("password"));
        WebElement button2 = driver.findElement(By.xpath("/html/body/div/form/div/button"));
        
        id2.sendKeys("Kayttaja1");
        pass2.sendKeys("Salasana");
        button2.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/login"));
        
        //create second user Kayttaja2
        driver.get("http://localhost:" + port + "/login");

        click(find("a").first());
        assertTrue(driver.getCurrentUrl().endsWith("/register"));

        WebElement id3 = driver.findElement(By.name("username"));
        WebElement pass3 = driver.findElement(By.name("password"));
        WebElement passConf3 = driver.findElement(By.name("passwordConfirm"));
        WebElement button3 = driver.findElement(By.xpath("/html/body/div/form/div/button"));

        id3.sendKeys("Kayttaja2");
        pass3.sendKeys("Salasana");
        passConf3.sendKeys("Salasana");
        button3.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/login"));

        WebElement id4 = driver.findElement(By.name("username"));
        WebElement pass4 = driver.findElement(By.name("password"));
        WebElement button4 = driver.findElement(By.xpath("/html/body/div/form/div/button"));
        
        id4.sendKeys("Kayttaja2");
        pass4.sendKeys("Salasana");
        button4.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));
        driver.findElement(By.linkText("Profile")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/profile"));
        
        assertFalse(pageSource().contains("Kayttaja1"));
        
        //add a new friendrequest (Kayttaja1) to user Kayttaja2
        WebElement friend = driver.findElement(By.name("username"));  
        WebElement addFriendReqButton = driver.findElement(By.xpath("//input[contains(@value, 'Add')]")); 
        
        friend.sendKeys("Kayttaja1");
        addFriendReqButton.submit();            
        
        assertTrue(pageSource().contains("Kayttaja1"));
        
        //remove friendrequest (target=Kayttaja1)
        WebElement removeFriendReqButton = driver.findElement(By.xpath("//input[contains(@value, 'Cancel')]")); 
        
        removeFriendReqButton.submit();
        
        assertFalse(pageSource().contains("Kayttaja1"));
        
        //readd a friendrequest (Kayttaja1) to user Kayttaja2
        WebElement friend2 = driver.findElement(By.name("username"));  
        WebElement addFriendReqButton2 = driver.findElement(By.xpath("//input[contains(@value, 'Add')]")); 
        
        friend2.sendKeys("Kayttaja1");
        addFriendReqButton2.submit();            
        
        assertTrue(pageSource().contains("Kayttaja1"));
        
        //logout as Kayttaja2 and login as Kayttaja1
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/login"));
        
        WebElement id5 = driver.findElement(By.name("username"));
        WebElement pass5 = driver.findElement(By.name("password"));
        WebElement button5 = driver.findElement(By.xpath("/html/body/div/form/div/button"));
        
        id5.sendKeys("Kayttaja1");
        pass5.sendKeys("Salasana");
        button5.submit();

        assertTrue(driver.getCurrentUrl().endsWith("/calendar"));
        
        //logged in as Kayttaja1. Go to profile-page and see friendrequest from Kayttaja2
        driver.findElement(By.linkText("Profile")).click();
        assertTrue(driver.getCurrentUrl().endsWith("/profile"));
        
        assertTrue(pageSource().contains("Kayttaja2"));
        
        //accept friendrequest from kayttaja2
        WebElement acceptFriendReqButton = driver.findElement(By.xpath("//input[contains(@value, 'Accept')]")); 
        acceptFriendReqButton.submit();
        
        assertTrue(pageSource().contains("Kayttaja2"));
        
        //remove friend kayttaja2
        WebElement removeFriendButton = driver.findElement(By.xpath("//input[contains(@value, 'Remove')]")); 
        removeFriendButton.submit();
        
        assertFalse(pageSource().contains("Kayttaja2"));
        
        
        
        
        
        

    }
}
