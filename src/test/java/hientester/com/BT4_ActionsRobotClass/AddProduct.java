package hientester.com.BT4_ActionsRobotClass;

import hientester.com.common.BaseTest;
import hientester.com.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;

public class AddProduct extends BaseTest {
     SoftAssert softAssert = new SoftAssert();
     String categoryName = "HienTester";
     String productName = "Happy day";
     int countColor = 3;
     String productColor1 = "AntiqueWhite";
     String productColor2 = "DarkBlue";
     String productColor3 = "Red";

     @BeforeMethod
     public void testLoginCMS() {
         driver.get("https://demo.activeitzone.com/ecommerce/login");
         driver.findElement(By.id("email")).clear();
         driver.findElement(By.id("password")).clear();

         //Kiểm tra text của tiêu đề, dialog
         softAssert.assertEquals(driver.getTitle(),"Active eCommerce | Demo of Active eCommerce CMS","Sai tiêu đề trang");
         softAssert.assertTrue(driver.findElement(By.xpath("//button[normalize-space()='Login']")).isEnabled());

         driver.findElement(By.id("email")).sendKeys("admin@example.com");
         driver.findElement(By.id("password")).sendKeys("123456");
         driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
         WebUI.sleep(2);

         softAssert.assertAll();
     }

    @Test(priority = 1)
    public void addCategory() {
        //Click trang Products
        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();

        //Click trang Category
        driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();

        //Kiểm tra Text của Field " All Categories"
        String textAllCategories =  driver.findElement(By.xpath("//div[@class='col-md-6']//h1")).getText();
        softAssert.assertEquals(textAllCategories,"All Categories","Tiêu đề của Category bị sai");
        System.out.println("Get textAllCategories: " + textAllCategories);

        //Click Field "Add New Category"
        driver.findElement(By.xpath("//span[normalize-space()='Add New category']")).click();

        //Điền value cho Field "Name"
        driver.findElement(By.id("name")).sendKeys("HienTester");
        WebUI.sleep(1);

        //Điền value cho Field "Parent Category"
        driver.findElement(By.xpath("(//div[normalize-space()='No Parent'])[3]")).click();
        driver.findElement(By.xpath("(//div[@class='bs-searchbox']//input)[1]")).sendKeys("Hot Categories", Keys.ENTER);

        //Chọn file ảnh cho Field "Banner (200x200)"
        driver.findElement(By.xpath("//label[normalize-space()='Banner (200x200)']/following-sibling::div")).click();
        softAssert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Banner (200x200)']/following-sibling::div")).isEnabled());
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//input[@placeholder='Search your files']")).sendKeys("flash deal", Keys.ENTER);
        WebUI.sleep(3);
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//div[@title='flash deal.jpg']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        WebUI.sleep(2);

        //Chọn ảnh cho Field "Icon (32x32)"
        driver.findElement(By.xpath("//label[normalize-space()='Icon (32x32)']/following-sibling::div")).click();
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//input[@placeholder='Search your files']")).sendKeys("534", Keys.ENTER);
        WebUI.sleep(3);
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//div[@title='534.webp']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        WebUI.sleep(1);

        //Điền value cho Field "Meta Title"
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Khuyến mãi khủng");

        //Điền value cho Field "Meta Description"
        driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("Giam gia 10% cho tat ca mat hang");

        //Chọn value cho dropdown "Filtering Attributes"
        driver.findElement(By.xpath("(//div[normalize-space()='Nothing selected'])[1]")).click();
        driver.findElement(By.xpath("//label[normalize-space()='Filtering Attributes']//following::input")).sendKeys("Liter", Keys.ENTER);

        //Click button "Save"
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

        //Search category name vừa add"
        driver.findElement(By.xpath("//input[@placeholder='Type name & Enter']")).sendKeys("HienTester", Keys.ENTER);

        //Get Text của item kết quả đầu tiên ở cột name sau khi search"
        String searchResult = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
        System.out.println("Get Category Item: "+ searchResult); // In ra để xác nhận kết quả search đúng không?

        softAssert.assertEquals(searchResult, "HienTester");
        WebUI.sleep(2);
        softAssert.assertAll();

    }
    @Test(priority = 2)
    public void addProduct() throws AWTException {
         addCategory();
         Actions action = new Actions(driver);
         Robot robot = new Robot();

         //Click vào product menu

    }
}
