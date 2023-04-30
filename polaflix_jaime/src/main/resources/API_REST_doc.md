---
Polaflix API REST - Jaime Palacios Mediavilla
--- 

A continaución se definarán los diferentes recursos de la API REST de Polaflix. Teniendo controladores para los usuarios y las series.

- [**Usuarios**](#usuarios)
    - [GET /users](#get-users)
    - [GET /users/{id}](#get-usersid)
    - [POST /users](#post-users)
    - [GET /users/{id}/pending-series](#get-usersidpending-series)
    - [POST /users/{id}/pending-series](#post-usersidpending-series)
    - [GET /users/{id}/started-series](#get-usersidstarted-series)
    - [GET /users/{id}/finished-series](#get-usersidfinished-series)
    - [POST /users/{id}/mark-chapter-viewed/{idSerie}/{idSeason}/{idChapter}](#post-usersidmark-chapter-viewedidserieidseasonidchapter)
    - [GET /users/{id}/last-chapter-viewed/{idSerie}](#get-usersidlast-chapter-viewedidserie)
    - [GET /users/{id}/views](#get-usersidviews)
    - [GET /users/{id}/bills](#get-usersidbills)
- [**Series**](#series)
    - [GET /series](#get-series)
    - [GET /series/{id}](#get-seriesid)
    - [POST /series](#post-series)
    - [POST /series/{id}/change-category/{newCategory}](#post-seriesidchange-categorynewcategory)

## **Usuarios**

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
| email | String | Filtra por email del usuario |

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
| - | - | - |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| email | String | Email del usuario |
| username | String | Nombre de usuario |
| password | String | Contraseña del usuario |
| IBAN | String | IBAN del usuario |
| fixedFee | Boolean | Cuota fija del usuario |

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
        "id": 2,
        "title": "Dark"
    },
    {
        "id": 1,
        "title": "Breaking Bad"
    }
]
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id de la serie |
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


#### GET /users/{id}/started-series
Retorna las series en curso de un usuario.

Endpoint URL
```
http://localhost:8080/users/{id}/started-series
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
        "id": 1,
        "title": "Breaking Bad"
    }
]
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id de la serie |
| title | String | Titulo de la serie |


#### GET /users/{id}/finished-series
Retorna las series finalizadas de un usuario.

Endpoint URL
```
http://localhost:8080/users/{id}/finished-series
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
        "id": 2,
        "title": "Dark"
    }
]
```

Campos de respuesta
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id de la serie |
| title | String | Titulo de la serie |


#### POST /users/{id}/mark-chapter-viewed/{idSerie}/{idSeason}/{idChapter}
Marca un capítulo como visto.

Endpoint URL
```
http://localhost:8080/users/{id}/mark-chapter-viewed/{idSerie}/{numSeason}/{numChapter}
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id del usuario |
| idSerie | Long | Id de la serie |
| numSeason | Integer | Numero de la temporada |
| numChapter | Integer | Numero del capítulo |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

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
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| serieSeasonViews | Array | Array de temporadas |
| chapters | Array | Array de capítulos |
| true | Boolean | Capítulo visto |
| false | Boolean | Capítulo no visto |


#### GET /users/{id}/last-chapter-viewed/{idSerie}
Retorna el último capítulo visto de una serie.

Endpoint URL
```
http://localhost:8080/users/{id}/last-chapter-viewed/{idSerie}
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id del usuario |
| idSerie | Long | Id de la serie |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

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
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| number | Integer | Numero del capítulo |
| title | String | Titulo del capítulo |
| description | String | Descripción del capítulo |
| season | Object | Objeto de la temporada |
| number | Integer | Numero de la temporada |
| serie | Object | Objeto de la serie |
| title | String | Titulo de la serie |


#### GET /users/{id}/views
Retorna las vistas de un usuario.

Endpoint URL
```
http://localhost:8080/users/{id}/views
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
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| serieSeasonViews | Array | Array de vistas de capítulos por temporada |
| chapters | Array | Array de booleanos ordenados que indican si el capítulo ha sido visto o no |


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
| date (opcional) | String | Fecha del mes de la factura |

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

## **Series**

#### GET /series
Retorna todas las series de la base de datos.

Endpoint URL
```
http://localhost:8080/series
```

Parametror de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| initial (opcional) | String | Inicial de las series |

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
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| title | String | Titulo de la serie |
| description | String | Descripción de la serie |
| category | Object | Objeto de la categoría |
| name | String | Nombre de la categoría |
| seasons | Array | Array de temporadas |
| number | Integer | Numero de la temporada |
| chapters | Array | Array de capítulos |
| number | Integer | Numero del capítulo |
| title | String | Titulo del capítulo |
| creators | Array | Array de creadores |
| name | String | Nombre del creador |
| surname | String | Apellido del creador |
| actors | Array | Array de actores |
| name | String | Nombre del actor |
| surname | String | Apellido del actor |


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
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| title | String | Titulo de la serie |
| description | String | Descripción de la serie |
| category | Object | Objeto de la categoría |
| name | String | Nombre de la categoría |
| seasons | Array | Array de temporadas |
| number | Integer | Numero de la temporada |
| chapters | Array | Array de capítulos |
| number | Integer | Numero del capítulo |
| title | String | Titulo del capítulo |
| creators | Array | Array de creadores |
| name | String | Nombre del creador |
| surname | String | Apellido del creador |
| actors | Array | Array de actores |
| name | String | Nombre del actor |
| surname | String | Apellido del actor |


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
    "category": {
        "name": "Standard"
    },
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
| category | Object | Objeto de la categoría |
| name | String | Nombre de la categoría |
| seasons | Array | Array de temporadas de la serie |
| creators | Array | Array de creadores de la serie |
| name | String | Nombre del creador |
| surname | String | Apellido del creador |
| actors (optional) | Array | Array de actores de la serie |


#### POST /series/{id}/change-category/{newCategory}
Cambia la categoria de una serie.

Endpoint URL
```
http://localhost:8080/series/{id}/change-category/{newCategory}
```

Parametros de la URL
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| id | Long | Id de la serie |
| newCategory | String | Nueva categoria de la serie |

Parametros de la petición
| Campo | Tipo | Descripción |
| ------ | ------ | ------ |
| - | - | - |

Modelo de respuesta
```json
{
    "title": "Lost",
    "description": "Plane crashes on a dessert island",
    "category": {
        "name": "Silver"
    },
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
| category | Object | Objeto de la categoría |
| name | String | Nombre de la categoría |
| seasons | Array | Array de temporadas de la serie |
| creators | Array | Array de creadores de la serie |
| name | String | Nombre del creador |
| surname | String | Apellido del creador |
| actors | Array | Array de actores de la serie |