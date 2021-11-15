package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPage {

    private WebDriver driver;
    private By firstitem = By.xpath("(//div[@class='productListContent-root']//img)[1]");
    private By fifthitem = By.xpath("(//div[@class='productListContent-root']//img)[5]");



    public CategoriesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickFirstItem(){
        driver.findElement(firstitem).click();
    }

    public void mouseoverFifthItem(){
        Actions actions = new Actions(driver);
        WebElement we = driver.findElement(fifthitem);
        actions.moveToElement(we).moveToElement(driver.findElement(fifthitem)).build().perform();
    }
}
