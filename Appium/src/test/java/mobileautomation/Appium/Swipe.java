package mobileautomation.Appium;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Swipe extends BaseTest {

	@Test
	public void swipeGesture() throws MalformedURLException, InterruptedException {

		setup();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

		String focuable;
		WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		focuable = driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable");
		assertEquals(focuable, "true");

		// Swipe
		swipeAction(firstImage, "left");

		focuable = driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable");
		assertEquals(focuable, "false");

	}
}
