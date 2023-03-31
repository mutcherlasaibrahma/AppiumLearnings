package mobileautomation.Appium;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class DragAndDrop  extends BaseTest {

	@Test
	public void dragAndDropGesture() throws MalformedURLException, InterruptedException {

		setup();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement elementToBeDragged = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		dragAndDrop(elementToBeDragged, 649, 690);
		
		String verifedDropedMessage = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		assertEquals(verifedDropedMessage, "Dropped!");
	}

}
