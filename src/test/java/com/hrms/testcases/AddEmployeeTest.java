package com.hrms.testcases;

import com.hrms.pages.*;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelReading;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        VerifyingEmployee verifyingEmployee = new VerifyingEmployee();
        //assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(addEmp.firstNameTextBox, verifyingEmployee.enteredFirstName);
    }

    @Test(groups = "regresion")
    public void addMultipleEmployes() throws InterruptedException {
        LoginPage login = new LoginPage();
        login.login(ConfigsReader.getPropertyValue("username"), ConfigsReader.getPropertyValue("password"));
// navigate to add employee page
        DashboardPage dashboardPage = new DashboardPage();
        PersonalDetailPage personalDetailPage = new PersonalDetailPage(driver);
        JSclick(dashboardPage.PIMButton);
        JSclick(dashboardPage.addEmployeeBtn);
// add employee
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, "EmployeeData");
        AddEmployee addEmployeePage = new AddEmployee(driver);
        EmployeeListPage empList = new EmployeeListPage(driver);
        List<String> empIDlist = new ArrayList<>();
        SoftAssert softAssert = new SoftAssert();
        Iterator<Map<String, String>> it = newEmployees.iterator();
        while (it.hasNext()) {
            JSclick(dashboardPage.PIMButton);
            JSclick(dashboardPage.addEmployeeBtn);
            Map<String, String> mapNewEmployee = it.next();
            sendText(addEmployeePage.firstNameTextBox, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePage.middleNameTextbox, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePage.lastNameTextbox, mapNewEmployee.get("LastName"));
            String employeeIDValue = addEmployeePage.empIDTextbox.getAttribute("value");
            sendText(addEmployeePage.photograph, mapNewEmployee.get("Photograph"));
            if (!addEmployeePage.createLoginDetails.isSelected()) {
                addEmployeePage.createLoginDetails.click();
            }
            sendText(addEmployeePage.usernameCreate, mapNewEmployee.get("Username"));
            sendText(addEmployeePage.userPassword, mapNewEmployee.get("Password"));
            sendText(addEmployeePage.rePassword, mapNewEmployee.get("Password"));
            click(addEmployeePage.saveButton);


            JSclick(dashboardPage.PIMButton);
            JSclick(dashboardPage.employeeList);
            waitForClickability(empList.idEmployee);
            sendText(empList.idEmployee, employeeIDValue);
            click(empList.searchBtn);

            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id = 'resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                String expectedEmpDetails = employeeIDValue + " " + mapNewEmployee.get("FirstName") + " " + mapNewEmployee.get("MiddleName") + " " + mapNewEmployee.get("LastName");
                softAssert.assertEquals(rowText, expectedEmpDetails, "Data is NOT matched");


            }

        }

        softAssert.assertAll();

    }
}