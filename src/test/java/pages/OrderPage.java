package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderPage {


    private WebDriver driver;
    private By addToBasketButton = By.id("addToCart");
    private By itemPrice = By.xpath("//*[@id='offering-price']");
    private By itemName = By.id("product-name");


    public OrderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickAddToBasketButton(){
        driver.findElement(addToBasketButton).click();
    }

    public String savePrice(){
        //String price = driver.findElement(itemPrice).getText();
        String price = driver.findElement(itemPrice).getAttribute("content");
        Pattern pattern;
        Matcher matcher = null;
        pattern = Pattern.compile("(\\d+).(\\d+)");
        matcher = pattern.matcher(price);
        String lastPrice = "";
        if (matcher.find()) {
            lastPrice = matcher.group(1)+matcher.group(2);
        }
        lastPrice = lastPrice+" TL";
        System.out.println("SEPETE EKLEMEDEN ONCEKI DEGER = "+lastPrice);
        return lastPrice;
    }

    public String saveItemName(){
        String ItemName = driver.findElement(itemName).getText();
        System.out.println("SEPETE EKLEMEDEN ONCEKI URUN ISMI = "+ItemName);
        return ItemName;
    }
}
