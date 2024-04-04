package testrunner;

import entities.EntLogin;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.browser.BrowserPage;
import resources.utilities.Utils;
import scenarios.EscLogin;

import java.io.IOException;


public class ChangePasswordAndLoginTests {
    public static WebDriver driver;
    EntLogin entLogin;
    EscLogin escLogin;

    @BeforeTest
    public void PreEjecucion() {
        BrowserPage browserPage = new BrowserPage(driver);
        driver = browserPage.OpenBrow("https://www.demo.guru99.com/V4/");
    }

    @AfterTest
    public void PostEjecucion() {
        //driver.close();

        driver.manage().deleteAllCookies();
        driver.quit();
    }
    String actualpass= "1234568_";
    String newpass="12345_";
    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][]{
                {"mngr561845", actualpass},

        };
    }

    @DataProvider(name = "passwordChangeData")
    public Object[][] passwordChangeData() {
        return new Object[][]{
                {actualpass, newpass,newpass},
        };
    }

    @Test(dataProvider = "loginData")
    public void DoLogin(String username, String password) throws IOException, InterruptedException {
        entLogin = new EntLogin();
        entLogin.username = username;
        entLogin.password = password;
        escLogin = new EscLogin(driver);
        escLogin.Login(entLogin);

        try {
            // Intenta cambiar al cuadro de diálogo de alerta y aceptarlo
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No se encontró un cuadro de diálogo de alerta, continua con el cierre de sesión
            String header_actual = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[2]/td/marquee")).getText();
            String header_expected = "Welcome To Manager's";
            org.testng.Assert.assertTrue(header_actual.contains(header_expected));
            Utils utils= new Utils(driver);
            utils.doScroll(driver);

            /*String btnlogout = "/html/body/div[3]/div/ul/li[15]/a";
            utils.Click(btnlogout);
            Alert alert = driver.switchTo().alert();
            alert.accept();*/
        }
        /*String header_actual =driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();;
        String header_expected="Manger Id";
        org.testng.Assert.assertTrue(header_actual.contains(header_expected));*/
    }
    @Test(dataProvider = "passwordChangeData", dependsOnMethods = "DoLogin")
    public void DoPasswordChange(String oldpassword, String newPassword, String confirmpass) {
        entLogin = new EntLogin();
        entLogin.oldpassword= oldpassword;
        entLogin.newpassword=newPassword;
        entLogin.confirmpassword= confirmpass;
        escLogin = new EscLogin(driver);
        escLogin.ChangePassword(entLogin);
        try {
            // Intenta cambiar al cuadro de diálogo de alerta y aceptarlo
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            String header_actual = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[2]/td/marquee")).getText();
            String header_expected = "Welcome To Manager's";
            org.testng.Assert.assertTrue(header_actual.contains(header_expected));
            Utils utils= new Utils(driver);
            utils.doScroll(driver);

        }



    }


}