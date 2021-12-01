package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver = null;
    String URL = "https://hepsiburada.com";

    @Given("^I Open To (Chrome|Firefox) Browser$")
    public void open_chrome_browser(String browser) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path is :" + projectPath);
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/Drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            //driver.manage().window().maximize();
            System.out.println("Chrome Browser Is Opened");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/src/test/resources/Drivers/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            //driver.manage().window().maximize();
            System.out.println("Firefox Browser Is Opened");
        }
    }


    @And("^I go to application")
    public void go_to_link() {
        driver.navigate().to(URL);
    }

    @After(value = "not @API", order = -1)
    public void close_browser() {
        driver.close();
        driver.quit();
    }


    @After(order = 1)
    public void takeScreenAfter(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] data =((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String screenshotName = scenario.getName();
            scenario.attach(data,"image/png",screenshotName);

        }
    }
}
