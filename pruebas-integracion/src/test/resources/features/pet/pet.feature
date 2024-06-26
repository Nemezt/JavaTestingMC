Feature: Gestionar mascotas

  @obtenerMascotaErronea
  Scenario: Listar una mascota por id incorrecto
    Given el cliente configura la URI base
    When el cliente realiza una peticion GET a "/pet/100"
    Then el servidor debe de responder con un status 404

  @actualizarMascota
  Scenario: Actualizar una mascota en el sistema
    Given el cliente tiene los detalles de una mascota a actualizadar
    """
      {
        "id": 1,
        "name": "snow",
        "category": {
          "id": 1,
          "name": "Dogs"
        },
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
    """
    When el cliente realiza a "/pet/" una peticion PUT con id de mascota actualizado
    Then el servidor debe de responder con un status 200
    And el cuerpo de la respuesta no debe de estar vacio

  @crearMascota
  Scenario: Registrar una nueva mascota en el sistema
    Given el cliente tiene los datos de un nuevo  mascota
        """
      {
        "id": 11,
        "name": "Shadow",
        "category": {
          "id": 2,
          "name": "Cat"
        },
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
    """
    When el cliente realiza una peticion POST a "/pet/" con los detalles del nuevo de mascota
    Then el servidor debe de responder con un status 200

  @obtenerMascota
  Scenario Outline: Listar una mascota por id
    Given el cliente configura la URI base
    When el cliente realiza una peticion GET a <uri>
    Then el servidor debe de responder con un status <statusCode>
    And el cuerpo de la respuesta contiene la propiedad id con el valor <value>
    And el cuerpo de la respuesta contiene la propiedad name con el valor <value1>
    Examples:
      | uri      | statusCode | value | value1     |
      | "/pet/1" | 200        | 1     | "snow" |
      | "/pet/11" | 200        | 11    | "Shadow"   |

  @obtenerMascotasEstado
  Scenario: Listar mascotas por estado
    Given el cliente configura la URI base
    When el cliente realiza una peticion GET a "/pet/findByStatus?status=available"
    Then el servidor debe de responder con un status 200
    And el cuerpo de la respuesta debe de ser una lista de mascota con el estado

  @eliminarMascota
  Scenario: Eliminar una  mascota en el sistema
    Given el cliente configura la URI base
    When el cliente realiza una peticion DELETE a "/pet/{id}" con id de mascota eliminado 1
    Then el servidor debe de responder con un status 200
