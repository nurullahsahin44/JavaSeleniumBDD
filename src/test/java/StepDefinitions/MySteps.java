package StepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.java.en.And;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.time.Duration;
import java.util.*;

public class MySteps {


    public static WebDriver driver = null;
    Hashtable<String, String> user_dict = new Hashtable<String, String>();
    Hashtable<String, String> my_dict = new Hashtable<String, String>();
    ObjectMapper PAGE = new ObjectMapper();
    String CurrentPage = "";

    private static final Logger LOGGER = LoggerFactory.getLogger(MySteps.class);


    public MySteps() {
        driver = Hooks.driver;
    }


    @And("^I see (\\w+(?: \\w+)*) page")
    public void seePage(String page) throws IOException, ParseException {
        CurrentPage = page;
        Object document = null;
        JSONParser parser = new JSONParser();
        String projectPath = System.getProperty("user.dir");
        Object obj = parser.parse(new FileReader(projectPath + "\\src\\test\\java\\pages\\" + page + ".json"));
        JSONObject jsonObject = (JSONObject) obj;
        document = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toJSONString());
        String MustElementKey = JsonPath.read(document, "$.waitPageLoad.elementKey");
        if (MustElementKey.equals("")) {
            // DO NOTHING
        } else {
            seeElement(MustElementKey);
            LOGGER.info("SUCCESSFULLY SEE PAGE " + page);
        }
    }

    @And("^I see (\\w+(?: \\w+)*) element")
    public void seeElement(String elementKey) throws IOException, ParseException {
        By element = findSelector(elementKey);
        driver.findElement(element).isDisplayed();
    }

    @And("^I see (\\w+(?: \\w+)*) element in (\\d+) seconds")
    public void seeElementWithSecond(String elementKey, int seconds) throws IOException, ParseException {
        By element = findSelector(elementKey);
        WebElement web = new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(driver -> driver.findElement(element));
    }


    By findSelector(String element) throws IOException, ParseException {
        By EE = null;
        Object document = null;
        JSONParser parser = new JSONParser();
        Object page = null;
        if (CurrentPage.equals("")) {
            Assert.fail("BEFORE TEST STEPS HAVE TO SEE ANY PAGE 'I see *** page' ");
        } else {
            page = CurrentPage;
        }
        String projectPath = System.getProperty("user.dir");
        Object obj = parser.parse(new FileReader(projectPath + "\\src\\test\\java\\Pages\\" + page + ".json"));
        JSONObject jsonObject = (JSONObject) obj;
        document = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toJSONString());
        String elementKey = JsonPath.read(document, "$.elements.['" + element + "']");
        if (elementKey.charAt(0) == '/' || elementKey.charAt(0) == '(') {
            EE = By.xpath(elementKey);
            return EE;
        } else if (elementKey.charAt(0) == '#') {
            EE = By.id(elementKey.substring(1));
            return EE;
        } else {
            Assert.fail("COULD NOT FIND ELEMENT");
        }
        return EE;
    }

    @And("^I am registered with (\\w+(?: \\w+)*)")
    public void registeredInformation(String user) throws IOException, ParseException {
        findAndSaveUser(user);
    }

    public void findAndSaveUser(String userJson) throws IOException, ParseException {
        Object document = null;
        JSONParser parser = new JSONParser();
        String projectPath = System.getProperty("user.dir");
        Object obj = parser.parse(new FileReader(projectPath + "\\src\\test\\java\\Users\\" + userJson + ".json"));
        JSONObject jsonObject = (JSONObject) obj;
        document = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toJSONString());
        String userName = JsonPath.read(document, "$.username");
        String password = JsonPath.read(document, "$.password");
        user_dict.put("my username", userName);
        user_dict.put("my password", password);
    }

    @And("^I fill:")
    public void seeElementAndFill(Map<String, String> map) throws IOException, ParseException {
        for (String key : map.keySet()) {
            if (!map.get(key).equals("my username") && !map.get(key).equals("my password")) {
                By elementKey = findSelector(key);
                driver.findElement(elementKey).sendKeys(map.get(key));
                LOGGER.info("FILLED " + elementKey + " = " + map.get(key));
                System.out.println("FILLED " + elementKey + " = " + map.get(key));
            } else if (map.get(key).equals("my username")) {
                String username = user_dict.get("my username");
                By elementKey = findSelector(key);
                driver.findElement(elementKey).sendKeys(username);
                LOGGER.info("FILLED " + elementKey + " = " + username);
                System.out.println("FILLED " + elementKey + " = " + username);
            } else {
                String password = user_dict.get("my password");
                By elementKey = findSelector(key);
                driver.findElement(elementKey).sendKeys(password);
                LOGGER.info("FILLED " + elementKey + " = " + password);
                System.out.println("FILLED " + elementKey + " = " + password);
            }
        }
    }


    @And("^I click (\\w+(?: \\w+)*) element")
    public void seeElementAndClick(String element) throws IOException, ParseException {
        By elementKey = findSelector(element);
        try {
            driver.findElement(elementKey).click();
            LOGGER.info("CLICKED " + element);
            System.out.println("CLICKED " + element);
        } catch (Exception ee) {
            Assert.fail("COULD NOT FIND ELEMENT : " + elementKey);
        }
    }


    // if this text more than one, you can choose any parameters, but text have 1 elementKey you can choose 1
    @And("^I click \"([^\"]*)\" and (\\d+|last)th")
    public void seeElementAndClickToText(String text, String order) throws IOException, ParseException {
        By elementKey = null;
        if(!order.equals("last")){
            elementKey =By.xpath("(//*[text()='"+ text +"'])["+ order +"]");
        }else{
            elementKey =By.xpath("(//*[text()='"+ text +"'])["+ order +"()]");
        }
        try {
            driver.findElement(elementKey).click();
            LOGGER.info("CLICKED " + text);
            System.out.println("CLICKED " + text);
        } catch (Exception ee) {
            Assert.fail("COULD NOT FIND ELEMENT : " + elementKey);
        }
    }



    @And("^I mouseover (\\w+(?: \\w+)*) element")
    public void mouseOverPageAndElement(String element) throws IOException, ParseException {
        By elementKey = findSelector(element);
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(elementKey);
        action.moveToElement(we).moveToElement(driver.findElement(elementKey)).build().perform();
    }

    @And("^I save (\\w+(?: \\w+)*) element, get text and save the (\\w+(?: \\w+)*)")
    public void seeElementAndSave(String element, String variableName) throws IOException, ParseException {
        By elementKey = findSelector(element);
        my_dict.put("the " + variableName, driver.findElement(elementKey).getText());
        LOGGER.info("SAVED " + variableName + " = " + driver.findElement(elementKey).getText());
        System.out.println("SAVED " + variableName + " = " + driver.findElement(elementKey).getText());
    }

    @And("^I verify (\\w+(?: \\w+)*) equals \"([^\"]*)\" with text")
    public void verifyElementAndTextStrings(String value1, String value2) {
        if (my_dict.get(value1) == null) {
            Assert.fail(value1 + " is NULL");
        }
        if (my_dict.get(value1).equals(value2)) {
            LOGGER.info(my_dict.get(value1) + " Is Equals " + value2);
            System.out.println(my_dict.get(value1) + " Is Equals " + value2);
        } else {
            Assert.assertEquals(value1, value2);
        }
    }

    @And("^I not verify (\\w+(?: \\w+)*) equals \"([^\"]*)\" texts")
    public void notVerifyElementAndTextStrings(String value1, String value2) {
        if (my_dict.get(value1) == null) {
            Assert.fail(value1 + " is NULL");
        }
        if (!my_dict.get(value1).equals(value2)) {
            LOGGER.info(my_dict.get(value1) + " Is Not Equals " + value2);
            System.out.println(my_dict.get(value1) + " Is Not Equals " + value2);
        } else {
            Assert.fail(my_dict.get(value1) + " EQUALS " + value2);
        }
    }

    @And("^I verify (\\w+(?: \\w+)*) equals (\\w+(?: \\w+)*)")
    public void verificationElementAndElementStrings(String value1, String value2) {
        if (my_dict.get(value1) == null || my_dict.get(value2) == null) {
            Assert.fail(value1 + " or " + value2 + " is NULL");
        }
        if (my_dict.get(value1).equals(my_dict.get(value2))) {
            LOGGER.info(my_dict.get(value1) + " Is Equals " + my_dict.get(value2));
            System.out.println(my_dict.get(value1) + " Is Equals " + my_dict.get(value2));
        } else {
            Assert.assertEquals(my_dict.get(value1), my_dict.get(value2));
        }
    }

    @And("^I not verify (\\w+(?: \\w+)*) equals (\\w+(?: \\w+)*)")
    public void notVerificationElementAndElementStrings(String value1, String value2) {
        if (my_dict.get(value1) == null || my_dict.get(value2) == null) {
            Assert.fail(value1 + " or " + value2 + " is NULL");
        }
        if (!my_dict.get(value1).equals(my_dict.get(value2))) {
            LOGGER.info(my_dict.get(value1) + " Is Not Equals " + my_dict.get(value2));
            System.out.println(my_dict.get(value1) + " Is Not Equals " + my_dict.get(value2));
        } else {
            Assert.fail(my_dict.get(value1) + " EQUALS " + my_dict.get(value2));
        }
    }


    @And("^screen to maximize$")
    public void maximize() {
        driver.manage().window().maximize();
    }


    @And("^I wait to (\\d+) seconds$")
    public void Sleep(int second) throws InterruptedException {
        long time = second * 1000;
        Thread.sleep(time);

    }


    @And("^switch to new window$")
    public void switchTab() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }
}
