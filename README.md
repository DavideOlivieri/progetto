# Project Ticketmaster
## Java project made with Spring
## Summary:
- [Introduction](#id-section1)
- [API Ticketmaster](#id-section2)
- [Application usage](#id-section3)
- [Routes](#id-section4)
- [Exception](#id-section5)
- [Authors](#id-section6)

<div id='id-section1'/>

## Introduction
***
This project is a Sping application that allows you to filter the data of Ticketmaster site, and make statistics of the data.\
Ticketmaster is a well-known software dedicated to the management and booking of musical, theatrical, film and artistic events, available at https://www.ticketmaster.it/. \
Through the APIs derived from the TM Developer page, accessible through the Discovery API address, we get the metadata.\
The program will have to evaluate only the events located in USA and Canada. \
They are available through our application:
- filtering of events, foreseen in USA and / or Canada, through the selection of one or two States, followed by the production of statistics relating to the State or States chosen.
- filtering of events, foreseen in USA and / or Canada, through the selection of one or two genres, followed by the production of statistics relating to the chosen genre or genres.
- 
- statistics relating to the state chosen, such as total events, the months with the most and the fewest events, monthly average of events, and events grouped by genre.

<div id='id-section2'/>

## API Ticketmaster
***
To get access to the Ticketmaster API, we made an account on the TM Developer website ( https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/#search-events-v2 ) and then we were given the route to be used together with a personal apikey.
> apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC

#### Problems with API
When we make a call to the API, the response we are given is made up of a maximum of 60 events. \
So on some routes such as those for searching by country, the respons contains a maximum of 60 events and so the statistics are not very accurate. \
But for example, for the search by state the statistics are almost perfect because the events in many states are less than 60. 


<div id='id-section3'/>

## Application usage
***
After the application has been started, routes can be called from any browser or from an application such as postman.\
Example of usecase
`localhost:8080/<route_name>`

In this case 8080 is the standard port but can be changed.

<div id='id-section4'/>

## Routes
***
### All the routes
| Routes  | Desciption | Type |
| :--- | :---: | ---:  |
| `/getEvents` | Displays the resulting data in JSON format | Get |
| `/getEventsForSegment` | Displays all events of that kind of segment | Get |
| `/getEventsForGenre` | Displays all events of that kind of genre | Get |
| `/getEventsForCountryCode` | Displays the events that will take place in the chosen state | Get |
| `/compareUSCA` | Compare the total / maximum / minimum of events between US and CA | Get |
| `/getStats` | Displays the statistics chosen a state and a genre | Get |
| `/getStatsforState` | Displays the events for genre for a specific State | Get |

### Route /getEvents
***
This route allows you to view the metadata, that is a description of all the attributes and related data types. This output is shown in JSON format.
#### Example
If for example in postman we insert this route : `localhost:8080/getEvents`

Response:
```json
{
    "country_code": " : country code",
    "event_id": " : event id",
    "local_time": " : time(year/month/day)",
    "city": " : city name",
    "subgenre": " : subgenre",
    "segment": " : segment",
    "country_name": " : country name",
    "genre": " : genre",
    "event_name": " : event name",
    "state": " : state name",
    "local_date": " : date"
}
```
### Route /getEventsForSegment
***
This route allows to view the metadata by segment. Segment groups specific genres. 

In this application we can search for these segments:

> Sports \
> Music \
> Arts & Theatre \
> Miscellaneous

Optional parameter to be entered:
> segment = param, if you want to search for another segment (default value = Sports). \
> seeEvents = yes/no, yes if you want to see the events, no if not (default value = no).

#### Example

In this example we will use the following parameters:
| KEY | VALUE |
| :--- | :---: | 
| segment | Music |
| seeEvents | yes |

The entered route will then be of this type: \
`localhost:8080/getEventsForSegment?segment=Music&seeEvents=yes`

Response:
```json
{
    "Events": [
        {
            "country_code": "US",
            "event_id": "vvG10ZpVj3ykZr",
            "local_time": "19:00:00",
            "city": "Inglewood",
            "subgenre": "Alternative Rock",
            "segment": "Music",
            "country_name": "United States Of America",
            "genre": "Rock",
            "event_name": "iHeartRadio ALTer EGO Presented by Capital One",
            "state": "California",
            "state_code": "CA",
            "local_date": "2022-01-15"
        },
        "..."
 ],
    "Events grouped by state": [
        {
            "Events number in Washington ": 3,
            "Events number in Pennsylvania ": 6,
            "Events number in North Carolina ": 9,
            "Events number in Florida ": 12,
            "Events number in California ": 6,
            "Events number in South Carolina ": 3,
            "Events number in Arizona ": 3,
            "Events number in Minnesota ": 3,
            "Events number in Texas ": 6,
            "Events number in Georgia ": 6,
            "Events number in Ohio ": 3
        }
    ],
    "Events grouped by genre": [
        {
            "Events number of Rock ": 60
        }
    ]
}        
```
### Route /getEventsForGenre
***
This route displays all events filtered by genre. \
For the United States and Canada, events grouped by state are displayed. \
These are among the best known genres:

>Sports | Baseball, Basketball, Boxing, Hockey \
>Music | Classical, Hip-Hop/Rap, Blues, Dance/Electronic, Other \
>Art & Theatre | Comedy \
>Miscellaneous | Hobby/Special Interest Expos

Optional parameter to be entered:
> genre = param, if you want to search for another genre (default value = Basketball). \
> seeEvents = yes/no, yes if you want to see the events, no if not (default value = no).

#### Example

In this example we will use the following parameters:
| KEY | VALUE |
| :--- | :---: | 
| genre | Hip-Hop/Rap |
| seeEvents | yes |

The entered route will then be of this type:

`localhost:8080/getEventsForGenre?genre=Hip-Hop/Rap&seeEvents=yes`

Response:
```json
{
    "Events": [
        {
            "country_code": "US",
            "event_id": "vvG10ZpIuvds4O",
            "local_time": "20:00:00",
            "city": "Inglewood",
            "subgenre": "French Rap",
            "segment": "Music",
            "country_name": "United States Of America",
            "genre": "Hip-Hop/Rap",
            "event_name": "Bad Bunny - El Ultimo Tour Del Mundo",
            "state": "California",
            "state_code": "CA",
            "local_date": "2022-02-25"
        },
        "..."
    ],
    "Events grouped by state": [
        {
            "Events number in Tennessee ": 3,
            "Events number in Nevada ": 3,
            "Events number in Florida ": 3,
            "Events number in District of Columbia ": 3,
            "Events number in Colorado ": 3,
            "Events number in Illinois ": 3,
            "Events number in California ": 12,
            "Events number in New Jersey ": 3,
            "Events number in Arizona ": 3,
            "Events number in Texas ": 12
        }
    ]
}        
        
```
### Route /getEventsForCountryCode
***
This route displays all events filtered by Country code. \
For the United States and Canada, events grouped by state and genre are displayed. \
Here are some examples of country codes to choose:
| Country | countryCode |
| :--- | :---: | 
| United State | US |
| Canada | CA |
| Poland | PL |

Optional parameter to be entered:

> countryCode = param, if you want to search for another Country (default value = CA). \
> seeEvents = yes/no, yes if you want to see the events, no if not (default value = no)

#### Example
Example of a route for /getEventForCountryCode 

In this example we will use the following parameters:

| KEY | VALUE |
| :--- | :---: | 
| seeEvents | yes |

The entered route will then be of this type:

`localhost:8080/getEventsForCountryCode?seeEvents=yes`

Response:
```json
{
    "Events": [
        {
            "country_code": "CA",
            "event_id": "G5vZZpzHppwzG",
            "local_time": "18:00:00",
            "city": "Toronto",
            "subgenre": "NBA",
            "segment": "Sports",
            "country_name": "Canada",
            "genre": "Basketball",
            "event_name": "Toronto Raptors vs. Portland Trail Blazers",
            "state": "Ontario",
            "state_code": "ON",
            "local_date": "2022-01-23"
        },
        "..."
         ],
    "Events grouped by state": [
        {
            "Events number in Ontario ": 27,
            "Events number in British Columbia ": 12,
            "Events number in Manitoba ": 3,
            "Events number in Alberta ": 9,
            "Events number in Quebec ": 9
        }
    ],
    "Events grouped by genre": [
        {
            "Events number of Basketball ": 18,
            "Events number of Hockey ": 36,
            "Events number of Rock ": 6
        }
    ]
}
```

### Route /compareUSCA
***
In this route, a comparison is made between the number of events that will take place in the United States and those in Canada.\
A comparison will also be made between the genres of events that will take place in the two states.
This route displays the total, maximum and minimum of events that will take place in US and Canada.

### Route /getStats
***
In this route you can enter 1 or 2 state code and 1 or 2 genres. \
Optional parameter to be entered:
> state_code = param \
> state_code2 = param \
> genre = paramn \
> genre2 = param \
> seeEvents = yes/no, yes if you want to see the events, no if not (default value = no).

You can choose from many states, these are some of the well known in the United States and Canada:

| United States | Canada |
| :--- | :---: | 
| AL - Alabama | AB - Alberta|
| AK - Alaska | BC - Colombie-Britannique|
| AZ - Arizona | NB - Nouveau-Brunswick |
| CA -  California | NL - Terre-Neuve-et-Labrador |
| CO - Colorado | NS - Nouvelle-Écosse|
| IN - Indiana | ON - Ontario |
| LA - Louisiana | PE - Île-du-Prince-Édouard |
| NY - New York | QC - Québec |
| TX - Texas | SK - 	Saskatchewan |
| UT - Utah | NT - Territoires du Nord-Ouest | 

On this site you can find all states for the United States ( https://en.wikipedia.org/wiki/ISO_3166-2:US ) \
On this site you can find all states for Canada ( https://en.wikipedia.org/wiki/ISO_3166-2:CA ) \

#### Example

In this example we will use the following parameters:
| KEY | VALUE |
| :--- | :---: | 
| state_code | AZ |
| genre | Hockey |
| genre2 | Basketball |

The entered route will then be of this type:

`localhost:8080/getStats?state_code=AZ&genre=Hockey&genre2=Basketball`

Response:
```json
{
    "The month with the fewest events of Hockey in AZ is": "February, March, April, May, June, July, August, September, October, November, December",
    "The average monthly events of Basketball in AZ is": 2.5,
    "The total of events of Basketball in AZ is": 30,
    "The total of events of Hockey in AZ is": 3,
    "The average monthly events of Hockey in AZ is": 0.25,
    "The month with the most events of Basketball in AZ is": "February",
    "The month with the most events of Hockey in AZ is": "January",
    "The month with the fewest events of Basketball in AZ is": "April, May, June, July, August, September, October, November, December"
}
```

### Route /getStatsforState
***
This route shows some statistics for the chosen State like, the total of events, the average monthly events, the month with the most events and the month with the fewest events.
It also allows to see the number of events for a specific genre. \
Optional parameter to be entered:

> state_code = param, if you want to search for another state (defalt value = CA(California)) \
> seeEvents = yes/no, yes if you want to see the events, no if not (default value = no)

#### Example

Example of a route for /getStatsforState \

In this example we will not enter any parameters.

The entered route will then be of this type:

`localhost:8080/getStatsforState`

```json
{
    "The month with the most events in CA is": "January",
    "The month with the fewest events in CA is": "May, June, July, August, September, October, November, December",
    "The total of events in CA is": 60,
    "The average monthly events in CA is": 5.0,
    "Events grouped by genre": [
        {
            "Events number of Basketball ": 21,
            "Events number of Hockey ": 9,
            "Events number of Performance Art ": 3,
            "Events number of Motorsports/Racing ": 6,
            "Events number of Fairs & Festivals ": 6,
            "Events number of Rock ": 6,
            "Events number of Football ": 9
        }
    ]
}
```
<div id='id-section5'/>

## Exception
***

There are 3 types of exceptions that check the entered parameters, and they are: 

1) [countryParamException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/countryParamException.java)
2) [segmentParamException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/segmentParamException.java)
3) [genreParamException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/genreParamException.java)

With these exceptions, the parameters written for the countrycode, the segment and the genre are checked and if they are not present in our list an error message is sent with some indications to resolve the error.

#### Example:

For example, if in the route /getEventsForGenre as parameter for genre is inserted "Basket" instead of "Basketball" the program will return this type of error.


>Error: The genre searched for does not exist or is not present in our databases. \
>The first letter must be uppercase for every genre. \
>These are some examples of the most popular genres: \
>Baseball, Basketball, Boxing, Hockey; Classical, Hip-Hop/Rap, Blues, Comedy, Hobby/Special Interest Expos.

<div id='id-section6'/>


## Authors
This project was developed during the Object Oriented Programming course (2021/2022) by:

-[Davide Olivieri](https://github.com/DavideOlivieri) \
-[Jacopo Coloccioni](https://github.com/JacopoColoccioni)
