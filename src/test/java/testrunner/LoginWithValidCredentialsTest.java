package testrunner;

import entities.EntLogin;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.browser.BrowserPage;
import resources.data.excel.DataExcel;
import scenarios.EscLogin;


import org.openqa.selenium.WebDriver;

import javax.swing.table.DefaultTableModel;

import java.io.IOException;


public class LoginWithValidCredentialsTest {
    public static WebDriver driver;
    EntLogin entLogin;
    EscLogin escLogin;
    DataExcel dataExcel;
    int numerofila= 0; //numero de fila a actualizar (indice 0)

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

        entLogin.username= properties.getProperty("username");
        entLogin.password=properties.getProperty("password");*/
        dataExcel.excelAbrirConexion(false);
        dataTable= dataExcel.excelTabla();
        entLogin.username = dataExcel.obtenerValorColumna(numerofila,"username").toString();
        entLogin.password= dataExcel.obtenerValorColumna(numerofila,"password").toString();
        dataExcel.excelcerrarConexion();

    }

    private DefaultTableModel dataTable;

    @Test //User can do login successfully
    public void TestValidCredencials() throws IOException {
        InstanciasBasicas();
        escLogin.Login(entLogin);
        String header_actual =  driver.findElement(By.xpath("/html/body/div[2]/h2")).getText();
        String header_expected = "Guru99 Bank";
        org.testng.Assert.assertTrue(header_actual.contains(header_expected));

    }



}
