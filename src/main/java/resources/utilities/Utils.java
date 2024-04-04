package resources.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Utils {
    WebDriver driver;
    public Utils(WebDriver _driver){
        driver=_driver;
    }
    public void Click(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }


    public void Escribir(String Texto, String xpath){
        driver.findElement(By.xpath(xpath)).sendKeys(Texto);
    }

    public String getText(By locator){
        return driver.findElement(locator).getText();
    }
    public static void doScroll(WebDriver driver){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

}
