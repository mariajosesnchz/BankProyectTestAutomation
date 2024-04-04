package testrunner;

import entities.EntLogin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.browser.BrowserPage;
import resources.data.excel.DataExcel;
import scenarios.EscLogin;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class LoginWithInvalidCredentialsTest {
    public static WebDriver driver;
    EntLogin entLogin;
    EscLogin escLogin;
    DataExcel dataExcel;
    int numerofila= 1; //numero de fila a actualizar (indice 1)

    @BeforeTest
    public void PreEjecucion(){

        BrowserPage browserPage=new BrowserPage(driver);
        driver=browserPage.OpenBrow("https://www.demo.guru99.com/V4/");
    }

    @AfterTest
    public void PostEjecucion(){
        //driver.close();
    }


    public void InstanciasBasicas() throws IOException {
        dataExcel= new DataExcel();
        entLogin= new EntLogin();
        escLogin=new EscLogin(driver);
        ReadData();
    }
    public void ReadData() throws IOException {
       /* Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Maria Jose\\Desktop\\PROYECTOS\\MY_PROJECTS\\BankProyectTestAutomation\\src\\main\\java\\Resources\\Data\\Data.properties");
        properties.load(fis);

        entLogin.username= properties.getProperty("invalidusername");
        entLogin.password=properties.getProperty("password");*/
        dataExcel.excelAbrirConexion(false);
        dataTable= dataExcel.excelTabla();
        entLogin.username = dataExcel.obtenerValorColumna(numerofila,"username").toString();
        entLogin.password= dataExcel.obtenerValorColumna(numerofila,"password").toString();
        dataExcel.excelcerrarConexion();
    }
    private DefaultTableModel dataTable;
    @Test //User cannot do login successfully
    public void TestInvalidCredentials() throws IOException {
        InstanciasBasicas();
        escLogin.Login(entLogin);
        Alert alert = driver.switchTo().alert();
        //  Verify the text of the dialog box
        String alertText = alert.getText();
        Assert.assertTrue(alertText.contains("User or Password is not valid"));
    }


}
