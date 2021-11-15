package pages;

import StepDefinitions.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyBasketPage {
    private WebDriver driver;
    private By basketPageLastItemName = By.xpath("(//*[@id='onboarding_item_list']//div[contains(@class,'product_name')])[last()]");
    private By basketPageLastItemPrice = By.xpath("(//*[@id='onboarding_item_list']//div[contains(@data-test-id,'price-current-price')])[last()]");
    private By myBasketFirstDeleteButton = By.xpath("(//*[@aria-label='Ürünü Kaldır'])[1]");
    private By deleteButton = By.xpath("//button[text()='Sil']");
    public void clickDeleteButton(){driver.findElement(deleteButton).click();}

    public MyBasketPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String saveBasketPageLastItemName(){
        String ItemName = driver.findElement(basketPageLastItemName).getText();
        System.out.println("SEPETE EKLEMEDEN ONCEKI URUN ISMI = "+ItemName);
        return ItemName;
    }

    public String saveBasketPageLastItemPrice(){
        String ItemPrice = driver.findElement(basketPageLastItemPrice).getText();
        Pattern pattern;
        Matcher matcher = null;
        pattern = Pattern.compile("(\\d+).(\\d+).(\\d+)?");
        matcher = pattern.matcher(ItemPrice);
        String lastPrice = "";
        if (matcher.find()) {
            if(matcher.group(3)==null){
                lastPrice = matcher.group(1)+matcher.group(2);
            }else{
                lastPrice = matcher.group(1)+matcher.group(2)+matcher.group(3);
            }
        }
        lastPrice = lastPrice+" TL";
        System.out.println("SEPETE EKLEDIKTEN SONRA KI DEGER = "+lastPrice);
        return lastPrice;
    }

    public void myBasketPageDeleteItems(){
        HomePage home = new HomePage(driver);
        String itemCount = home.saveMyBasketItemCount();
        if(!itemCount.equals("0")){
            int count = 5;
            Boolean status = true;
            home.goToMyBasket();
            while (count < 6  && status == true){
                try{
                    driver.findElement(myBasketFirstDeleteButton).click();
                    clickDeleteButton();
                    count--;
                }catch (Exception ee){
                    status = false;
                }
            }
            home.clickReturnToHomePage();
        }else{
            // DO NOTHING
        }

    }
}
