package hientester.com.BT3_Assert;

import hientester.com.common.BaseTest;
import hientester.com.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Edit_DeleteCategory extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

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

        //Kiểm tra Text của Field " All Categories"
        String textAllCategories = driver.findElement(By.xpath("//div[@class='col-md-6']//h1")).getText();
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
        System.out.println("Get Category Item: " + searchResult); // In ra để xác nhận kết quả search đúng không?

        softAssert.assertEquals(searchResult, "HienTester");
        WebUI.sleep(3);
        softAssert.assertAll();
    }

    @Test (priority = 2)
    public void EditCategory(){

        //Search category name vừa add"
        driver.findElement(By.xpath("//input[@placeholder='Type name & Enter']")).sendKeys("HienTester", Keys.ENTER);

        //Click vào button Edit
        driver.findElement(By.xpath("(//tbody//tr//td//i[@class='las la-edit'])[1]")).click();
        softAssert.assertTrue(driver.findElement(By.xpath("(//tbody//tr//td//i[@class='las la-edit'])[1]")).isEnabled(),"Nút edit không cho phép bấm vào");

        //Edit value của Field "Meta Description"
        driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("giảm giá 25% nhân ngày BlackFriday");

        //Click button "Save"
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        softAssert.assertTrue(driver.findElement(By.xpath("//button[normalize-space()='Save']")).isDisplayed(),"Nút save không được hiển thị");
        softAssert.assertTrue(driver.findElement(By.xpath("//button[normalize-space()='Save']")).isEnabled(),"Nút save không cho phép bấm");
        WebUI.sleep(4);

        //Trở về trang Category
        //Click trang Category
        driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();

        //Search category name vừa edit "
        driver.findElement(By.xpath("//input[@placeholder='Type name & Enter']")).sendKeys("HienTester", Keys.ENTER);
        WebUI.sleep(2);

        //Click vào button Edit một lần nữa để check giá trị sau khi edit đã chính xác chưa?
        driver.findElement(By.xpath("(//tbody//tr//td//i[@class='las la-edit'])[1]")).click();
        WebUI.sleep(3);

        //Get Text của field "Meta Description" để check xem giá trị sau khi edit chính xác chưa
        String searchEdit = driver.findElement(By.xpath("//textarea[@name='meta_description']")).getText();
        WebUI.sleep(3);
        System.out.println("Meta Description: " + searchEdit);
        Assert.assertEquals(searchEdit,"giảm giá 25% nhân ngày BlackFriday");

        WebUI.sleep(3);
        softAssert.assertAll();
    }

}
