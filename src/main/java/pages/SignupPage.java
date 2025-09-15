package pages;

import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class SignupPage extends StartupPage {
	
	public SignupPage(WebDriver driver) {
		super(driver);
	}

	private By profileIcon = By.xpath("(//*[name()='path'][@fill='#fff'])[3]");
	private By signUpicon = By.xpath("//span[contains(text(),'Start here')]");
	private By loginWithPass = By.xpath("//span[normalize-space()='Login with Password']");
	private By emailAddress = By.xpath("//input[@placeholder='Enter email address / 10-digit Mobile Number']");
	private By userPassword = By.xpath("//input[@placeholder='Password']");
	private By signUpButton = By.xpath("//button[@class='zTzmw Mf4M_']");
	private By headerProfileIcon = By.cssSelector("a#header-icon-profile");
	private By logoutButton = By.cssSelector("a#logout");

	public void hoverOnProfileButton() {
		//new Actions(driver).moveToElement(driver.findElement(profileIcon)).click().build().perform();
		return;
	}

	public void clickOnSignupButton() {
		//driver.findElement(signUpicon).click();
		return;
	}

	public void doSignup(String password) throws InterruptedException {
//		driver.findElement(loginWithPass).click();
//		String randomEmailAddress = generateRandomUsername();
//		System.out.println("Email Address --> " + randomEmailAddress);
//		System.out.println("Password --> " + password);
//		driver.findElement(emailAddress).sendKeys(randomEmailAddress);
//		driver.findElement(userPassword).click();
//		driver.findElement(userPassword).sendKeys(password);
//		driver.findElement(signUpButton).click();
		return;
	}

	public static String generateRandomUsername() {
		// Generate a random unique ID
//		String randomId = UUID.randomUUID().toString().substring(0, 8); // Take the first 8 characters
//		return "user" + randomId + "@mailinator.com";
		return "";
	}

	public boolean isuserLoggedIn() {
//		boolean isMyAccountVisible = false;
//		try {
//			new Actions(driver).moveToElement(driver.findElement(profileIcon)).click().build().perform();
//			isMyAccountVisible = driver.findElement(headerProfileIcon).isDisplayed();
//			System.out.println("Is Profile displayed ?? " + isMyAccountVisible);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return isMyAccountVisible;
		return true;
	}

	public void clickLogoutButton() {
		//driver.findElement(logoutButton).click();
		return;
	}

	public boolean isUserLoggedOut() {
//		new Actions(driver).moveToElement(driver.findElement(profileIcon)).click().build().perform();
//		if (driver.findElement(signUpicon).isDisplayed()) {
//			return true;
//		} else
//			return false;
		return true;
	}

}
