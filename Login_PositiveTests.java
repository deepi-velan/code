package com.herokuapp.theinternet.FormAuthentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;

public class Login_PositiveTests extends TestUtilities {

	@Test
	public void logInTest() {
		log.info("Starting logIn test");


		// open main page
		String url = "http://the-internet.herokuapp.com/";
		driver.get(url);
		log.info("Main page is opened.");

		// Click on Form Authentication link
		driver.findElement(By.linkText("Form Authentication")).click();

		// enter username and password
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
sleep(3000);
		// push log in button
		WebElement logInButton = driver.findElement(By.className("radius"));
	//	wait.until(ExpectedConditions.elementToBeClickable(logInButton));
		logInButton.click();

		// verifications
		// new url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		Assert.assertEquals(driver.getCurrentUrl().trim(), expectedUrl);

		// log out button is visible
//		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='button secondary radius']")).isDisplayed(),
//				"logOutButton is not visible.");
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".button.secondary.radius")).isDisplayed(),"logOutButton is not visible");

		// Successful log in message
		String expectedSuccessMessage = "You logged into a secure area!";
		//String actualSuccessMessage = driver.findElement(By.id("flash")).getText();
		String actualSuccessMessage = driver.findElement(By.cssSelector(".flash.success")).getText();
		
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
						+ expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
sleep(2000);
	}
}
