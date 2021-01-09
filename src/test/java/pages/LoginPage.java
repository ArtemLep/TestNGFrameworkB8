package pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "txtUsername")
    public WebElement username;

    @FindBy(name = "txtPassword")
    public WebElement password;

    @FindBy(css = "input#btnLogin")
    public WebElement loginBtn;

    public LoginPage() {
        PageFactory.initElements(CommonMethods.driver, this);
    }

}
