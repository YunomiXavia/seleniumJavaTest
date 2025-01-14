package com.automationtesting.tests;

import com.automationtesting.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class LoginTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test(dependsOnMethods = {"com.automationtesting.tests.RegisterTest.testRegistration"})
    public void testLogin() {
        logger.info("Bắt đầu kiểm thử đăng nhập");
        driver.get(baseUrl + "my-account/");

        WebElement emailLogin = driver.findElement(By.id("username"));
        WebElement passwordLogin = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.name("login"));

        emailLogin.sendKeys(loginedEmail);
        logger.info("Đã nhập email đăng nhập: {}", loginedEmail);
        passwordLogin.sendKeys(loginedPassword);
        logger.info("Đã nhập mật khẩu đăng nhập");
        loginButton.click();
        logger.info("Đã nhấn nút đăng nhập");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
            Assert.assertTrue(logoutLink.isDisplayed(), "Đăng nhập thành công, hiển thị liên kết Logout.");
            logger.info("Đăng nhập thành công, liên kết Logout hiển thị");
        } catch (Exception e) {
            logger.error("Đăng nhập không thành công", e);
            takeScreenshot("LoginTest_failure.png");
            Assert.fail("Đăng nhập không thành công.");
        }
    }

    private void takeScreenshot(String fileName) {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + fileName;
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            logger.info("Đã lưu ảnh chụp màn hình tại: {}", screenshotPath);
        } catch (IOException e) {
            logger.error("Lỗi khi lưu ảnh chụp màn hình", e);
        }
    }
}
