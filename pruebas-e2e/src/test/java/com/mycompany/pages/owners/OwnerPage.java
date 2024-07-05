package com.mycompany.pages.owners;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


public class OwnerPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"main-navbar\"]/ul/li[2]/a")
    WebElementFacade ownerMenuLink;

    @FindBy(xpath = "//*[@id=\"search-owner-form\"]/div[2]/div/button")
    WebElementFacade ownerOptionButton;

    @FindBy(id = "owners")
    WebElementFacade ownersTitle;

    @FindBy(xpath = "/html/body/div/div/form/div[2]/div/button")
    WebElementFacade ownerSearchOptionMenu;

    @FindBy(xpath = "/html/body/div/div/table[1]/tbody/tr[1]/td/strong")
    WebElementFacade ownersTableName;

    @FindBy(xpath = "//*[@id=\"ownerInformation\"]")
    WebElementFacade ownerInformation;

    @FindBy(xpath = "/html/body/div/div/form/div[1]/div/div/input")
    WebElementFacade lastNameFindField;

    // Add owner
    @FindBy(xpath = "/html/body/div/div/a")
    WebElementFacade addOwnerButtonOption;

    @FindBy(id = "firstName")
    WebElementFacade firstNameField;

    @FindBy(id = "lastName")
    WebElementFacade lastNameField;

    @FindBy(id = "address")
    WebElementFacade addressField;

    @FindBy(id = "city")
    WebElementFacade cityField;

    @FindBy(id = "telephone")
    WebElementFacade telephoneField;

    @FindBy(xpath = "/html/body/div/div/form/div[2]/div/button")
    WebElementFacade addOwnerButton;


    @Step("Click on the owner menu link")
    public void clickOnOwnerMenuLink() {
        ownerMenuLink.waitUntilClickable().click();
    }


    @Step("Click on the owner search option")
    public void clickOnOwnerSearchOptionMenu() {
        ownerSearchOptionMenu.waitUntilClickable().click();
    }

    @Step("Get Owners header text")
    public String getOwnersHeaderText() {
        return ownersTitle.waitUntilVisible().getText();
    }

    @Step("Enter last name {0}")
    public void findLastName(String lastName) {
        lastNameFindField.waitUntilVisible().type(lastName);
    }

    @Step("Get Owners info")
    public void getOwnersInformation() {
        ownerInformation.waitUntilVisible();
    }
    // Add Owner
    @Step("Click Add Owner button option")
    public void clickAddOwnerButtonOption() {
        addOwnerButtonOption.waitUntilClickable().click();
    }

    @Step("Enter first name {0}")
    public void enterFirstName(String firstName) {
        firstNameField.waitUntilVisible().type(firstName);
    }

    @Step("Enter last name {0}")
    public void enterLastName(String lastName) {
        lastNameField.waitUntilVisible().type(lastName);
    }

    @Step("Enter address {0}")
    public void enterAddress(String address) {
        addressField.waitUntilVisible().type(address);
    }

    @Step("Enter city {0}")
    public void enterCity(String city) {
        cityField.waitUntilVisible().type(city);
    }

    @Step("Enter telephone {0}")
    public void enterTelephone(String telephone) {
        telephoneField.waitUntilVisible().type(telephone);
    }

    @Step("Enter owner data")
    public void enterOwnerData() {
        String firstName = Serenity.sessionVariableCalled("firstName");
        String lastName = Serenity.sessionVariableCalled("lastName");
        String address = Serenity.sessionVariableCalled("address");
        String city = Serenity.sessionVariableCalled("city");
        String telephone = Serenity.sessionVariableCalled("telephone");
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterTelephone(telephone);
    }

    @Step("Click Add Owner button")
    public void clickAddOwnerButton() {
        addOwnerButton.waitUntilClickable().click();
    }

    @Step("Get full name")
    public String getFullName() {
        return ownersTableName.waitUntilVisible().getText();
    }

}
