package steps;

import io.cucumber.java.DocStringType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
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

public class StepDefsPetTypes {

    private final String HOSTNAME = System.getenv().getOrDefault("CONTAINER_IP", "localhost");
    private final String URI_BASE = "https://petstore3.swagger.io" ;

    @Given("el cliente configura la URI base")
    public void elClienteConfiguraLaURIBase() {
        rest().baseUri(URI_BASE);
    }

    @When("el cliente realiza una peticion GET a {string}")
    public void el_cliente_realiza_una_peticion_get_a(String path) {
        when().get(URI_BASE.concat(path)).andReturn();
    }

    @Then("el servidor debe de responder con un status {int}")
    public void el_servidor_debe_de_responder_con_un_status(Integer statusCode) {
        then().statusCode(statusCode);
    }

    @Then("el cuerpo de la respuesta debe de ser una lista de tipos de mascotas")
    public void el_cuerpo_de_la_respuesta_debe_de_ser_una_lista_de_tipos_de_mascotas() {
        then().body("$", not(empty()));
//        then().body("$", hasSize(6));
//        then().body("size()", is(6));
//        then().body("size()", equalTo(6));
        then().body("[0].id", notNullValue());
        then().body("[0].name", notNullValue());
    }

    @And("el cuerpo de la respuesta contiene la propiedad id con el valor {int}")
    public void elCuerpoDeLaRespuestaContieneLaPropiedadIdConElValor(Integer value) {
        then().body("id", is(value));
    }

    @And("el cuerpo de la respuesta contiene la propiedad name con el valor {string}")
    public void elCuerpoDeLaRespuestaContieneLaPropiedadNameConElValor(String value) {
        then().body("name", is(value));
    }

    @Given("el cliente tiene los detalles de un nuevo tipo de mascota")
    public void el_cliente_tiene_los_detalles_de_un_nuevo_tipo_de_mascota() {
        Map<String, Object> petType = new HashMap<>();
        petType.put("name", "Pez");
        Serenity.setSessionVariable("petType").to(petType);
    }
    @When("el cliente realiza una peticion POST a {string} con los detalles del nuevo tipo de mascota")
    public void el_cliente_realiza_una_peticion_post_a_con_los_detalles_del_nuevo_tipo_de_mascota(String path) {
        String petType = Serenity.sessionVariableCalled("petType");
        given().contentType(ContentType.JSON)
                .body(petType)
                .post(URI_BASE.concat(path))
                .andReturn();
    }
    @Then("el cuerpo de la respuesta debe contener los detalles del nuevo tipo de mascota registrado")
    public void el_cuerpo_de_la_respuesta_debe_contener_los_detalles_del_nuevo_tipo_de_mascota_registrado() throws JsonProcessingException {
        String docString = Serenity.sessionVariableCalled("petType");
        Map<String, Object> jsonMap = new ObjectMapper().readValue(docString, new TypeReference<>() {
        });

        then().body(notNullValue());
        then().body("id", notNullValue());
        then().body("id", instanceOf(Number.class));
        then().body("name", notNullValue());
        then().body("name", is(jsonMap.get("name")));

    }

    @Given("el cliente tiene los datos de un nuevo tipo de mascota")
    public void el_cliente_tiene_los_datos_de_un_nuevo_tipo_de_mascota(String docString) {
        Serenity.setSessionVariable("petType").to(docString);
    }

    @Given("el cliente tiene los detalles de un tipo de mascota actualizado")
    public void el_cliente_tiene_los_detalles_de_un_tipo_de_mascota_actualizado(String docString) {
        Serenity.setSessionVariable("petType").to(docString);
    }
    @When("el cliente realiza una peticion PUT a {string} con id tipo de mascota actualizado {int}")
    public void el_cliente_realiza_una_peticion_put_a_con_id_tipo_de_mascota_actualizado(String path, Integer id) {
        String petType = Serenity.sessionVariableCalled("petType");
        given().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(petType)
                .put(URI_BASE.concat(path))
                .andReturn();
    }

    @Then("el cuerpo de la respuesta debe de estar vacio")
    public void el_cuerpo_de_la_respuesta_debe_de_estar_vacio() {
        then().body(is(emptyString()));
    }

    @When("el cliente realiza una peticion DELETE a {string} con id tipo de mascota eliminado {int}")
    public void el_cliente_realiza_una_peticion_delete_a_con_id_tipo_de_mascota_eliminado(String path, Integer id) {
        given().pathParam("id", id)
                .delete(URI_BASE.concat(path))
                .andReturn();
    }
}
