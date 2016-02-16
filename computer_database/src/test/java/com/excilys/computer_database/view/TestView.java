package com.excilys.computer_database.view;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

public class TestView {
	private WebDriver driver;
	private String baseUrl;
	private static SeleniumServer server;

	@BeforeClass
	public static void executeBeforeEachTest() throws Exception {
		/*
		 * driver = new FirefoxDriver(); 
		 * baseUrl = "http://localhost:8080/";
		 * driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		 */

		RemoteControlConfiguration conf = new RemoteControlConfiguration();
		conf.setPort(4444);
		conf.setDebugURL("/wd/hub");
		server = new SeleniumServer(conf);
		server.start();

		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), DesiredCapabilities.firefox());
	}

	@AfterClass
	public static void executeAfterEachTest() throws Exception {
		server.stop();
	}
	
	@Test
	public void test1(){
		driver.getCurrentUrl();
	}

	@Test
	public void testLinkClick() throws Exception {
		driver.get(baseUrl + "/computer_database/displayComputers?page=1&offset=10");
		driver.findElement(By.linkText("2")).click();
		driver.findElement(By.linkText("3")).click();
		driver.findElement(By.linkText("1")).click();
		assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));
		assert (!isElementPresent(By.linkText(("«"))));
		driver.findElement(By.linkText("8")).click();
		assert (isElementPresent(By.linkText(("Amiga 1000"))));
		assert (isElementPresent(By.linkText(("11"))));
		assert (isElementPresent(By.linkText(("«"))));
	}

	@Test
	public void testNextPreviousClick() throws Exception {
		driver.get(baseUrl + "/computer_database/displayComputers?page=1&offset=10");
		driver.findElement(By.linkText("»")).click();
		driver.findElement(By.linkText("»")).click();
		assert (isElementPresent(By.linkText(("Macintosh IIfx"))));
		driver.findElement(By.linkText("«")).click();
		driver.findElement(By.linkText("«")).click();
		assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));
	}

	@Test
	public void testOffsetClick() throws Exception {
		driver.get(baseUrl + "/computer_database/displayComputers?page=1&offset=10");
		assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));
		driver.findElement(By.linkText("50")).click();
		assert (isElementPresent(By.linkText(("Commodore PET"))));
		driver.findElement(By.linkText("100")).click();
		assert (isElementPresent(By.linkText(("Tinkertoy Tic-Tac-Toe Computer"))));
		driver.findElement(By.linkText("10")).click();
		assert (!isElementPresent(By.linkText(("Apple II Plus"))));
	}

	@Test
	public void testAddComputer() throws Exception {
		driver.get(baseUrl + "/computer_database/displayComputers?page=1&offset=10");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("Test");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		assertTrue(isAlertPresent());
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}
