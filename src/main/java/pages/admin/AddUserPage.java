package pages.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class AddUserPage {

    private WebDriver driver;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    @FindBy(linkText = "+ Add")
    private WebElement addBudgetButton;

    @FindBy(name = "budgetName")
    private WebElement budgetName;

    @FindBy(css = ".selectBox.inputBox")
    private WebElement frequancy;

    @FindBy(name = "balance")
    private WebElement balance;

    public AddUserPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isEmptyBudgetValidationPresent(){
        addBudgetButton.click();
        return isBudgetNameError() && isBalanceError() && isFrequancyError();
    }

    public boolean isBudgetNameError(){
        try{
            new WebDriverWait(driver, 2).until(ExpectedConditions.attributeContains(budgetName, "class", "error"));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public boolean isFrequancyError(){
        try{
            new WebDriverWait(driver, 2).until(ExpectedConditions.attributeContains(frequancy, "class", "error"));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public boolean isBalanceError(){
        try{
            new WebDriverWait(driver, 2).until(ExpectedConditions.attributeContains(budgetName, "class", "error"));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public void logout(){
        logoutButton.click();
    }
}
