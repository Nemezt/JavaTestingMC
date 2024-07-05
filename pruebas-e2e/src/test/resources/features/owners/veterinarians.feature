# language: en
@consultarVeterinarios
Feature: Consultar veterinarios

  @listarTodosVeterinarios
  Scenario: Listar todos veterinarios
    Given el cliente abre el navegador web
    When el cliente navega al menú encontrar veterinarios
    Then la página debe mostrar una lista de veterinarios