package com.herokuapp.internet;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginIT {
    @Test
    public void usernameFailed(){
        // create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // open page
        String url ="http://the-internet.herokuapp.com/login";
        driver.get(url);
        //driver.manage().window().maximize();
        // input username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmithx");
        // input password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");
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
        Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual Message Username Tidak sama dengan Expected Message");


        sleep(3000);
        driver.quit();
    }

    @Test
    public void passwordFailed(){
        // create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // open page
        String url ="http://the-internet.herokuapp.com/login";
        driver.get(url);
        //driver.manage().window().maximize();
        // input username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");
        // input password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword");
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
        Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual Message Password Tidak sama dengan Expected Message");

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

