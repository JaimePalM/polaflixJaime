# Polaflix API REST

A continaución se definarán los diferentes recursos de la API REST de Polaflix. Teniendo controladores para los usuarios y las series.

- [Polaflix API REST](#polaflix-api-rest)
    - [Usuarios](#usuarios)
      - [GET /users](#get-users)
      - [GET /users/{id}](#get-usersid)
      - [POST /users](#post-users)
      - [PUT /users/{id}](#put-usersid)
      - [GET /users/{id}/pending-series](#get-usersidpending-series)
      - [POST /users/{id}/pending-series](#post-usersidpending-series)
      - [GET /users/{id}/bills](#get-usersidbills)
    - [Series](#series)
      - [GET /series](#get-series)
      - [POST /series](#post-series)

### Usuarios

#### GET /users
Retorna todos los usuarios de la base de datos.

Endpoint URL
```
http://localhost:8080/users
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Modelo de respuesta
```json
[
    {
        "email": "paco23@polafix.com",
        "username": "Paco",
        "pendingSeries": [
            {
                "title": "Dark"
            }
        ],
        "startedSeries": [],
        "finishedSeries": []
    },
    {
        "email": "lola43@polaflix.com",
        "username": "Lola",
        "pendingSeries": [],
        "startedSeries": [],
        "finishedSeries": []
    }
]
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| pendingSeries | Array | Array de series pendientes |
| startedSeries | Array | Array de series en curso |
| finishedSeries | Array | Array de series finalizadas |

#### GET /users/{id}
Retorna un usuario de la base de datos.

Endpoint URL
```
http://localhost:8080/users/{id}
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Modelo de respuesta
```json
{
    "email": "paco23@polafix.com",
    "username": "Paco",
    "pendingSeries": [
        {
            "title": "Dark"
        }
    ],
    "startedSeries": [],
    "finishedSeries": []
}
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| pendingSeries | Array | Array de series pendientes |
| startedSeries | Array | Array de series en curso |
| finishedSeries | Array | Array de series finalizadas |

#### POST /users
Crea un usuario en la base de datos.

Endpoint URL
```
http://localhost:8080/users
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| password | String | Contraseña del usuario |
| IBAN | String | IBAN del usuario |
| fixedFee | Boolean | Cuota fija del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Modelo de respuesta
```json
{
    "email": "manolo@polaflix.com",
    "username": "Manolo",
    "pendingSeries": [],
    "startedSeries": [],
    "finishedSeries": []
}
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| pendingSeries | Array | Array de series pendientes |
| startedSeries | Array | Array de series en curso |
| finishedSeries | Array | Array de series finalizadas |

#### PUT /users/{id}
Actualiza un usuario en la base de datos.

Endpoint URL
```
http://localhost:8080/users/{id}
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id del usuario |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| password | String | Contraseña del usuario |
| IBAN | String | IBAN del usuario |
| fixedFee | Boolean | Cuota fija del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Modelo de respuesta
```json
{
    "email": "manolo@polaflix.com",
    "username": "Manolo",
    "pendingSeries": [],
    "startedSeries": [],
    "finishedSeries": []
}
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| pendingSeries | Array | Array de series pendientes |
| startedSeries | Array | Array de series en curso |
| finishedSeries | Array | Array de series finalizadas |

#### GET /users/{id}/pending-series
Retorna las series pendientes de un usuario.

Endpoint URL
```
http://localhost:8080/users/{id}/pending-series
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Modelo de respuesta
```json
[
    {
        "title": "Dark"
    },
    {
        "title": "Breaking Bad"
    }
]
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| title | String | Titulo de la serie |


#### POST /users/{id}/pending-series
Añade una serie a la lista de pendientes de un usuario.

Endpoint URL
```
http://localhost:8080/users/{id}/pending-series
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| serieId | Long | Id de la serie |

Modelo de respuesta
```json
{
    "email": "paco23@polafix.com",
    "username": "Paco",
    "pendingSeries": [
        {
            "title": "Dark"
        },
        {
            "title": "Breaking Bad"
        }
    ],
    "startedSeries": [],
    "finishedSeries": []
}
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| pendingSeries | Array | Array de series pendientes |
| title | String | Titulo de la serie |
| startedSeries | Array | Array de series en curso |
| finishedSeries | Array | Array de series finalizadas |

#### GET /users/{id}/bills
Retorna las facturas de un usuario.

Endpoint URL
```
http://localhost:8080/users/{id}/bills
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| month (opcional) | String | Mes de la factura |

Modelo de respuesta
```json
{
    "totalAmount": 1.5,
    "monthBilled": "2023-04-01",
    "monthViews": [
        {
            "chapter": {
                "number": 2,
                "season": {
                    "number": 1,
                    "serie": {
                        "title": "Dark"
                    }
                }
            },
            "dateView": "2023-04-22T17:11:40.999+00:00",
            "price": 1.5
        }
    ]
}
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| totalAmount | Double | Importe total de la factura |
| monthBilled | String | Mes de la factura |
| monthViews | Array | Array de visualizaciones del mes |
| chapter | Object | Capitulo visto |
| dateView | String | Fecha de la visualización |
| price | Double | Precio de la visualización |

### Series

#### GET /series
Retorna todas las series de la base de datos.

Endpoint URL
```
http://localhost:8080/series
```

Parametror de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |


Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| initial (opcional) | String | Filtra por la inicial de la serie |

Modelo de respuesta
```json
{
    "title": "Dark",
    "seasons": [
        {
            "number": 1,
            "chapters": [
                {
                    "number": 2,
                    "title": "Lies"
                },
                {
                    "number": 1,
                    "title": "Secrets"
                }
            ]
        }
    ]
}
```	

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| title | String | Titulo de la serie |
| seasons | Array | Array de temporadas de la serie |
| number | Integer | Numero de la temporada |
| chapters | Array | Array de capitulos de la temporada |
| number | Integer | Numero del capitulo |
| title | String | Titulo del capitulo |

#### GET /series/{id}
Retorna una serie de la base de datos.

Endpoint URL
```
http://localhost:8080/series/{id}
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id de la serie |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Modelo de respuesta
```json
{
    "title": "Dark",
    "seasons": [
        {
            "number": 1,
            "chapters": [
                {
                    "number": 2,
                    "title": "Lies"
                },
                {
                    "number": 1,
                    "title": "Secrets"
                }
            ]
        }
    ]
}
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| title | String | Titulo de la serie |
| seasons | Array | Array de temporadas de la serie |
| number | Integer | Numero de la temporada |
| chapters | Array | Array de capitulos de la temporada |
| number | Integer | Numero del capitulo |
| title | String | Titulo del capitulo |

#### POST /series
Crea una serie en la base de datos.

Endpoint URL
```
http://localhost:8080/series
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| title | String | Titulo de la serie |
| description | String | Descripción de la serie |
| category | String | Categoria de la serie |
| creatorName | String | Nombre del creador de la serie |
| creatorSurname | String | Apellido del creador de la serie |

Modelo de respuesta
```json
{
    "title": "Lost",
    "description": "Plane crashes on a dessert island",
    "seasons": [],
    "creators": [
        {
            "name": "Jeffrey Jacob",
            "surname": "Abrams"
        }
    ],
    "actors": []
}
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| title | String | Titulo de la serie |
| description | String | Descripción de la serie |
| seasons | Array | Array de temporadas de la serie |
| creators | Array | Array de creadores de la serie |
| name | String | Nombre del creador |
| surname | String | Apellido del creador |
| actors | Array | Array de actores de la serie |
