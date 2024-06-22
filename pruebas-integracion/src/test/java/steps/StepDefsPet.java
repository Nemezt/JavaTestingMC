package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import java.util.HashMap;
import java.util.Map;
import static net.serenitybdd.rest.SerenityRest.*;
import static net.serenitybdd.rest.SerenityRest.rest;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;


public class StepDefsPet {
    private final String URI_BASE = "https://petstore3.swagger.io/api/v3";


    @Given("el cliente configura la URI base")
    public void elClienteConfiguraLaURIBase() {
        rest().baseUri(URI_BASE);
    }


    @When("el cliente realiza una peticion GET a {string}")
    public void elClienteRealizaUnaPeticionGETA(String path) {
        when().get(URI_BASE.concat(path)).andReturn();
    }

    @Then("el servidor debe de responder con un status {int}")
    public void elServidorDebeDeResponderConUnStatus(int arg0) {
        SerenityRest.then().statusCode(arg0);
    }

    @And("el cuerpo de la respuesta contiene la propiedad id con el valor {int}")
    public void elCuerpoDeLaRespuestaContieneLaPropiedadIdConElValor(int arg0) {
        SerenityRest.then().body("id", Matchers.is(arg0));
    }

    @And("el cuerpo de la respuesta contiene la propiedad name con el valor {string}")
    public void elCuerpoDeLaRespuestaContieneLaPropiedadNameConElValor(String arg0) {
        SerenityRest.then().body("name", Matchers.is(arg0));
    }



    @And("el cuerpo de la respuesta debe de ser una lista de mascota con el estado")
    public void elCuerpoDeLaRespuestaDebeDeSerUnaListaDeMascotaConElEstado() {
        SerenityRest.then().body("$", org.hamcrest.Matchers.not(Matchers.empty()));
        SerenityRest.then().body("[0].id", notNullValue());
    }

    @Given("el cliente tiene los detalles de una mascota a actualizadar")
    public void elClienteTieneLosDetallesDeUnaMascotaAActualizadar(String docString) {
        Serenity.setSessionVariable("pet").to(docString);
    }

    @When("el cliente realiza a {string} una peticion PUT con id de mascota actualizado")
    public void elClienteRealizaAUnaPeticionPUTConIdDeMascotaActualizado(String path) {
        String pet = Serenity.sessionVariableCalled("pet");
        given().contentType(ContentType.JSON)
                .body(pet)
                .put(URI_BASE.concat(path))
                .andReturn();
    }

    @And("el cuerpo de la respuesta no debe de estar vacio")
    public void elCuerpoDeLaRespuestaNoDebeDeEstarVacio() {
        SerenityRest.then().body("$", org.hamcrest.Matchers.not(Matchers.empty()));
    }

    @Given("el cliente tiene los datos de un nuevo  mascota")
    public void elClienteTieneLosDatosDeUnNuevoMascota(String docString) {
        Serenity.setSessionVariable("pet").to(docString);
    }

    @When("el cliente realiza una peticion POST a {string} con los detalles del nuevo de mascota")
    public void elClienteRealizaUnaPeticionPOSTAConLosDetallesDelNuevoDeMascota(String path) {
        String pet = Serenity.sessionVariableCalled("pet");
        given().contentType(ContentType.JSON)
                .body(pet)
                .post(URI_BASE.concat(path))
                .andReturn();
    }


    @And("el cuerpo de la respuesta debe contener los detalles del nuevo  mascota registrado")
    public void elCuerpoDeLaRespuestaDebeContenerLosDetallesDelNuevoMascotaRegistrado() {
        //TODO
    }

    @When("el cliente realiza una peticion DELETE a {string} con id de mascota eliminado {int}")
    public void elClienteRealizaUnaPeticionDELETEAConIdDeMascotaEliminado(String path, int id) {
        given().pathParam("id", id)
                .delete(URI_BASE.concat(path))
                .andReturn();
    }

}
