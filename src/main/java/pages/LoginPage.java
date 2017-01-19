package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import pages.admin.AdminCustomersPage;
import pages.vendor.VendorProductsPage;

public class LoginPage {

    private WebDriver driver;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "div.center")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void fillEmail(String _email){
        email.clear();
        email.sendKeys(_email);
    }

    private void fillPassword(String _password){
        password.clear();
        password.sendKeys(_password);
    }

    public AdminCustomersPage loginAsAdmin(String _email, String _password){
        fillEmail(_email);
        fillPassword(_password);
        loginButton.click();
        try{
            new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return !driver.getCurrentUrl().contains("=");
                }
            });
            driver.findElement(By.linkText("Customers")).click();
            return new AdminCustomersPage(driver);
        }catch (TimeoutException e){
            return null;
        }
    }

    public VendorProductsPage loginAsVendor(String _email, String _password){
        fillEmail(_email);
        fillPassword(_password);
        loginButton.click();
        try{
            new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return !driver.getCurrentUrl().contains("=");
                }
            });
            return new VendorProductsPage(driver);
        }catch (TimeoutException e){
            return null;
        }
    }
}
