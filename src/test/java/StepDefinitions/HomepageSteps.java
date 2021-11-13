package StepDefinitions;

import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.LoginPage;

public class HomepageSteps {


    HomePage homePage;
    MySteps mySteps;
    Hooks hooks;
    WebDriver driver;


    public HomepageSteps(){
        driver = hooks.driver;
        homePage = PageFactory.initElements(driver,HomePage.class);
        mySteps = PageFactory.initElements(driver,MySteps.class);
    }

    @And("^I mouseover Electronic Menu in Homepage$")
    public void mouseoverElectronicMenu(){
        homePage.mouseoverElectronicMenu();
    }

    @And("^I search (\\w+(?: \\w+)*)$")
    public void searchMainPage(String value){
        homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.searchMainPage(value);
    }

    @And("^I select laptop menu$")
    public void selectDizustuBilgisayarMenu(){
        homePage.mouseoverElectronicMenu();
        homePage.mouseoverComputerTabletMenu();
    }

    @And("^I added to basket first item, go to basket and verification price$")
    public void addToBasketFirstItem() throws InterruptedException {
        homePage.clickFirstItem();
        mySteps.Sleep(2);
        mySteps.switchTab();
        mySteps.Sleep(1);
        String price = homePage.savePrice();
        String name = homePage.saveItemName();
        mySteps.Sleep(1);
        homePage.clickAddToBasketButton();
        homePage.goToMyBasket();
        mySteps.Sleep(1);
        String LastName = homePage.saveBasketPageLastItemName();
        String LastPrice = homePage.saveBasketPageLastItemPrice();
        if(price.equals(LastPrice) && name.equals(LastName)){
            // DO NOTHING
        }else{
            Assert.fail("NAME OR PRICE IS NOT EQUAL");
        }

    }

    @And("^I clear to my basket and return home page$")
    public void clearMyBasket() throws InterruptedException {
       homePage.myBasketPageDeleteItems();
    }


    @And("^I go to my basket$")
    public void goToMyBasket(){
        homePage.goToMyBasket();
    }
}
