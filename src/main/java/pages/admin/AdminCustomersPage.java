package pages.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;

public class AdminCustomersPage {

    private WebDriver driver;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    @FindBy(css = ".top-menu li")
    private List<WebElement> topMenu;

    @FindBy(linkText = "+ Add User")
    private WebElement addUserButton;

    public AdminCustomersPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        logoutButton.click();
    }

    public AddUserPage goToAddUserPage(){
        addUserButton.click();
        return new AddUserPage(driver);
    }

    public AdminProductsPage goToAdminProductsPage(){
        topMenu.get(1).click();
        return new AdminProductsPage(driver);
    }
}
