package com.example.crud.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EmployeeCrudSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);

        driver.manage().window().setSize(
                new Dimension(1400, 900)
        );
    }

    @Test
    void crudTest() throws InterruptedException {

        driver.get("http://localhost:5173");

        driver.findElement(By.id("name"))
                .sendKeys("ram");

        driver.findElement(By.id("email"))
                .sendKeys("ram@gmail.com");

        driver.findElement(By.id("salary"))
                .sendKeys("50000");

        driver.findElement(By.id("saveBtn"))
                .click();

        Thread.sleep(2000);

        driver.findElement(By.className("deleteBtn"))
                .click();

        Thread.sleep(2000);

        System.out.println("Current URL = " + driver.getCurrentUrl());
        System.out.println("Page Source = ");
        System.out.println(driver.getPageSource());

        Assertions.assertTrue(
                driver.getPageSource().contains("ram")
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}