package pl.two.jaquiz.gui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.List;

public class DeleteQuizTest {

    private WebDriver webDriver;

    private String url;

    private AddQuizTest addQuizTest;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:/Users/Jelon/Downloads/Nowy syf/geckodriver-v0.26.0-win64/geckodriver.exe");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("marionatte", false);
        FirefoxOptions opt = new FirefoxOptions();
        opt.merge(dc);
        webDriver = new FirefoxDriver(opt);


        url = "http://localhost:8080/index";

    }
    @Test
    public void addSimpleQuestionTest() {
        webDriver.get(url);

        System.out.println("Application opened");
        System.out.println("Page Title is : " + webDriver.getTitle());



        webDriver.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
