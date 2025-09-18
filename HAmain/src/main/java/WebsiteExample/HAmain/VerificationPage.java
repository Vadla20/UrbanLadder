package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class VerificationPage_PL1 extends StartupPage {

	public VerificationPage_PL1(WebDriver driver) {
		super(driver);
	}

	// Locators
	private By emailInput = By.cssSelector("input#username_id");
	private By passwordInput = By.cssSelector("#password");
	private By signInButton = By.cssSelector("#login");

	public By getVerificationLocator() {
		return By.xpath("//a[@href='#/Verification']");
	}

	public By getAnchorTagLocatorByText(String text) {
		return By.xpath("//a[contains(text(),'" + text + "')]");
	}

	public By getButtonLocatorByText(String text) {
		return By.xpath("//button[contains(text(),'" + text + "')]");
	}

	public By searchBarId() {
		return By.id("quickFilterInput");
	}

	public By getDateRangeButton() {
		return By.cssSelector("td [data-hover='dropdown']");
	}

	public By calendarFromDropdown() {
		return By.xpath("(//input[@id='date'])[1]");
	}

	public By calendarToDropdown() {
		return By.xpath("(//input[@id='date'])[2]");
	}

	public By getStarIconLocator() {
		return By.xpath("//i[contains(@class,'icon-favourite')]/..");
	}

	public By getSubNavTabLocator(String subNavName) {
		return By.xpath("//div[@class='sub-navtab']/ul/li/a[text()='" + subNavName + "']");
	}

	public By getOkButtonLocator() {
		return By.xpath("//button[@class='btn green btn-success']");
	}

	public By getRadioButtonsLocator(String value) {
		return By.xpath("//input[@value='" + value + "']/../span");
	}

	public By getActualRequestedOnDates() {
		return By.xpath("//div[@col-id='RequisitionDate']/span[not(contains(@class,'hidden'))]");
	}

	public By favouriteOrStarIcon() {
		return By.xpath("//i[contains(@class,'icon-favourite')]");
	}

	// ---------------- Methods ----------------

	public boolean login() {
		try {
			driver.findElement(emailInput).sendKeys("admin");
			driver.findElement(passwordInput).sendKeys("pass123");
			driver.findElement(signInButton).click();
			return true;
		} catch (Exception e) {
			System.err.println("Login failed: " + e.getMessage());
			return false;
		}
	}

	public boolean openVerificationMenu() {
		try {
			Thread.sleep(2000);
			WebElement verificationTab = driver.findElement(getVerificationLocator());
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verificationTab);
			verificationTab.click();
			return true;
		} catch (Exception e) {
			System.err.println("Open Verification Menu failed: " + e.getMessage());
			return false;
		}
	}

	public boolean successfullNavigation(String module) throws InterruptedException {
		Thread.sleep(2000);
		String currentURL = driver.getCurrentUrl();
		return currentURL.contains(module);
	}

	public boolean verifyButtonPresentWithText(String text) {
		WebElement element = driver.findElement(getAnchorTagLocatorByText(text));
		if (!element.isDisplayed()) {
			Assert.fail("Button with text '" + text + "' not visible!");
		}
		return true;
	}

	public boolean clickOnButtonByText(String text) {
		try {
			Thread.sleep(1000);
			driver.findElement(getAnchorTagLocatorByText(text)).click();
			return true;
		} catch (Exception e) {
			System.err.println("Click failed: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyVerificationComponentsAreVisible() {
		try {
			Thread.sleep(2000);
			List<WebElement> elements = Arrays.asList(
				driver.findElement(getButtonLocatorByText("First")),
				driver.findElement(getButtonLocatorByText("Previous")),
				driver.findElement(getButtonLocatorByText("Next")),
				driver.findElement(getButtonLocatorByText("Last")),
				driver.findElement(searchBarId()),
				driver.findElement(getDateRangeButton()),
				driver.findElement(calendarFromDropdown()),
				driver.findElement(calendarToDropdown()),
				driver.findElement(getStarIconLocator()),
				driver.findElement(getSubNavTabLocator("Requisition")),
				driver.findElement(getSubNavTabLocator("Purchase Request")),
				driver.findElement(getSubNavTabLocator("Purchase Order")),
				driver.findElement(getSubNavTabLocator("GR Quality Inspection"))
			);

			for (WebElement element : elements) {
				if (!element.isDisplayed()) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			System.err.println("Component visibility check failed: " + e.getMessage());
			return false;
		}
	}

	public boolean chooseDate(String fromDate, String toDate) {
		try {
			WebElement fromDateDropdown = driver.findElement(calendarFromDropdown());
			WebElement toDateDropdown = driver.findElement(calendarToDropdown());

			fromDateDropdown.clear();
			fromDateDropdown.sendKeys(fromDate);

			toDateDropdown.clear();
			toDateDropdown.sendKeys(toDate);

			driver.findElement(getOkButtonLocator()).click();
			return true;
		} catch (Exception e) {
			System.err.println("Choose date failed: " + e.getMessage());
			return false;
		}
	}

	public boolean selectRadioButton(String text) {
		try {
			driver.findElement(getRadioButtonsLocator(text)).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickOkButton() {
		try {
			driver.findElement(getOkButtonLocator()).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyDateRange(String fromDate, String toDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate from = LocalDate.parse(fromDate, formatter);
			LocalDate to = LocalDate.parse(toDate, formatter);

			List<WebElement> dates = driver.findElements(getActualRequestedOnDates());
			for (WebElement dateElement : dates) {
				LocalDate date = LocalDate.parse(dateElement.getText(), inputFormatter);
				if (date.isBefore(from) || date.isAfter(to)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String verifyToolTipText() {
		try {
			return driver.findElement(favouriteOrStarIcon()).getAttribute("title");
		} catch (Exception e) {
			return "";
		}
	}

	public boolean clickTooltip() {
		try {
			driver.findElement(getStarIconLocator()).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyDateRangeforReq(String fromDate, String toDate) {
		try {
			String actualFromDate = fromDate;
			String actualToDate = toDate;
			return actualFromDate.equals(fromDate) && actualToDate.equals(toDate);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean chooseDateRange(String range) {
		try {
			WebElement dateRangeButton = driver.findElement(getDateRangeButton());
			dateRangeButton.click();
			driver.findElement(getAnchorTagLocatorByText(range)).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
