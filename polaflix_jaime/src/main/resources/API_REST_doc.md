---
Polaflix API REST - Jaime Palacios Mediavilla
--- 

A continuación se definarán los diferentes recursos de la API REST de Polaflix. Teniendo controladores para los usuarios y las series.

- [**Usuarios**](#usuarios)
    - [GET /users/{id}](#get-usersid)
    - [PUT /users/{id}/pending-series/{serieId}](#put-usersidpending-seriesserieid)
    - [PUT /users/{id}/views/serie/{idSerie}/season/{numSeason}/chapter/{numChapter}](#put-usersidviewsserieidserieseasonnumseasonchapternumchapter)
    - [GET /users/{id}/last-chapter-viewed/{idSerie}](#get-usersidlast-chapter-viewedidserie)
    - [GET /users/{id}/views](#get-usersidviews)
    - [GET /users/{id}/bills](#get-usersidbills)
- [**Series**](#series)
    - [GET /series](#get-series)
    - [GET /series/{id}](#get-seriesid)

## **Usuarios**

#### GET /users/{id}
Retorna un usuario de la base de datos.

Endpoint URL
```
http://localhost:8000/users/{id}
```

Parametros de la URL
| Campo | Tipo | Descripción    |
| ----- | ---- | -------------- |
| id    | Long | Id del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Estados de respuesta: 200 OK, 404 Not Found

Modelo de respuesta
```json
{
    "email": "paco23@polafix.com",
    "username": "Paco",
    "pendingSeries": [
        {
            "id": 1,
            "title": "Dark"
        }
    ],
    "startedSeries": [
        {
            "id": 2,
            "title": "Breaking Bad"
        }
    ],
    "finishedSeries": [
        {
            "id": 3,
            "title": "Game of Thrones"
        }
    ]
}
```

Campos de respuesta
| Campo          | Tipo   | Descripción                 |
| -------------- | ------ | --------------------------- |
| email          | String | Email del usuario           |
| username       | String | Nombre de usuario           |
| pendingSeries  | Array  | Array de series pendientes  |
| id             | Long   | Id de la serie              |
| title          | String | Titulo de la serie          |
| startedSeries  | Array  | Array de series en curso    |
| finishedSeries | Array  | Array de series finalizadas |

#### PUT /users/{id}/pending-series/{serieId}
Añade una serie a la lista de pendientes de un usuario.

Endpoint URL
```
http://localhost:8000/users/{id}/pending-series/{serieId}
```

Parametros de la URL
| Campo   | Tipo | Descripción    |
| ------- | ---- | -------------- |
| id      | Long | Id del usuario |
| serieId | Long | Id de la serie |

Parametros de la petición
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Estados de respuesta: 200 OK, 404 Not Found

Modelo de respuesta
```json
{
    "email": "paco23@polafix.com",
    "username": "Paco",
    "pendingSeries": [
        {
            "id": 1,
            "title": "Dark"
        },
        {
            "id": 2,
            "title": "Breaking Bad"
        }
    ],
    "startedSeries": [
        {
            "id": 4,
            "title": "Better Call Saul"
        }
    ],
    "finishedSeries": [
        {
            "id": 3,
            "title": "Game of Thrones"
        }
    ]
}
```

Campos de respuesta
| Campo          | Tipo   | Descripción                 |
| -------------- | ------ | --------------------------- |
| email          | String | Email del usuario           |
| username       | String | Nombre de usuario           |
| pendingSeries  | Array  | Array de series pendientes  |
| title          | String | Titulo de la serie          |
| startedSeries  | Array  | Array de series en curso    |
| finishedSeries | Array  | Array de series finalizadas |


#### PUT /users/{id}/views/serie/{idSerie}/season/{numSeason}/chapter/{numChapter}
Marca un capítulo como visto.

Endpoint URL
```
http://localhost:8000//users/{id}/views/serie/{idSerie}/season/{numSeason}/chapter/{numChapter}
```

Parametros de la URL
| Campo      | Tipo    | Descripción            |
| ---------- | ------- | ---------------------- |
| id         | Long    | Id del usuario         |
| idSerie    | Long    | Id de la serie         |
| numSeason  | Integer | Numero de la temporada |
| numChapter | Integer | Numero del capítulo    |

Parametros de la petición
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Estados de respuesta: 200 OK, 404 Not Found

Modelo de respuesta
```json
{
    "serieSeasonViews": [
        {
            "chapters": [
                true,
                true
            ]
        }
    ]
}
```

Campos de respuesta
| Campo            | Tipo    | Descripción         |
| ---------------- | ------- | ------------------- |
| serieSeasonViews | Array   | Array de temporadas |
| chapters         | Array   | Array de capítulos  |
| true             | Boolean | Capítulo visto      |
| false            | Boolean | Capítulo no visto   |


#### GET /users/{id}/last-chapter-viewed/{idSerie}
Retorna el último capítulo visto de una serie.

Endpoint URL
```
http://localhost:8000/users/{id}/last-chapter-viewed/{idSerie}
```

Parametros de la URL
| Campo   | Tipo | Descripción    |
| ------- | ---- | -------------- |
| id      | Long | Id del usuario |
| idSerie | Long | Id de la serie |

Parametros de la petición
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Estados de respuesta: 200 OK, 404 Not Found

Modelo de respuesta
```json
{
    "number": 2,
    "title": "Lies",
    "description": "Second chapter",
    "season": {
        "number": 1,
        "serie": {
            "title": "Dark"
        }
    }
}
```

Campos de respuesta
| Campo       | Tipo    | Descripción              |
| ----------- | ------- | ------------------------ |
| number      | Integer | Numero del capítulo      |
| title       | String  | Titulo del capítulo      |
| description | String  | Descripción del capítulo |
| season      | Object  | Objeto de la temporada   |
| number      | Integer | Numero de la temporada   |
| serie       | Object  | Objeto de la serie       |
| title       | String  | Titulo de la serie       |


#### GET /users/{id}/views
Retorna las vistas de un usuario.

Endpoint URL
```
http://localhost:8000/users/{id}/views
```

Parametros de la URL
| Campo | Tipo | Descripción    |
| ----- | ---- | -------------- |
| id    | Long | Id del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Estados de respuesta: 200 OK, 404 Not Found

Modelo de respuesta
```json
{
    "serieSeasonViews": [
        {
            "chapters": [
                false,
                true
            ]
        }
    ]
}
```

Campos de respuesta
| Campo            | Tipo  | Descripción                                                                |
| ---------------- | ----- | -------------------------------------------------------------------------- |
| serieSeasonViews | Array | Array de vistas de capítulos por temporada                                 |
| chapters         | Array | Array de booleanos ordenados que indican si el capítulo ha sido visto o no |


#### GET /users/{id}/bills
Retorna las facturas de un usuario.

Endpoint URL
```
http://localhost:8000/users/{id}/bills
```

Parametros de la URL
| Campo | Tipo | Descripción    |
| ----- | ---- | -------------- |
| id    | Long | Id del usuario |

Parametros de la petición
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Estados de respuesta: 200 OK, 404 Not Found

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
| Campo       | Tipo   | Descripción                      |
| ----------- | ------ | -------------------------------- |
| totalAmount | Double | Importe total de la factura      |
| monthBilled | String | Mes de la factura                |
| monthViews  | Array  | Array de visualizaciones del mes |
| chapter     | Object | Capitulo visto                   |
| dateView    | String | Fecha de la visualización        |
| price       | Double | Precio de la visualización       |

## **Series**

#### GET /series
Retorna todas las series de la base de datos.

Endpoint URL
```
http://localhost:8000/series
```

Parametror de la URL
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Parametros de la petición
| Campo              | Tipo   | Descripción           |
| ------------------ | ------ | --------------------- |
| initial (opcional) | String | Inicial de las series |

Estados de respuesta: 200 OK

Modelo de respuesta
```json
[
    {
        "title": "Dark",
        "description": "Time travel",
        "category": {
            "name": "Gold"
        },
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
        ],
        "creators": [
            {
                "name": "Baran",
                "surname": "Bo Odar"
            }
        ],
        "actors": [
            {
                "name": "Louis",
                "surname": "Hofmann"
            },
            {
                "name": "Lisa",
                "surname": "Vicari"
            }
        ]
    },
    {
        "title": "Breaking Bad",
        "description": "Drug dealer",
        "category": {
            "name": "Gold"
        },
        "seasons": [
            {
                "number": 1,
                "chapters": [
                    {
                        "number": 2,
                        "title": "Cat's in the bag"
                    },
                    {
                        "number": 1,
                        "title": "Pilot"
                    }
                ]
            }
        ],
        "creators": [
            {
                "name": "Vince",
                "surname": "Gilligan"
            }
        ],
        "actors": [
            {
                "name": "Bryan",
                "surname": "Cranston"
            },
            {
                "name": "Aaron",
                "surname": "Paul"
            }
        ]
    }
]
```

Campos de respuesta
| Campo       | Tipo    | Descripción             |
| ----------- | ------- | ----------------------- |
| title       | String  | Titulo de la serie      |
| description | String  | Descripción de la serie |
| category    | Object  | Objeto de la categoría  |
| name        | String  | Nombre de la categoría  |
| seasons     | Array   | Array de temporadas     |
| number      | Integer | Numero de la temporada  |
| chapters    | Array   | Array de capítulos      |
| number      | Integer | Numero del capítulo     |
| title       | String  | Titulo del capítulo     |
| creators    | Array   | Array de creadores      |
| name        | String  | Nombre del creador      |
| surname     | String  | Apellido del creador    |
| actors      | Array   | Array de actores        |
| name        | String  | Nombre del actor        |
| surname     | String  | Apellido del actor      |


#### GET /series/{id}
Retorna una serie de la base de datos.

Endpoint URL
```
http://localhost:8000/series/{id}
```

Parametros de la URL
| Campo | Tipo | Descripción    |
| ----- | ---- | -------------- |
| id    | Long | Id de la serie |

Parametros de la petición
| Campo | Tipo | Descripción |
| ----- | ---- | ----------- |
| -     | -    | -           |

Estados de respuesta: 200 OK, 404 Not Found

Modelo de respuesta
```json
{
    "title": "Dark",
    "description": "Time travel",
    "category": {
        "name": "Gold"
    },
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
    ],
    "creators": [
        {
            "name": "Baran",
            "surname": "Bo Odar"
        }
    ],
    "actors": [
        {
            "name": "Louis",
            "surname": "Hofmann"
        },
        {
            "name": "Lisa",
            "surname": "Vicari"
        }
    ]
}
```

Campos de respuesta
| Campo       | Tipo    | Descripción             |
| ----------- | ------- | ----------------------- |
| title       | String  | Titulo de la serie      |
| description | String  | Descripción de la serie |
| category    | Object  | Objeto de la categoría  |
| name        | String  | Nombre de la categoría  |
| seasons     | Array   | Array de temporadas     |
| number      | Integer | Numero de la temporada  |
| chapters    | Array   | Array de capítulos      |
| number      | Integer | Numero del capítulo     |
| title       | String  | Titulo del capítulo     |
| creators    | Array   | Array de creadores      |
| name        | String  | Nombre del creador      |
| surname     | String  | Apellido del creador    |
| actors      | Array   | Array de actores        |
| name        | String  | Nombre del actor        |
| surname     | String  | Apellido del actor      |