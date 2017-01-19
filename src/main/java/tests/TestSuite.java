package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.admin.*;
import org.apache.log4j.*;
import pages.*;
import pages.vendor.*;
import java.util.concurrent.TimeUnit;

public class TestSuite {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static final Logger logger = Logger.getLogger(TestSuite.class);

    @BeforeClass
    public static void beforeTests(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Женя\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://team:interesnee@test.interesnee.ru/admin");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testCase01(){
        AdminCustomersPage adminCustomersPage = loginPage.loginAsAdmin("admin@spggoods.com", "admin");
        try{
            Assert.assertNotNull(adminCustomersPage);
            adminCustomersPage.logout();
            logger.info("Test case 01 PASS");
        }catch (Error e){
            logger.error("Test case 01 FAIL: Admin don't have access to backend");
        }
    }

    @Test
    public void testCase02(){
        VendorProductsPage vendorProductsPage = loginPage.loginAsVendor("firstvendor@spggoods.com", "vendor");
        try{
            Assert.assertNotNull(vendorProductsPage);
            vendorProductsPage.logout();
            logger.info("Test case 02 PASS");
        }catch (Error e){
            logger.error("Test case 02 FAIL: Vendor don't have access to backend");
        }
    }

    @Test
    public void testCase03(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        VendorProductsPage vendorProductsPage = loginPage.loginAsVendor("firstvendor@spggoods.com", "vendor");
        try {
            Assert.assertFalse(vendorProductsPage.isAddProductButtonPresent());
            Assert.assertFalse(vendorProductsPage.isAddLinkAvailable());
            Assert.assertFalse(vendorProductsPage.isDeleteIconPresent());
        }catch (Error e){
            logger.error("Test case 03 FAIL: Vendor have ability to edit/add/delete products");
            vendorProductsPage.logout();
            return;
        }
        VendorProductPage vendorProductPage = vendorProductsPage.goToProductPage();

        try{
            Assert.assertFalse(vendorProductPage.isSaveButtonPresent());
            Assert.assertFalse(vendorProductPage.isDeleteButtonPresent());
            logger.info("Test case 03 PASS");
        }catch (Error e){
            logger.error("Test case 03 FAIL: Vendor have ability to edit/add/delete products");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        vendorProductPage.logout();
    }

    @Test
    public void testCase04(){
        VendorProductsPage vendorProductsPage = loginPage.loginAsVendor("firstvendor@spggoods.com", "vendor");
        try {
            Assert.assertFalse(vendorProductsPage.isVendorHaveAccessToPage("http://test.interesnee.ru/admin/products/add?productID=16"));
            logger.info("Test case 04 PASS");
        }catch (Error e){
            logger.error("Test case 04 FAIL: Vendor is able to see other vendor's product");
        }
        vendorProductsPage.logout();
    }

    @Test
    public void testCase05(){
        String newProductName = "TestName1";
        AdminProductsPage adminProductsPage = loginPage.loginAsAdmin("admin@spggoods.com", "admin")
                                                       .goToAdminProductsPage()
                                                       .goToProductPage(1)
                                                       .fillProductName(newProductName)
                                                       .clickSaveButton();
        try{
            Assert.assertEquals(newProductName, adminProductsPage.getProductName(1));
            logger.info("Test case 05 PASS");
        }catch (Error e){
            logger.error("Test case 05 FAIL: Admin is not able to change product name");
        }
        adminProductsPage.logout();
    }

    @Test
    public void testCase06(){
        VendorProductsPage vendorProductsPage = loginPage.loginAsVendor("firstvendor@spggoods.com", "vendor");
        try {
            Assert.assertFalse(vendorProductsPage.isVendorHaveAccessToPage("http://test.interesnee.ru/admin/customers/add"));
            logger.info("Test case 06 PASS");
        }catch (Error e){
            logger.error("Test case 06 FAIL: Vendor is able to create new customers");
        }
        vendorProductsPage.logout();
    }

    @Test
    public void testCase07(){
        AddUserPage addUserPage = loginPage.loginAsAdmin("admin@spggoods.com", "admin")
                                           .goToAddUserPage();
        try {
            Assert.assertTrue(addUserPage.isEmptyBudgetValidationPresent());
            logger.info("Test case 07 PASS");
        }catch (Error e){
            logger.error("Test case 07 FAIL: Empty budget validation is not present");
        }
        addUserPage.logout();
    }

    @AfterClass
    public static void afterTests(){
        driver.quit();
    }
}
