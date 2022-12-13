package hientester.com.BT2_AnnotationTestNG;

import hientester.com.common.BaseTest;
import hientester.com.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddCategory_Annotation extends BaseTest {

    @BeforeMethod
    public void testLoginCMS() {
        driver.get("https://demo.activeitzone.com/ecommerce/login");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("email")).sendKeys("admin@example.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        WebUI.sleep(2);
    }

    @Test(priority = 1)
    public void AddCategory() {
        //Click trang Products
        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();

        //Click trang Category
        driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();

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
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Khuyến mãi khung");

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

        Assert.assertEquals(searchResult, "HienTester");
        WebUI.sleep(1);
    }
}
