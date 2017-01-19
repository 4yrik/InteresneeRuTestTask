package pages.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class AdminProductPage {

    private WebDriver driver;

    @FindBy(name = "name")
    private WebElement productName;

    @FindBy(linkText = "Save")
    private WebElement saveButton;

    public AdminProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdminProductPage fillProductName(String name){
        productName.clear();
        productName.sendKeys(name);
        return this;
    }

    public AdminProductsPage clickSaveButton(){
        saveButton.click();
        return new AdminProductsPage(driver);
    }
}
