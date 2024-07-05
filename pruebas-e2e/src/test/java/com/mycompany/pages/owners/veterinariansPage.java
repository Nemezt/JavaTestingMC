package com.mycompany.pages.owners;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class veterinariansPage extends PageObject{

    @FindBy(xpath = "/html/body/nav/div/div/ul/li[3]/a")
    WebElementFacade  veterinarianMenuLink;

    @FindBy(xpath = "/html/body/div/div/div[1]/table")
    WebElementFacade veterinarianInformation;

    @Step("Click on the owner menu link")
    public void clickVeterinarianMenuLink() {
        veterinarianMenuLink.waitUntilClickable().click();
    }

    @Step("Get veterinarians info")
    public void geteterinarianInformation() {
        veterinarianInformation.waitUntilVisible();
    }
}
