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

public class RegisterTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RegisterTest.class);

    @Test
    public void testRegistration() {
        logger.info("Bắt đầu kiểm thử đăng ký");
        driver.get(baseUrl + "my-account/");

        registeredEmail = "yunom2834@gmail.com";
        logger.info("Email đăng ký: {}", registeredEmail);
        String password = registeredPassword;

        WebElement emailReg = driver.findElement(By.id("reg_email"));
        WebElement passwordReg = driver.findElement(By.id("reg_password"));
        WebElement registerButton = driver.findElement(By.name("register"));

        emailReg.sendKeys(registeredEmail);
        logger.info("Đã nhập email đăng ký");
        passwordReg.sendKeys(password);
        logger.info("Đã nhập mật khẩu đăng ký");
        registerButton.click();
        logger.info("Đã nhấn nút đăng ký");

        // Sử dụng WebDriverWait để đợi liên kết "Logout" xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
            Assert.assertTrue(logoutLink.isDisplayed(), "Đăng ký thành công, hiển thị liên kết Logout.");
            logger.info("Đăng ký thành công, liên kết Logout hiển thị");
        } catch (Exception e) {
            logger.error("Đăng ký không thành công", e);
            takeScreenshot("RegisterTest_failure.png");
            Assert.fail("Đăng ký không thành công.");
        }
    }

    // Phương thức chụp ảnh màn hình khi kiểm thử thất bại
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
