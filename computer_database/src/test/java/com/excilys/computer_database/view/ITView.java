package com.excilys.computer_database.view;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ITView {
    private static WebDriver driver;
    private static String baseUrl;
    private boolean acceptNextAlert = true;

    /**
     * Launch the driver.
     * @throws Exception exception
     */
    @Before
    public void executeBeforeEachTest() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8181";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    /**
     * Quit the driver.
     * @throws Exception exception
     */
    @After
    public void executeAfterEachTest() throws Exception {
        driver.quit();
    }

    /**
     * Test.
     * @throws Exception if element not found
     */
    @Test
    public void testLinkClick() throws Exception {
        driver.get(
                baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
        driver.findElement(By.linkText("2")).click();
        driver.findElement(By.linkText("3")).click();
        driver.findElement(By.linkText("1")).click();
        assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));
        driver.findElement(By.linkText("6")).click();
        driver.findElement(By.linkText("8")).click();
        assert (isElementPresent(By.linkText(("Macintosh 128K"))));
        assert (isElementPresent(By.linkText(("11"))));
    }

    /**
     * Test.
     * @throws Exception if element not found
     */
    @Test
    public void testNextPreviousClick() throws Exception {
        driver.get(
                baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
        driver.findElement(By.linkText("›")).click();
        driver.findElement(By.linkText("›")).click();
        assert (isElementPresent(By.linkText(("Macintosh IIfx"))));
        driver.findElement(By.linkText("‹")).click();
        driver.findElement(By.linkText("‹")).click();
        assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));

    }

    /**
     * Test.
     * @throws Exception if element not found
     */
    @Test
    public void testOffsetClick() throws Exception {
        driver.get(
                baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
        assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));
        assertEquals(10,
                driver.findElements(By.xpath("//table[@id='table_computers']/tbody/tr")).size());
        driver.findElement(By.linkText("50")).click();
        assert (isElementPresent(By.linkText(("Commodore PET"))));
        assertEquals(50,
                driver.findElements(By.xpath("//table[@id='table_computers']/tbody/tr")).size());
        driver.findElement(By.linkText("100")).click();
        assert (isElementPresent(By.linkText(("Tinkertoy Tic-Tac-Toe Computer"))));
        assertEquals(100,
                driver.findElements(By.xpath("//table[@id='table_computers']/tbody/tr")).size());
        driver.findElement(By.linkText("10")).click();
        assert (!isElementPresent(By.linkText(("Apple II Plus"))));
        assertEquals(10,
                driver.findElements(By.xpath("//table[@id='table_computers']/tbody/tr")).size());
    }

    /**
     * Test.
     * @throws Exception if element not found
     */
    @Test
    public void testAddComputer() throws Exception {
        driver.get(
                baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
        driver.findElement(By.id("addComputer")).click();
        driver.findElement(By.id("computerName")).clear();
        driver.findElement(By.id("computerName")).sendKeys("Test");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        assertEquals("Computer Successfully created", closeAlertAndGetItsText());
        driver.findElement(By.id("searchsubmit")).click();
        driver.findElement(By.linkText("»")).click();
        assert (isElementPresent(By.linkText(("Test"))));
    }

    /**
     * Test.
     * @throws Exception if element not found
     */
    @Test
    public void testFilter() throws Exception {
        driver.get(
                baseUrl + "/computer_database/display_computers?page=1&offset=10&order=id&filter=");
        driver.findElement(By.id("searchbox")).clear();
        driver.findElement(By.id("searchbox")).sendKeys("apple");
        driver.findElement(By.id("searchsubmit")).click();
        assert (isElementPresent(By.linkText(("Apple Lisa"))));
    }

    /**
     * Test.
     * @throws Exception if element not found
     */
    @Test
    public void testOrder() throws Exception {
        driver.get(
                baseUrl + "/computer_database/display_computers?page=1&offset=10&order=&filter=");
        driver.findElement(By.xpath("//section[@id='main']/div[2]/table/thead/tr/th[2]/div/a[2]"))
                .click();
        assert (isElementPresent(By.linkText(("ZX Spectrum +3"))));
        driver.findElement(By.xpath("//section[@id='main']/div[2]/table/thead/tr/th[2]/div/a[2]"))
                .click();
        assert (isElementPresent(By.linkText(("ZX Spectrum +3"))));
        driver.findElement(By.xpath("//section[@id='main']/div[2]/table/thead/tr/th[2]/div/a"))
                .click();
        assert (isElementPresent(By.linkText(("Acer Extensa 5220"))));
        driver.findElement(By.linkText("Application - Computer Database")).click();
        assert (isElementPresent(By.linkText(("MacBook Pro 15.4 inch"))));
    }

    /**
     * Check the presence of an element.
     * @param by by
     * @return true if element is present, else false
     */
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Close an alert.
     * @return the message
     */
    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
