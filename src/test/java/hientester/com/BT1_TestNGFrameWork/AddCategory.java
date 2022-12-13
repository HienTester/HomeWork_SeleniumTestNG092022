package hientester.com.BT1_TestNGFrameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddCategory {
    public WebDriver driver;

    @Test (priority = 1)
    public void createBrowswe() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        System.out.println("Start Chrome Browser from BaseTest");
    }

    @Test (priority = 2 )
    public void loginCMS() throws InterruptedException{
        //Bắt Element trang Login
        driver.get("https://demo.activeitzone.com/ecommerce/login");
        driver.findElement(By.id("email")).sendKeys("admin@example.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        //Bắt Element trang Products
        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();

        //Bắt Element trang Category
        driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Add New category']")).click();

        //Bắt Element Field "Name"
        driver.findElement(By.id("name")).sendKeys("HienTester");

        //Bắt Element Field "Parent Category"
        driver.findElement(By.xpath("(//div[normalize-space()='No Parent'])[3]")).click();
        driver.findElement(By.xpath("(//div[@class='bs-searchbox']//input)[1]")).sendKeys("Hot Categories", Keys.ENTER);

        //Bắt Element Field "Banner (200x200)"
        driver.findElement(By.xpath("//label[normalize-space()='Banner (200x200)']/following-sibling::div")).click();
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//input[@placeholder='Search your files']")).sendKeys("flash deal",Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//div[@title='flash deal.jpg']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        Thread.sleep(1000);

        //Bắt Element Field "Icon (32x32)"
        driver.findElement(By.xpath("//label[normalize-space()='Icon (32x32)']/following-sibling::div")).click();
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//input[@placeholder='Search your files']")).sendKeys("534",Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='aizUploaderModal']//div[@title='534.webp']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        Thread.sleep(1000);

        //Bắt Element Field "Meta Title"
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Khuyen mai sale khung");

        //Bắt Element Field "Meta Description"
        driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("Giam gia 10% cho tat ca mat hang");

        //Bắt Element Field "Filtering Attributes"
        driver.findElement(By.xpath("(//div[normalize-space()='Nothing selected'])[1]")).click();
        driver.findElement(By.xpath("//label[normalize-space()='Filtering Attributes']//following::input")).sendKeys("Liter", Keys.ENTER);

        //Bắt Element button "Save"
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

        //Search category name vừa add"
        driver.findElement(By.xpath("//input[@placeholder='Type name & Enter']")).sendKeys("HienTester", Keys.ENTER);

        //Get Text của item kết quả đầu tiên ở cột name sau khi search"
        WebElement searchResult = driver.findElement(By.xpath("//tbody//tr[1]//td[2]"));

        if(searchResult.getText().equals("HienTester")){
            System.out.println("Tạo Category thành công");
        }else{
            System.out.println("Không tạo Category được");
        }
    }

        @Test (priority = 3)
        public void closeBrowswe()throws InterruptedException{
        System.out.println("Close Browser");
        driver.quit();
    }
}

