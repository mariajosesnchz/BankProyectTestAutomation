package scenarios;

import entities.EntLogin;
import resources.utilities.Utils;
import org.openqa.selenium.WebDriver;

public class EscLogin {
    WebDriver driver;
    public EscLogin(WebDriver _driver){
        driver=_driver;
    }
    public void Login(EntLogin entLogin){
        Utils utils= new Utils(driver);
        String usser =" //*[@name='uid']";;
        String pass=" //*[@name='password']";
        String btnLogin= " //*[@name='btnLogin']";

        utils.Escribir(entLogin.username, usser);
        utils.Escribir(entLogin.password,pass);
        utils.Click(btnLogin);
    }
    public void ChangePassword(EntLogin entLogin){
        Utils utils= new Utils(driver);
        String changePass= "/html/body/div[3]/div/ul/li[11]/a";
        utils.Click(changePass);
        String oldpass =" //*[@name='oldpassword']";
        String newpass=" //*[@name='newpassword']";
        String confirmpass="//*[@name='confirmpassword']";
        String btnsubmit="//*[@name='sub']";
        utils.Escribir(entLogin.oldpassword,oldpass);
        utils.Escribir(entLogin.newpassword,newpass);
        utils.Escribir(entLogin.confirmpassword,confirmpass);
        //utils.Escribir(entLogin.newpassword,confirmpass);
        utils.Click(btnsubmit);
    }
}
