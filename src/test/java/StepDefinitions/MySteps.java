package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MySteps {


    public static WebDriver driver = null;

    LoginPage loginPage;
    HomePage homePage;
    MyBasketPage myBasketPage;
    CategoriesPage categoriesPage;
    OrderPage orderPage;


    public MySteps(){
        driver = Hooks.driver;
        homePage = PageFactory.initElements(driver,HomePage.class);
        myBasketPage = PageFactory.initElements(driver,MyBasketPage.class);
        categoriesPage = PageFactory.initElements(driver,CategoriesPage.class);
        orderPage = PageFactory.initElements(driver,OrderPage.class);
    }





    @And("^screen to maximize$")
    public void maximize(){
        driver.manage().window().maximize();
    }


    @And("^I wait to (\\d+) seconds$")
    public void Sleep(int second) throws InterruptedException {
        long time = second * 1000;
        Thread.sleep(time);

    }


    @And("^I mouseover element:(.*)$")
    public void mouseoverElement(String ElementKey)  {
        Actions actions = new Actions(driver);
        WebElement we = driver.findElement(By.xpath(ElementKey));
        actions.moveToElement(we).moveToElement(driver.findElement(By.xpath(ElementKey))).click().build().perform();
    }

    @And("^switch to new window$")
    public void switchTab(){
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }


    @And("^I click to (.*)$")
    public void click_element(String ElementKey)  {
        driver.findElement(By.xpath(ElementKey)).click();
    }


    @And("^I fill:")
    public void fill(Map<String, String> map){
        for (String key : map.keySet()) {
            System.out.println(key + "=" + map.get(key));
            driver.findElement(By.xpath(key)).sendKeys(map.get(key));
        }
    }


    @And("^(.*) Element to press ENTER$")
    public void press_enter(String ElementKey){
        driver.findElement(By.xpath(ElementKey)).sendKeys(Keys.ENTER);
    }


    @And("^I clear to my basket and return home page$")
    public void clearMyBasket() throws InterruptedException {
        myBasketPage.myBasketPageDeleteItems();
    }


    @And("^I added to basket first item, go to basket and verification price$")
    public void addToBasketFirstItem() throws InterruptedException {
        categoriesPage.clickFirstItem();
        Sleep(2);
        switchTab();
        Sleep(1);
        String price = orderPage.savePrice();
        String name = orderPage.saveItemName();
        Sleep(1);
        orderPage.clickAddToBasketButton();
        homePage.goToMyBasket();
        Sleep(1);
        String LastName = myBasketPage.saveBasketPageLastItemName();
        String LastPrice = myBasketPage.saveBasketPageLastItemPrice();
        if(price.equals(LastPrice) && name.equals(LastName)){
            // DO NOTHING
        }else{
            Assert.fail("NAME OR PRICE IS NOT EQUAL");
        }

    }


}
