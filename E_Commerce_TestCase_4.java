package mobileautomation.Appium;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E_Commerce_TestCase_4 extends BaseTest {

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
		verifyTermsAndConditions();
		submitOrder();
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
	
	public static void addMutipleProducts() throws InterruptedException {
		
		driver.findElements(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).get(0).click();//Added to cart so next we using 0
		driver.findElements(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),
				"text", "Cart"));
		Thread.sleep(5000);
		
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
	
	
	public static void verifyTermsAndConditions() throws InterruptedException {
		WebElement element = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressAction(element);
		Thread.sleep(2000);
		String termsAndCondtionTitile = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
		Assert.assertEquals(termsAndCondtionTitile, "Terms Of Conditions");
		
		
	}
	
	public static void submitOrder() throws InterruptedException {
		
		WebElement closeBtnelement = driver.findElement(By.id("android:id/button1"));
		closeBtnelement.click();
		
		WebElement checkBoxBtn = driver.findElement(AppiumBy.className("android.widget.CheckBox"));
		checkBoxBtn.click();
		
		WebElement btnProcessed = driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
		btnProcessed.click();
		Thread.sleep(6000);
	}
	
	
	

}
