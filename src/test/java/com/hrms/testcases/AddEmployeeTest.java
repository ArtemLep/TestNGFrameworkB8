package com.hrms.testcases;

import com.hrms.pages.AddEmployee;
import com.hrms.pages.DashboardPage;
import com.hrms.pages.LoginPage;
import com.hrms.pages.VerifyingEmployee;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddEmployeeTest extends CommonMethods {
    @Test(groups = "smoke")
    public void addEmployee() {
        //login to the hrms
        LoginPage login = new LoginPage();
        login.login(ConfigsReader.getPropertyValue("username"), ConfigsReader.getPropertyValue("password"));
        //navigate to add employee page
        DashboardPage dash = new DashboardPage();
        JSclick(dash.PIMButton);
        JSclick(dash.addEmployeeBtn);
        //add employee
        AddEmployee addEmp = new AddEmployee(driver);
        sendText(addEmp.firstNameTextBox, "Artem");
        sendText(addEmp.lastNameTextbox, "Lepinskyi");
        click(addEmp.saveButton);
        //validation
        VerifyingEmployee verifyingEmployee=new VerifyingEmployee();
        //assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(addEmp.firstNameTextBox,verifyingEmployee.enteredFirstName);
    }

}