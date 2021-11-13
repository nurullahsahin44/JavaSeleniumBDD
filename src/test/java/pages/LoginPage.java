package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    private WebDriver driver;
    private By username_txt = By.id("txtUserName");
    private By password_txt = By.id("txtPassword");
    private By login_button = By.id("btnLogin");
    private By shortcutLoginText = By.xpath("//span[contains(@title,'Giriş Yap')]");
    private By shortcutLoginButton = By.id("login");
    private By forgetMyPassword = By.xpath("//span[contains(.,'Şifremi unuttum')]");
    private By profilName = By.xpath("//span[contains(text(),'Hesabım')]/following-sibling::span");
    private By login2_txt = By.xpath("//*[contains(text(),'Yardıma ihtiyacım var')]");
    private By login_button2 = By.id("btnEmailSelect");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void enterUsername(String username){
        driver.findElement(username_txt).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(password_txt).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(login_button).click();
    }

    public void enterUsernameAndPassword(String username, String password){
        Boolean status = false;
        try{
            status = driver.findElement(login2_txt).isDisplayed();
        }catch (Exception ee){
            status = false;
        }

        if(status == true){
            driver.findElement(username_txt).sendKeys(username);
            driver.findElement(login_button).click();
            driver.findElement(password_txt).sendKeys(password);
            driver.findElement(login_button2).click();
        }else{
            driver.findElement(username_txt).sendKeys(username);
            driver.findElement(password_txt).sendKeys(password);
            driver.findElement(login_button).click();
        }
    }

    public void mouseOverShortcutLogin(){
        Actions actions = new Actions(driver);
        WebElement we = driver.findElement(shortcutLoginText);
        actions.moveToElement(we).moveToElement(driver.findElement(shortcutLoginText)).click().build().perform();
    }

    public void clickShourtcutLoginButton(){
        driver.findElement(shortcutLoginButton).click();
    }

    public void seeLoginPage(){
        driver.findElement(forgetMyPassword).isDisplayed();
    }

    public void loadedPage(){
        JavascriptExecutor js;
        String pageLoadStatus = null;
        do {
            js = (JavascriptExecutor) driver;
            pageLoadStatus = (String)js.executeScript("return document.readyState");
        } while ( !pageLoadStatus.equals("complete") );
        System.out.println("PAGE IS LOADED");
    }

    public String seeProfilename(){
        String profileName = driver.findElement(profilName).getText();
        return profileName;
    }
}
