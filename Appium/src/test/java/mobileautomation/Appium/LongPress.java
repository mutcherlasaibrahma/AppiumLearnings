package mobileautomation.Appium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LongPress extends BaseTest {

	@Test
	public void longPressGesture() throws MalformedURLException, InterruptedException {

		setup();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

		WebElement peopleNameElement = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longPressAction(peopleNameElement);
		Thread.sleep(2000);

		String longPressTextOnPeopleMenu = driver.findElement(By.id("android:id/title")).getText();
		Boolean isSampleMenuPresent = driver.findElement(By.id("android:id/title")).isDisplayed();
		assertTrue(isSampleMenuPresent);
		assertEquals(longPressTextOnPeopleMenu, "Sample menu");

		tearDownServer();

	}

}