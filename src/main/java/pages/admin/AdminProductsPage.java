package pages.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;

public class AdminProductsPage {

    private WebDriver driver;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    @FindBy(css = ".data-table tr")
    private List<WebElement> products;

    public AdminProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdminProductPage goToProductPage(int numberOfProduct){
        products.get(numberOfProduct).click();
        return new AdminProductPage(driver);
    }

    public String getProductName(int numberOfProduct){
        return products.get(numberOfProduct).findElement(By.tagName("td")).getText();
    }

    public void logout(){
        logoutButton.click();
    }
}
