package com.rentacar.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Disabled("Selenium tests are disabled for CI/CD")
public class SeleniumTest {

    @Autowired
    private com.rentacar.repository.MarkaRepository markaRepository;
    @Autowired
    private com.rentacar.repository.ModelRepository modelRepository;
    @Autowired
    private com.rentacar.repository.SubeRepository subeRepository;
    @Autowired
    private com.rentacar.repository.SinifRepository sinifRepository;
    @Autowired
    private com.rentacar.repository.PersonelRepository personelRepository;

    private com.rentacar.model.Model testModel;
    private com.rentacar.model.Sube testSube;
    private com.rentacar.model.Sinif testSinif;

    private WebDriver driver;
    private final String BASE_URL = "http://localhost:9090";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Seed Data
        if (markaRepository.count() == 0) {
            com.rentacar.model.Marka marka = new com.rentacar.model.Marka();
            marka.setAd("Test Marka");
            markaRepository.save(marka);
        }

        if (modelRepository.count() == 0) {
            testModel = new com.rentacar.model.Model();
            testModel.setAd("Test Model");
            testModel.setMarka(markaRepository.findAll().get(0));
            testModel = modelRepository.save(testModel);
        } else {
            testModel = modelRepository.findAll().get(0);
        }

        if (subeRepository.count() == 0) {
            testSube = new com.rentacar.model.Sube();
            testSube.setAd("Test Sube");
            testSube = subeRepository.save(testSube);
        } else {
            testSube = subeRepository.findAll().get(0);
        }

        if (sinifRepository.count() == 0) {
            testSinif = new com.rentacar.model.Sinif();
            testSinif.setAd("Test Sinif");
            testSinif = sinifRepository.save(testSinif);
        } else {
            testSinif = sinifRepository.findAll().get(0);
        }

        // Seed Admin User
        if (personelRepository.findByKullaniciAdi("admin") == null) {
            com.rentacar.model.Personel admin = new com.rentacar.model.Personel();
            admin.setAd("Admin");
            admin.setSoyad("User");
            admin.setKullaniciAdi("admin");
            admin.setSifre("admin123");
            admin.setRol("ADMIN");
            admin.setSube(testSube);
            personelRepository.save(admin);
        }
    }

    @Test
    public void testHomePageTitle() {
        driver.get(BASE_URL);
        String title = driver.getTitle();
        assertTrue(title.contains("LuxeDrive"));
    }

    @Test
    public void testAddCarAndVerify() throws InterruptedException {
        // 1. Go to Login Page
        driver.get(BASE_URL + "/login.html");
        Thread.sleep(2000);

        // 2. Login as Admin
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(2000);

        // 3. Go to Admin Page (should be redirected automatically or we can navigate)
        // Since login redirects to admin.html for admins, we just wait/verify
        // But to be safe in test flow:
        driver.get(BASE_URL + "/admin.html");
        Thread.sleep(2000);

        // 4. Fill Form
        String plaka = "TEST" + System.currentTimeMillis(); // Unique plaka
        driver.findElement(By.id("plaka")).sendKeys(plaka);
        driver.findElement(By.id("yil")).sendKeys("2024");
        Thread.sleep(2000);

        // Set hidden fields dynamically
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("document.getElementById('modelId').value = arguments[0];", testModel.getId());
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("document.getElementById('subeId').value = arguments[0];", testSube.getId());
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("document.getElementById('sinifId').value = arguments[0];", testSinif.getId());

        // 5. Submit
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(2000);

        // 6. Wait for success message
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions
                        .visibilityOfElementLocated(By.id("successMsg")));

        // 7. Go to Home Page
        driver.get(BASE_URL);
        Thread.sleep(2000);

        // 8. Verify Car is Listed
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions
                        .textToBePresentInElementLocated(By.id("car-list"), plaka));

        boolean isPresent = driver.getPageSource().contains(plaka);
        assertTrue(isPresent, "Added car should be visible on the home page");
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
