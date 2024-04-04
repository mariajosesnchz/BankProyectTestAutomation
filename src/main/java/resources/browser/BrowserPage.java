package resources.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BrowserPage {
    private WebDriver driver;
    public BrowserPage(WebDriver _driver) {
        driver = _driver;
    }
    public WebDriver OpenBrow(String url) {
        // Establecer la propiedad del controlador de Firefox
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Maria Jose\\Desktop\\PROYECTOS\\MY_PROJECTS\\BankProyectTestAutomation\\geckoDriver\\geckodriver.exe");

        // Opciones de configuración para Firefox
        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("start-maximized");

        // Iniciar el navegador Firefox con las opciones
        driver = new FirefoxDriver(options);

        // Abrir la URL especificada
        driver.get(url);

        // Establecer un tiempo de espera implícito
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);


        return driver;
    }

}
