package stepDefinitions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import io.cucumber.java.en.*;

public class DailyMailHome {

	public WebDriver driver;
	int imagecounter = 0;
	
	@Given("User launches browser")
	@Test
	public void user_launches_browser() {
		System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		System.out.println("Browser opened.");
		
	}

	@When("User navigates to DailyMail home page")
	@Test
	public void user_navigates_to_daily_mail_home_page() {
		driver.get("https://www.dailymail.co.uk/home/index.html");
		driver.manage().window().maximize();
		System.out.println("URL opened in browser.");
	}

	@Then("User validates the current date and time")
	@Test
	public void user_validates_the_current_date_and_time() {
		
		boolean blnDate= driver.findElement(By.xpath("//*[@id=\"weather-wrapper\"]/strong")).isDisplayed();
		boolean ActualTime = driver.findElement(By.xpath("//*[@id=\"weather-wrapper\"]/a/span[1]")).isDisplayed();
		
		if (blnDate && ActualTime)
		{
			System.out.println("Date is displayed");
			System.out.println("Time is displayed");
		}
	}


	@When("User clicks on Sports menu")
	@Test
	public void user_clicks_on_sports_menu() {

		WebElement sport = driver.findElement(By.xpath("//*[@id='page-container']/div[2]/ul/li[4]/span/a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", sport);
		System.out.println("Sport menu clicked successfully.");

	}
	
	@Then("User validates the primary and secondary navigation colors")
	@Test
	public void user_validates_the_primary_and_secondary_navigation_colors() {

		
		String primarynavcolor = driver.findElement(By.xpath("//*[@id=\"page-container\"]/div[2]/ul/li[4]")).getCssValue("background-color");
		String secondarynavcolor = driver.findElement(By.xpath("//*[@id=\"page-container\"]/div[2]/div[2]/div/ul[1]/li[1]/a")).getCssValue("background-color");
		
		System.out.println(driver.findElement(By.xpath("//*[@id=\"page-container\"]/div[2]/ul/li[4]")).getCssValue("background-color"));
		System.out.println(driver.findElement(By.xpath("//*[@id=\"page-container\"]/div[2]/div[2]/div/ul[1]/li[1]/a")).getCssValue("background-color"));
		
		String hex = Color.fromString(primarynavcolor).asHex();
		String hex1 = Color.fromString(secondarynavcolor).asHex();
		
		if (hex.equalsIgnoreCase(hex1))
		{
			System.out.print("Primary and Secondary navigation color matched");
		}
		
	}

		@When("User clicks on Football sub-navigation")
		@Test
		public void user_clicks_on_football_sub_navigation() {
			WebElement football = driver.findElement(By.xpath("//*[@id=\'page-container\']/div[2]/div[2]/div/ul[1]/li[1]/a"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", football);
			System.out.println("Football sub-menu clicked successfully.");
		}
		
		@Then("User clicks the first article displayed and traverse to the gallery")
		@Test
		public void user_clicks_the_first_article_displayed_and_traverse_to_the_gallery() {
			
			WebElement firstarticle = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/h2/a[1]"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", firstarticle);
			driver.get("https://www.dailymail.co.uk/sport/football/article-11091451/Barcelona-threaten-legal-action-Frenkie-Jongs-2020-contract-extension.html");
			System.out.println("First article is clicked");
		}


		@When("User clicks on the gallery icon")
		@Test
		public void user_clicks_on_the_gallery_icon() throws InterruptedException {
			
			Thread.sleep(10000);

			WebElement gallery = driver.findElement(By.xpath("//*[@id=\'js-article-text\']/div[3]/div[1]/div/div[1]/button/div[2]"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", gallery);
			System.out.println("Gallery icon is clicked");
		}
		
		@Then("gallery load on full screen and has previous and next buttons")
		@Test
		public void gallery_load_on_full_screen_and_has_previous_and_next_buttons() {

			WebElement nextButton = driver.findElement(By.className("nextButton-3SUcS"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", nextButton);			
			System.out.println("Next button is clicked");
			
			WebElement prevButton = driver.findElement(By.className("previousButton-1MRE_"));
			js.executeScript("arguments[0].click()", prevButton);
			System.out.println("Previous button is clicked");

			WebElement closeButton = driver.findElement(By.className("closeButton-3n9vO"));
			closeButton.click();
			System.out.println("Close button is clicked");
		}
		
		
	
	@Then("User clicks on Facebook icon and validates modal dialog opens")
	@Test
	public void user_clicks_on_facebook_icon_and_validates_modal_dialog_opens() throws InterruptedException {
		WebElement facebook = driver.findElement(By.xpath("//*[@id=\'js-article-text\']/div[3]/div[1]/div/div[2]/ul/li[1]"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", facebook);
		
		Thread.sleep(10000);
		
		String parent = driver.getWindowHandle();
		Set<String> childs = driver.getWindowHandles();

		Iterator <String> I1 = childs.iterator();
		
		while(I1.hasNext())
		{
			String child_window = I1.next();
			
			if (!child_window.equalsIgnoreCase(parent) )
			{
				driver.switchTo().window(child_window);
				System.out.println("Child Window Title : " + driver.getTitle());
				if (driver.getTitle().equalsIgnoreCase("Facebook"))
				{
					System.out.println("Facebook modal successfully opened.");
					System.out.println("Facebook modal successfully closed.");
				}
				driver.close();
			}
		
		}

		driver.switchTo().window(parent);

	}
	
	@Then("User clicks on embeded video and validates fullscreen and minimize mode")
	@Test
	public void user_clicks_on_embeded_video_and_validates_fullscreen_and_minimize_mode() throws InterruptedException {
		WebElement gallery = driver.findElement(By.xpath("//*[@id=\'js-article-text\']/div[3]/div[1]/div/div[1]/button/div[2]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].click()", gallery);
		Thread.sleep(2000);
		
		WebElement nextButton = driver.findElement(By.className("nextButton-3SUcS"));
		 
			while(!driver.findElement(By.xpath("//*[@id=\'vjs_video_3-clone\']/div[7]/div[8]")).isDisplayed())
			{
				js.executeScript("arguments[0].click()", nextButton);			
				
			}

			Thread.sleep(5000);
			
			WebElement maxButton = driver.findElement(By.xpath("//*[@id=\'vjs_video_3-clone\']/div[7]/div[8]"));
			maxButton.click();
			System.out.println("Video full screen is clicked and displayed.");
			Thread.sleep(1000);
			maxButton.click();
			System.out.println("Video full screen is minimized and displayed.");
			Thread.sleep(1000);
		 
		WebElement closeButton = driver.findElement(By.className("closeButton-3n9vO"));

		closeButton.click();

	}
	
			

	@Then("User exhibit the points and position from PREMIER LEAGUE table for {string} team")
	@Test
	public void user_exhibit_the_points_and_position_from_premier_league_table_for_team(String teamname) {

		List<WebElement> TeamNames = driver.findElements(By.xpath("//td[contains(@class,'team_1zTYu team-short_2uYdY')]"));
		List<WebElement> TeamPositions = driver.findElements(By.xpath("//td[contains(@class,'pos_3b93p')]"));
		List<WebElement> TeamPoints = driver.findElements(By.xpath("//td[contains(@class,'score-pts_TNAV7')]"));
		
		int i=0;
		
		for (WebElement cell:TeamNames)
		{
			String TeamName = cell.getAttribute("innerText");
			if(TeamName.equals(teamname))
			{
				System.out.println("Expected Team found : " + TeamName);
				System.out.println(TeamName+ "  Team's position : " + TeamPositions.get(i).getAttribute("innerText"));
				System.out.println(TeamName+ "  Team's position : " + TeamPoints.get(i).getAttribute("innerText"));
				break;
			}
			i++;

		}

	}
	
	@Then("User closes the browser")
	@Test
	public void user_closes_the_browser() {
		driver.quit();
	}

	
}
