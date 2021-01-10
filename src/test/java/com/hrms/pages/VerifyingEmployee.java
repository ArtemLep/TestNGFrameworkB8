package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyingEmployee {
    @FindBy(id="personal_txtEmpFirstName")
    public WebElement enteredFirstName;
    @FindBy(id="personal_txtEmpLastName")
    public WebElement enteredLastName;
    @FindBy(id="personal_txtEmployeeId")
    public WebElement givenEmployeeId;
    public VerifyingEmployee(){
        PageFactory.initElements(CommonMethods.driver,this);
    }
}
