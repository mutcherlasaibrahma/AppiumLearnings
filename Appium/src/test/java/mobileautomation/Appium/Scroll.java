package mobileautomation.Appium;

import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class Scroll extends BaseTest {

	@Test
	public void scrollGesture() throws MalformedURLException, InterruptedException {
		
		setup();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
//		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())."
//				+ "scrollIntoView(text(\"WebView\"));"));
		scrollActionToEnd();
		
		Thread.sleep(2000);
		tearDownServer();
		
	}


}
