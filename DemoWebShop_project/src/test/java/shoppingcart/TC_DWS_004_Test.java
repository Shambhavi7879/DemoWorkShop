package shoppingcart;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.objectrepository.HomePage;

public class TC_DWS_004_Test extends BaseClass {
	@Test(groups = "system")
	public void removeFromCart() {
		hp=new HomePage(driver);
		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
		driver.findElement(By.xpath("//a[text()='14.1-inch Laptop']/../..//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@value='Update shopping cart']")).click();
		boolean msgStatus = hp.getRemoveFromCart().isDisplayed();
		Assert.assertEquals(msgStatus, true,"Product failed to remove to the cart");
		test.log(Status.PASS, "Product has been removed from cart");

}
}