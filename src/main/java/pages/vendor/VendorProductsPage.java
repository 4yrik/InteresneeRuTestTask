package pages.vendor;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.List;

public class VendorProductsPage {

    private WebDriver driver;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    @FindBy(css = ".data-table tr")
    private List<WebElement> products;

    public VendorProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        logoutButton.click();
    }

    public boolean isAddProductButtonPresent(){
        try {
            driver.findElement(By.linkText("+ Add Product"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isAddLinkAvailable(){
        driver.get("http://test.interesnee.ru/admin/products/add");
        try{
            new WebDriverWait(driver, 2).until(ExpectedConditions.urlToBe("http://test.interesnee.ru/admin/products"));
            return false;
        }catch (TimeoutException e){
            return true;
        }
    }

    public boolean isDeleteIconPresent(){
        try {
            driver.findElement(By.cssSelector("div.icon.delete"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isVendorHaveAccessToPage(String url){
        driver.get(url);
        try{
            new WebDriverWait(driver, 2).until(ExpectedConditions.urlToBe("http://test.interesnee.ru/admin/products"));
            return false;
        }catch (TimeoutException e){
            return true;
        }
    }

    public VendorProductPage goToProductPage(){
        products.get(1).click();
        return new VendorProductPage(driver);
    }
}
