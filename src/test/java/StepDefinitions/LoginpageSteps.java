package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.LoginPage;

public class LoginpageSteps {

    HomePage homePage;
    MySteps mySteps;
    Hooks hooks;
    WebDriver driver;

    public LoginpageSteps(){
        driver = hooks.driver;
    }


    @Given("^I LOGIN username:(\\w+(?: \\w+)*@(?:.*com)) and password:(\\w+(?: \\w+)*) and see profileName:(\\w+(?: \\w+)*)$")
    public void loginToApp(String username, String password, String ResultprofileName){
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.enterUsernameAndPassword(username,password);
        String profileName = loginPage.seeProfilename();
        if(profileName.equals(ResultprofileName)){
            System.out.println("PROFILE NAME IS TRUE");
        }else{
            Assert.fail("PROFILE NAME IS WRONG ---- YOU SHOULD SEE="+profileName);
        }
    }


    @And("^I open login page$")
    public void openHomePage() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        MySteps mySteps = new MySteps();
        login.mouseOverShortcutLogin();
        mySteps.Sleep(3);
        login.clickShourtcutLoginButton();
        login.loadedPage();
        //login.seeLoginPage();
    }
}
