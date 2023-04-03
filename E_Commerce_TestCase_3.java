package mobileautomation.Appium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E_Commerce_TestCase_3 extends BaseTest {

	@Test
	public void fillForm() throws InterruptedException {
		setup();

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Aaathi");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector())." + "scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// Handling toash message
//		String errorMessage = 	driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
//		Assert.assertEquals(errorMessage, "Please enter your name");
		
		addMutipleProducts();
		//validateProductsPage();
		//verifyProductCartDetails();
		validatePriceOfProducts();
		tearDownServer();

	}

	public static void validateProductsPage() throws InterruptedException {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector())." + "scrollIntoView(text(\"Jordan 6 Rings\"));"));

		int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

		for (int i = 0; i < productCount; i++) {
			String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i)
					.getText();

			if (productName.equalsIgnoreCase("Jordan 6 Rings")) {

				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();

			}
		}
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(1000);

	}
	
	
	public static void verifyProductCartDetails() throws InterruptedException {
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("om.androidsample.generalstore:id/toolbar_title")),
				"text", "Cart"));
		String cartItems  = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(cartItems, "Jordan 6 Rings");
		
	}
	
	public static void addMutipleProducts() {
		
		driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[0]")).click();//Added to cart so next we using 0
		driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[0]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("om.androidsample.generalstore:id/toolbar_title")),
				"text", "Cart"));
		
	}
	
	public static void validatePriceOfProducts() { 
		
		List<WebElement> productPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count = productPrice.size();
		double totalPriceOfProducts = 0;
		for (int i = 0; i < count; i++) {
			String price = productPrice.get(i).getText();
			Double actualPrice =  Double.parseDouble(price.substring(1));
			totalPriceOfProducts = totalPriceOfProducts + actualPrice;
			
		}
		
		
		String expectedPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double expectedTotal = getFormatedAmount(expectedPrice);
		Assert.assertEquals(expectedTotal, totalPriceOfProducts);
	}
	
	

}
