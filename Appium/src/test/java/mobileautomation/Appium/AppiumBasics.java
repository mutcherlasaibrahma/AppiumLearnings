package mobileautomation.Appium;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AppiumBasics extends BaseTest {

	@Test
	public void clickOnPreferencesBtn() throws MalformedURLException {

		setup();
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

		// Getting the text
		String wifiAlertTitleName = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(wifiAlertTitleName, "WiFi settings");

		driver.findElement(By.id("android:id/edit")).sendKeys("Saibrahma123");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		tearDownServer();
	}

}
