# language: en
Feature: Gestionar tipos de mascotas

    @listarTiposMascotas
    Scenario: Listar todos los tipos de mascotas
      Given el cliente configura la URI base
      When el cliente realiza una peticion GET a "/api/pettypes"
      Then el servidor debe de responder con un status 200
      And el cuerpo de la respuesta debe de ser una lista de tipos de mascotas

    @obtenerMascota
    Scenario Outline: Listar un tipo de mascota por id
      Given el cliente configura la URI base
      When el cliente realiza una peticion GET a <uri>
      Then el servidor debe de responder con un status <statusCode>
      And el cuerpo de la respuesta contiene la propiedad id con el valor <value>
      And el cuerpo de la respuesta contiene la propiedad name con el valor <value1>
      Examples:
        | uri               | statusCode | value | value1  |
        | "/api/pettypes/4" | 200        | 4     | "snake" |
        | "/api/pettypes/5" | 200        | 5     | "bird"  |

    @crearTipoMascota
    Scenario: Registrar un nuevo tipo de mascota en el sistema
#      Given el cliente tiene los detalles de un nuevo tipo de mascota
      Given el cliente tiene los datos de un nuevo tipo de mascota
       """
         {
             "name": "rabbit"
         }
       """
      When el cliente realiza una peticion POST a "/api/pettypes" con los detalles del nuevo tipo de mascota
      Then el servidor debe de responder con un status 201
      And el cuerpo de la respuesta debe contener los detalles del nuevo tipo de mascota registrado

  @actualizarTipoMascota
  Scenario: Actualizar un tipo de mascota en el sistema
    Given el cliente tiene los detalles de un tipo de mascota actualizado
       """
         {
           "name": "dog 2"
         }
       """
    When el cliente realiza una peticion PUT a "/api/pettypes/{id}" con id tipo de mascota actualizado 4
    Then el servidor debe de responder con un status 204
    And el cuerpo de la respuesta debe de estar vacio

    @eliminarTipoMascota
    Scenario: Eliminar un tipo de mascota en el sistema
      Given el cliente configura la URI base
      When el cliente realiza una peticion DELETE a "/api/pettypes/{id}" con id tipo de mascota eliminado 4
      Then el servidor debe de responder con un status 204
      And el cuerpo de la respuesta debe de estar vacio