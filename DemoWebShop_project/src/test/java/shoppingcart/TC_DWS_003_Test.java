package shoppingcart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.genericutility.ListenerImplementation;
import com.tricentis.objectrepository.HomePage;

@Listeners(ListenerImplementation.class)
public class TC_DWS_003_Test extends BaseClass{
	
	@Test(groups = "system")
	public void addToCart() {
		hp=new HomePage(driver);
		driver.findElement(By.xpath("//a[text()='14.1-inch Laptop']/../..//input[@value='Add to cart']")).click();
		boolean msgStatus = hp.getAddTocartMsg().isDisplayed();
		Assert.assertEquals(msgStatus, true,"Product failed to add to the cart");
		test.log(Status.PASS, "Product has been added to cart");
		wait.until(ExpectedConditions.invisibilityOf(hp.getAddTocartMsg()));
	}
}
