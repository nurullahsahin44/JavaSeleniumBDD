package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {

    private WebDriver driver;
    private By electronicMenu = By.xpath("//*[contains(@class,'navigationBar')]//span[text()='Elektronik']");
    private By fashionMenu = By.xpath("//*[contains(@class,'navigationBar')]//span[text()='Moda']");
    private By searchTextbox = By.xpath("//input[contains(@placeholder,'Ürün, kategori veya marka ara')]");
    private By searchButton = By.xpath("//*[contains(@class,'SearchBoxOld-buttonContainer') and text()='ARA']");
    private By laptopMenu = By.xpath("//*[contains(@class,'navigationBar')]//span[text()='Elektronik']/ancestor-or-self::span/following-sibling::div//*[text()='Dizüstü Bilgisayar']");
    private By computerTabletMenu = By.xpath("//*[contains(@class,'navigationBar')]//span[text()='Elektronik']/ancestor-or-self::span/following-sibling::div//*[text()='Bilgisayar/Tablet']");
    private By myBasketButton = By.id("shoppingCart");
    private By myBasketItemCount = By.id("cartItemCount");
    private By returnToHomePage = By.xpath("//*[contains(@href,'https://www.hepsiburada.com') and @title='Hepsiburada']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void mouseoverElectronicMenu() {
        Actions actions = new Actions(driver);
        WebElement we = driver.findElement(electronicMenu);
        actions.moveToElement(we).moveToElement(driver.findElement(electronicMenu)).click().build().perform();
    }

    public void mouseoveropenFashionMenu() {
        Actions actions = new Actions(driver);
        WebElement we = driver.findElement(fashionMenu);
        actions.moveToElement(we).moveToElement(driver.findElement(fashionMenu)).click().build().perform();
    }

    public void mouseoverComputerTabletMenu(){
        Actions actions = new Actions(driver);
        WebElement we = driver.findElement(computerTabletMenu);
        actions.moveToElement(we).moveToElement(driver.findElement(computerTabletMenu)).click().build().perform();
    }

    public void searchMainPage(String value){
        driver.findElement(searchTextbox).sendKeys(value);
        driver.findElement(searchButton).click();
    }

    public void selectlaptopMenu(){
        driver.findElement(laptopMenu);
    }

    public void goToMyBasket(){
        driver.findElement(myBasketButton).click();
    }

    public String saveMyBasketItemCount(){
        String ItemCount = driver.findElement(myBasketItemCount).getText();
        return ItemCount;
    }

    public void clickReturnToHomePage(){
        driver.findElement(returnToHomePage).click();
    }
}
