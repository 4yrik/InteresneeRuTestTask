package pages.vendor;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class VendorProductPage {

    private WebDriver driver;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    public VendorProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        driver.navigate().back();
        logoutButton.click();
    }

    public boolean isSaveButtonPresent(){
        try {
            driver.findElement(By.linkText("Save"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isDeleteButtonPresent(){
        try {
            driver.findElement(By.linkText("Delete"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
