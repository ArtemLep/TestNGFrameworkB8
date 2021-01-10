package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployee extends CommonMethods {
    @FindBy(id = "firstName")
    public WebElement firstNameTextBox;

    @FindBy(id = "middleName")
    public WebElement middleNameTextbox;

    @FindBy(id = "lastName")
    public WebElement lastNameTextbox;

    @FindBy(id = "employeeId")
    public WebElement empIDTextbox;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(id = "chkLogin")
    public WebElement createLoginCheckbox;

    public AddEmployee(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
