import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HRSBookingPortal {

	public static void main(String[] args) {
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        // Navigate to the HRS booking portal
        driver.get("https://www.hrs.com/");
        // Handle cookie pop-up by accepting it
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
            acceptButton.click();
        } catch (Exception e) {
            // Cookie pop-up not found 
            System.out.println("Cookie pop-up not found or encountered an error while handling.");
        }
        
        // Find the Location input field and enter Barcelona
       
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement destinationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("DestinationSearchOpener_destinationFormat__hwuCN")));
        destinationInput.click();
        WebElement searchInput = driver.findElement(By.className("DestinationSearch_input__lLdOb"));
        searchInput.sendKeys("Barcelona");
        try {
        	Thread.sleep(3000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    // Get all the suggested search locations
	    List<WebElement> searchResults = driver.findElements(By.className("DestinationList_region__d_hv_"));
	    // Click on the search result by index (e.g., index 0)
	    int resultIndex = 0; // Change this to the desired index
	    if (resultIndex >= 0 && resultIndex < searchResults.size()) {
	        WebElement selectedResult = searchResults.get(resultIndex);
	        selectedResult.click();
	    } else {
	        System.out.println("Invalid result index: " + resultIndex);
	    }
	   
	    try {
	        Thread.sleep(3000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    // Get the check-in date input field and click on it to open the date picker
	    WebElement checkInInput = driver.findElement(By.className("DateRangeInputField_date__IAUhI"));
	    checkInInput.click();
	   
	     // Go to Next Month
        WebElement nextMonth = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[1]/div/div[1]/div/div[3]/div[2]/div/div[3]/div[2]/div[2]/div[1]/div[3]/img"));
        nextMonth.click();
        try {
	        Thread.sleep(2000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
        
        // Select start and end date
        WebElement startDate = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[1]/div/div[1]/div/div[3]/div[2]/div/div[3]/div[2]/div[2]/div[6]/div[6]"));
        startDate.click();
        WebElement endDate = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[1]/div/div[1]/div/div[3]/div[2]/div/div[3]/div[2]/div[2]/div[7]/div[4]"));
        endDate.click();
        
        // Click on search button
        WebElement searchButton = driver.findElement(By.id("SearchHotelsButton"));
        searchButton.click();
        
        try {
	        Thread.sleep(3000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
        
        List<WebElement> hotelCards = driver.findElements(By.className("HotelInfo_hotelName__HP58B"));
        // Iterate through each hotel card
        System.out.println("Numbers of Hotels - " + hotelCards.size());
        for (WebElement card : hotelCards) {
            // Get the text content of the hotel card
            String hotelName = card.getText();
            System.out.println("Hotel Name:- " + hotelName);
           
        }

	}
}

