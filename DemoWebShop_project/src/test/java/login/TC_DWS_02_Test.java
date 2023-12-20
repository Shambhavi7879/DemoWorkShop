package login;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.genericutility.ListenerImplementation;

@Listeners(ListenerImplementation.class)
public class TC_DWS_02_Test extends BaseClass{
	
	@Test(groups = "system")
	public void loginTest() throws EncryptedDocumentException, IOException {
		String EXPECTED_TITLE = eLib.getDataFromExcel("Login", 1, 2);
		Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE,"user failed to login");
		test.log(Status.PASS, "User logged in successfully");
	}
}
