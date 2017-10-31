package org.testingtalk.selenium;

import java.io.File;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest
{
    @Test
    @Ignore
    public void googleTest() throws  Exception{
       //  Создаем новый webdriver с указанием geckodriver
        System.setProperty("webdriver.gecko.driver", "${ путь до geckodriver }");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("${ путь до firefox}");
        WebDriver wd = new FirefoxDriver();
        // Открываем гугл
        wd.get("http://www.google.com");
        final String searchString = "selenium";
        // Находим поисковую строку и ищем "selenium"
        WebElement searchBox = wd.findElement(By.name("q"));
        searchBox.sendKeys(searchString);
        searchBox.submit();
        // т.к. google асинхронный - ждем поисковой выдачи
        (new WebDriverWait(wd, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(searchString);
            }
        });
        // закрываем webdriver
        wd.quit();
    }
}
