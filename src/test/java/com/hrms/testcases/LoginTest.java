package com.hrms.testcases;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends CommonMethods {
    @Test
    public void adminLogin() {
        LoginPage login = new LoginPage();
        sendText(login.username, ConfigsReader.getPropertyValue("username"));

        sendText(login.password, ConfigsReader.getPropertyValue("pasword"));
        click(login.loginBtn);
    }
}
