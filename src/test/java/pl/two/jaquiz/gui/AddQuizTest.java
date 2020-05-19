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

public class AddQuizTest {

    private WebDriver webDriver;


    private String url;

    private String questionContent;

    private List<String> answers;

    private String quizName;


    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:/Users/Jelon/Downloads/Nowy syf/geckodriver-v0.26.0-win64/geckodriver.exe");


        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("marionatte", false);
        FirefoxOptions opt = new FirefoxOptions();
        opt.merge(dc);
        webDriver = new FirefoxDriver(opt);


        url = "http://localhost:8080/new-quiz";
        questionContent = "What is your favorite dish?";
        answers = Arrays.asList("Pizza", "Spaghetti", "Noodles", "Fish");
        quizName = "Test quiz";
    }

    @Test
    public void addSimpleQuestionTest() {
        webDriver.get(url);

        System.out.println("Application opened");
        System.out.println("Page Title is : " + webDriver.getTitle());

        WebElement questionContentFromWeb = webDriver.findElement(By.id("questionContent"));
        questionContentFromWeb.clear();
        questionContentFromWeb.sendKeys(questionContent);

        List<WebElement> answerFromWeb = webDriver.findElements(By.id("answer"));

        int i = 0;
        for (WebElement element : answerFromWeb) {
            element.clear();
            element.sendKeys(answers.get(i));
            i++;
        }
        webDriver.findElement(By.xpath("//input[@value='Add question']")).click();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
