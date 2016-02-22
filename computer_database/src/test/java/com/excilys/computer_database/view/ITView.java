package com.excilys.computer_database.view;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ITView {
	private static WebDriver driver;
	private static String baseUrl;

	@Before
	public void executeBeforeEachTest() throws Exception {
		 driver = new FirefoxDriver(); 
		 baseUrl = "http://localhost:8181/";
		 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@After
	public void executeAfterEachTest() throws Exception {
		driver.quit();
	}

	@Test
	public void testLinkClick() throws Exception {
		driver.get(baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
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
		driver.get(baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
		driver.findElement(By.linkText("»")).click();
		driver.findElement(By.linkText("»")).click();
		assert (isElementPresent(By.linkText(("Macintosh IIfx"))));
		driver.findElement(By.linkText("«")).click();
		driver.findElement(By.linkText("«")).click();
		assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));
	}

	@Test
	public void testOffsetClick() throws Exception {
		driver.get(baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
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
		driver.get(baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
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
