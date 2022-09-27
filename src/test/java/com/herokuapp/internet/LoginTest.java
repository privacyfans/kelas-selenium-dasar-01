package com.herokuapp.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
    @Parameters({"username_u","password_u","expectedUrl"})
    @Test(priority = 1, groups = {"negativeTest","smokeTest"})
    public void loginSuccess(String username,String password, String expectedUrl){
        // create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // open page
        String url ="http://the-internet.herokuapp.com/login";
        driver.get(url);
        driver.manage().window().maximize();
        // input username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        // input password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        // click button login
        WebElement buttonLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonLogin.click();
        // validate loagin success
        // 1. url change to /secure
        //String expedtedURL = "http://the-internet.herokuapp.com/secure";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,expectedUrl,"URL expedted dan URL actual Tidak Sama");

        // 2. message success is displayed
        // 3. button logout is displayed

        sleep(3000);





        driver.quit();
    }

    @Parameters({"username_u","password_u","errorMessage_u"})
    @Test(priority = 2, groups = {"negativeTest","smokeTest"})
    public void usernameFailed(String username,String password, String message){
        // create driver
//        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // open page
        String url ="http://the-internet.herokuapp.com/login";
        driver.get(url);
        //driver.manage().window().maximize();
        // input username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        // input password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        // click button login
        WebElement buttonLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonLogin.click();
        // validate loagin success
        // 1. url change to /secure
        String expedtedURL = "http://the-internet.herokuapp.com/login";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,expedtedURL,"URL expedted dan URL actual Tidak Sama");

        // 2. message error username
        String expectedMessage = "Your username is invalid!";
        WebElement toastMessage = driver.findElement(By.id("flash"));
        String actualMessage = toastMessage.getText();
        Assert.assertTrue(actualMessage.contains(message),"Actual Message Username Tidak sama dengan Expected Message");


        sleep(3000);
        driver.quit();
    }

    @Parameters({"username_p","password_p","errorMessage_p"})
    @Test(priority = 3,groups = {"negativeTest"})
    public void passwordFailed(String username,String password, String message){
        // create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // open page
        String url ="http://the-internet.herokuapp.com/login";
        driver.get(url);
        //driver.manage().window().maximize();
        // input username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        // input password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        // click button login
        WebElement buttonLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonLogin.click();
        // validate login failed
        // 1. url change to /login
        String expedtedURL = "http://the-internet.herokuapp.com/login";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,expedtedURL,"URL expedted dan URL actual Tidak Sama");

        // 2. message error username
        String expectedMessage = "Your password is invalid!";
        WebElement toastMessage = driver.findElement(By.id("flash"));
        String actualMessage = toastMessage.getText();
        Assert.assertTrue(actualMessage.contains(message),"Actual Message Password Tidak sama dengan Expected Message");

        sleep(3000);
        driver.quit();
    }
    private void sleep(long time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
