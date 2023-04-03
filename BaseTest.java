package mobileautomation.Appium;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

import java.io.File;
import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {

	public static AppiumDriverLocalService service;
	public static AndroidDriver driver;

	@BeforeClass
	public void setup() {

		setUpServer();
		driver = new AndroidDriver(service.getUrl(), setCapabilities());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void setUpServer() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1").usingPort(4723)
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File("/usr/local/bin/node")).withArgument(BASEPATH, "/wd/hub")
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	private DesiredCapabilities setCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4");
//		capabilities.setCapability(MobileCapabilityType.APP,
//				"//Users//saibrahma.mutcherla//git//AppiumLearnings//Appium//src//test//java//resources//ApiDemos-debug.apk");
		capabilities.setCapability(MobileCapabilityType.APP,
				"//Users//saibrahma.mutcherla//git//AppiumLearnings//Appium//src//test//java//resources//General-Store.apk");
		capabilities.setCapability("noReset", false);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		return capabilities;
	}

	public void longPressAction(WebElement element) {

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));

	}

	public void scrollActionToEnd() {

		// when no prior idea
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}
	
	public void swipeAction(WebElement element,String direction ) {
		
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", 
				((RemoteWebElement) element).getId(), "duration", 2000,
			    "direction", direction,
			    "percent", 0.75
			));
		
	}
	
	public void dragAndDrop(WebElement elementToBeDraggedAndDropped,int endx,int endy) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) elementToBeDraggedAndDropped).getId(),
			    "endX", endx,
			    "endY", endy
			));
		
	}
	
	
	public static double getFormatedAmount(String amount) {
		
		Double price = Double.parseDouble(amount);
		return price;
	}

	@AfterClass
	public void tearDownServer() {

		driver.quit();
		service.stop();
	}

}
