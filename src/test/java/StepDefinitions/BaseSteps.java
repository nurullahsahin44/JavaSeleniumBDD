package StepDefinitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class BaseSteps {

    public static WebDriver driver = null;

    public BaseSteps() {
        this.driver = Hooks.driver;
    }


    @And("^I wait to (\\d+) seconds$")
    public void Sleep(int second) throws InterruptedException {
        long time = second * 1000;
        Thread.sleep(time);
    }

    @And("^screen to maximize$")
    public void maximize() {
        driver.manage().window().maximize();
    }

    @And("^switch to new window$")
    public void switchTab() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }

}
