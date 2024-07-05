package com.mycompany.steps;

import com.mycompany.pages.owners.veterinariansPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;


public class veterinariansSteps {
    @Steps
    veterinariansPage veterinariansPage;

    @Given("el cliente abre el navegador web")
    public void elClienteAbreElNavegadorWeb() {
        veterinariansPage.open();
    }

    @When("el cliente navega al menú encontrar veterinarios")
    public void elClienteNavegaAlMenúEncontrarVeterinarios() {
        veterinariansPage.clickVeterinarianMenuLink();
    }

    @Then("la página debe mostrar una lista de veterinarios")
    public void laPáginaDebeMostrarUnaListaDeVeterinarios() {
        veterinariansPage.geteterinarianInformation();
    }


}
