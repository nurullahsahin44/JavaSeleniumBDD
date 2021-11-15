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
        homePage.searchMainPage(value);
    }

    @And("^I select laptop menu$")
    public void selectDizustuBilgisayarMenu(){
        homePage.mouseoverElectronicMenu();
        homePage.mouseoverComputerTabletMenu();
    }





    @And("^I go to my basket$")
    public void goToMyBasket(){
        homePage.goToMyBasket();
    }
}
