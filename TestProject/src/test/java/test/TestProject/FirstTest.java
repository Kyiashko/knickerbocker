package test.TestProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {

	WebDriver driver;

	@Before
	public void beforeTest() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://knickerbocker-hotel-new-york.nochi.com/?btest=119");
	}

	@After
	public void afterTest() {

		List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
		System.out.println(logs);
		driver.quit();
	}

	@Test
	public void bookSomeRoom() throws IOException, InterruptedException {

		WebElement overviewButton = driver
				.findElement(By.xpath("//div[@class='h-page__container']/div/div[4]/ul/li[1]/a"));
		overviewButton.click();
		File scrOverview = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrOverview, new File("D:\\Dima\\screenshot1.png"));

		WebElement facilitiesButton = driver
				.findElement(By.xpath("//div[@class='fixed-navbar js-fixed-navbar']/div/div/div/ul/li[2]"));
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(facilitiesButton));
		facilitiesButton.click();
		File scrFacilities = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFacilities, new File("D:\\Dima\\screenshot2.png"));

		WebElement roomsButton = driver
				.findElement(By.xpath("//div[@class='fixed-navbar js-fixed-navbar']/div/div/div/ul/li[3]"));
		roomsButton.click();
		File scrRooms = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrRooms, new File("D:\\Dima\\screenshot3.png"));

		WebElement locationButton = driver
				.findElement(By.xpath("//div[@class='fixed-navbar js-fixed-navbar']/div/div/div/ul/li[4]"));
		locationButton.click();
		File scrLocation = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrLocation, new File("D:\\Dima\\screenshot4.png"));

		WebElement reviewsButton = driver
				.findElement(By.xpath("//div[@class='fixed-navbar js-fixed-navbar']/div/div/div/ul/li[5]"));
		reviewsButton.click();
		File scrReviews = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrReviews, new File("D:\\Dima\\screenshot3.png"));

		WebElement findYourRoomButton = driver.findElement(By.xpath("//button[text()='Найдите ваш номер']"));
		findYourRoomButton.click();

		WebElement chooseDateFrom = driver.findElement(By.xpath(
				"//div[@class='h-page__container']/div/div[5]/div[3]/div[2]/div/form/div/div[@class='check-form']/div[1]/div[1]/span/span[2]"));
		chooseDateFrom.click();

		WebElement findMonth = driver.findElement(By.xpath(
				"//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[2]/thead/tr[1]/th[3]/span"));
		findMonth.click();
		findMonth.click();
		findMonth.click();
		findMonth.click();

		WebElement checkInTime = driver.findElement(By.xpath(
				"//div[@class='date-picker-wrapper no-shortcuts no-gap two-months']/div[2]/table[2]/tbody/tr[4]/td[5]/div"));
		checkInTime.click();

		WebElement checkOutTime = driver.findElement(By.xpath(
				"//div[@class='date-picker-wrapper no-shortcuts no-gap two-months']/div[2]/table[2]/tbody/tr[4]/td[6]/div"));
		checkOutTime.click();

		WebElement roomAndGuestsButton = driver.findElement(By
				.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[1]"));
		roomAndGuestsButton.click();

		WebElement addAdultPeople = driver.findElement(By.xpath(
				"//div[@class=\"js-r-and-g-container r-and-g-container\"]/div/div/div[2]/div/div/div[@data-type='adults']/div[@class='js-add btn-add']"));
		addAdultPeople.click();

		WebElement addChildPeople = driver.findElement(By.xpath(
				"//div[@class=\"js-r-and-g-container r-and-g-container\"]/div/div/div[2]/div/div[2]/div/div[3]"));
		addChildPeople.click();
		addChildPeople.click();

		WebElement oldOfFirstChildren = driver.findElement(By.xpath("//select[@data-child-age='1']/option[4]"));
		oldOfFirstChildren.click();

		WebElement oldSecondChildren = driver.findElement(By.xpath("//select[@data-child-age='2']/option[12]"));
		oldSecondChildren.click();

		String numberOfGuests = "5Гости / 1Номер";
		String guests = roomAndGuestsButton.getText();

		Assert.assertTrue("Выбрано неверное количество гостей или комнат", guests.equals(numberOfGuests));

		Thread.sleep(3000);
		WebElement showPrices = driver.findElement(By.xpath(
				"//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[2]/div/div[2]/button[1]"));

		wait.until(ExpectedConditions.elementToBeClickable(showPrices));

		Actions action = new Actions(driver);
		action.moveToElement(showPrices).click().perform();

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

		WebElement bookNow = driver.findElement(
				By.xpath("//*[@id=\"hotel-608662\"]/div[3]/div[2]/div/div/div/div[2]/div[2]/div[4]/button"));

		if (bookNow.isDisplayed()) {
			bookNow.click();

		} else {

			Assert.assertTrue(false);

		}

		File scrBookNow = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrBookNow, new File("D:\\Dima\\screenshotBook.png"));

		String arrivalDate = "2019-01-24";
		String arrivalDateOnSite = driver
				.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/span[2]"))
				.getText();
		String dateOfDeparture = "2019-01-25";
		String dateOfDepartureOnSite = driver
				.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[1]/div[2]/span[2]"))
				.getText();

		Assert.assertTrue("Неверная дата заезда", arrivalDateOnSite.equals(arrivalDate));
		Assert.assertTrue("Неверная дата выезда", dateOfDepartureOnSite.equals(dateOfDeparture));

		String peopleNumber = "3 Взрослых," + "2 Детей";
		String peopleNumberOnSite = driver
				.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[2]/div[1]/span[2]"))
				.getText().replaceAll("\n", "");
		System.out.println(peopleNumberOnSite);

		Assert.assertTrue("Неверная количество гостей", peopleNumberOnSite.equals(peopleNumber));

	}
}
