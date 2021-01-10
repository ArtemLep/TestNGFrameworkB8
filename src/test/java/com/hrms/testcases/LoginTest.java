package com.hrms.testcases;

import com.hrms.pages.DashboardPage;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.hrms.pages.LoginPage;
import org.testng.asserts.SoftAssert;

public class LoginTest extends CommonMethods {
    @Test(groups = "smoke")
    public void adminLogin() {
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, ConfigsReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigsReader.getPropertyValue("password"));
        click(login.loginBtn);
        //validation
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(DashboardPage.welcomeMessage.isDisplayed(), "Welcome message is NOT dispalyed");
    }

    @Test(dataProvider = "invalidData",groups = "regresion")
    public void dataDrivenLogin(String username, String password, String message) {
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernameBox, username);
        sendText(loginPage.passwordBox, password);
        click(loginPage.loginBtn);
        String actualEror= loginPage.errorMsg.getText();
        Assert.assertEquals(actualEror,message,"Error mesage text is not matched");
    }

    @DataProvider
    public Object[][] invalidData() {
        Object[][] data = {
                {"James", "123!", "Invalid credentials"},
                {"Admin1", "Hum@Syntax123!", "Invalid credentials"},
                {"James", "", "Password cannot be empty"},
                {"", "Syntax123!", "Username cannot be empty"}
        };
        return data;
    }
}
