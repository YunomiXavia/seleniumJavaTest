package com.automationtesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    protected WebDriver driver;
    protected final String baseUrl = "https://practice.automationtesting.in/";
    protected static String registeredEmail;
    protected static String registeredPassword = "Dinhanst2832004%%";
    protected static String loginedEmail = "yunom2834@gmail.com";
    protected static String loginedPassword = "Dinhanst2832004%%";
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() {
        logger.info("Thiết lập WebDriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("WebDriver đã được thiết lập và trình duyệt đã được mở");
    }

    @AfterClass
    public void tearDown() {
        logger.info("Đóng trình duyệt");
        if (driver != null) {
            driver.quit();
            logger.info("Trình duyệt đã được đóng");
        }
    }
}
