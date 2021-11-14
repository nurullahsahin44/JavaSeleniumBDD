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
    private By firstitem = By.xpath("(//div[@class='productListContent-root']//img)[1]");
    private By fifthitem = By.xpath("(//div[@class='productListContent-root']//img)[5]");
    private By addToBasketButton = By.id("addToCart");
    private By myBasketButton = By.id("shoppingCart");
    private By itemPrice = By.xpath("//*[@id='offering-price']");
    private By itemName = By.id("product-name");
    private By basketPageLastItemName = By.xpath("(//*[@id='onboarding_item_list']//div[contains(@class,'product_name')])[last()]");
    private By basketPageLastItemPrice = By.xpath("(//*[@id='onboarding_item_list']//div[contains(@data-test-id,'price-current-price')])[last()]");
    private By myBasketItemCount = By.id("cartItemCount");
    private By myBasketFirstDeleteButton = By.xpath("(//*[@aria-label='Ürünü Kaldır'])[1]");
    private By returnToHomePage = By.xpath("//*[contains(@href,'https://www.hepsiburada.com') and @title='Hepsiburada']");
    private By deleteButton = By.xpath("//button[text()='Sil']");

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

    public void clickFirstItem(){
        driver.findElement(firstitem).click();
    }

    public void clickDeleteButton(){driver.findElement(deleteButton).click();}

    public void mouseoverFifthItem(){
        Actions actions = new Actions(driver);
        WebElement we = driver.findElement(fifthitem);
        actions.moveToElement(we).moveToElement(driver.findElement(fifthitem)).build().perform();
    }

    public void selectlaptopMenu(){
        driver.findElement(laptopMenu);
    }

    public void goToMyBasket(){
        driver.findElement(myBasketButton).click();
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

    public String saveMyBasketItemCount(){
        String ItemCount = driver.findElement(myBasketItemCount).getText();
        return ItemCount;
    }

    public void myBasketPageDeleteItems(){
        String itemCount = saveMyBasketItemCount();
        if(!itemCount.equals("0")){
            int count = 5;
            Boolean status = true;
            goToMyBasket();
            while (count < 6  && status == true){
                try{
                    driver.findElement(myBasketFirstDeleteButton).click();
                    clickDeleteButton();
                    count--;
                }catch (Exception ee){
                    status = false;
                }
            }
            clickReturnToHomePage();
        }else{
           // DO NOTHING
        }

    }

    public void clickAddToBasketButton(){
        driver.findElement(addToBasketButton).click();
    }

    public void clickReturnToHomePage(){
        driver.findElement(returnToHomePage).click();
    }
}
