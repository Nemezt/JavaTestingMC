# language: en
@gestionarPropietarios
Feature: Gestionar propietarios

  @listarTodosPropietarios
  Scenario: Listar todos propietarios
    Given el cliente abre el navegador
    When el cliente navega al menú encontrar propietarios
    And  el cliente selecciona la opción buscar propietario
    Then la página debe mostrar una lista de propietarios válida

  @buscarPropietarioApellido
  Scenario: Buscar propietario
    Given el cliente tiene un propietario para buscar "Black"
    And el cliente abre el navegador
    And el cliente navega al menú encontrar propietarios
    When el cliente ingresa el apellido
    And el cliente selecciona la opción buscar propietario
    Then la página debe mostrar la información del propietario

  @registrarPropietario
  Scenario Outline: Registrar un propietario
    Given el cliente tiene los siguientes datos del propietario:
      | firstName   | lastName   | address   | city   | telephone   |
      | <firstName> | <lastName> | <address> | <city> | <telephone> |
    And el cliente abre el navegador
    And el cliente navega al menú encontrar propietarios
    And el cliente selecciona la opción agregar nuevo
    And el cliente ingresa los datos del propietario
    And el cliente guarda el propietario
    Then la página debe mostrar la información del propietario registrado
    Examples:
      | firstName | lastName | address      | city | telephone |
      | Andrés      | Martínez      | San José | San José   | 123456789 |
      | Marcos      | Sanchez       | San José | San José   | 123456789 |
      | Monica      | Bolaños       | San José | San José   | 123456789 |
