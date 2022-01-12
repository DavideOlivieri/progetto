# Project Ticketmaster
## Java project made with Spring
## Summary:
- [Introduction](#id-section1)
- [API Ticketmaster](#id-section2)
- [Application usage](#id-section3)
- [Routes](#id-section4)
- [Authors](#id-section5)

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

                
<div id='id-section2'/>

## API Ticketmaster
***
To get access to the Ticketmaster API, we made an account on the TM Developer website ( https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/#search-events- v2 ) and then we were given the route to be used together with a personal apikey.
> apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC


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

The answer to this request will be:
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

> Sports
> Music
> Arts & Theatre


### Route /getEventForGenre
***
This route allows to view metatadata by genre of the events.
In thi application we can serch for these segment:

>Sports | Baseball, Basketball, Boxing, Hockey \
>Music | Classical  \
>Art & Theatre | Comedy 
   

#### Example
```json
{
 "country_code": "US", 
 "event_id": "Z7r9jZ1AdFf84",
 "local_time": "19:00:00",
 "city": "Nevada",
 "subgenre": "NHL",
 "segment": "Sports",
 "country_name": "United States Of America",
 "genre": "Hockey"
 "event_name": "Vegas Golden Knights vs. New York Rangers",
 "state": "NV",
 "state_code": "Las Vegas",
 "local_date": "2022-01-06"
}
```
### Route /getEventForCountryCode
***
This route allows to view metadata by CountryCode of the events.
Here are some examples of country codes to choose:
| Country | countryCode |
| :--- | :---: | 
| United State | US|
| Canada | CA |
| France | FR |
| Italy | IT |
| Germany | DE |

#### Example
Example of a route for /getEventForCountryCode with Cananda
>localhost:8080/getEventForCountryCode?countryCode=CA

Response:
```json
       {
            "country_code": "CA",
            "event_id": "G5vZZpzHpp3z6",
            "local_time": "18:00:00",
            "city": "Ontario",
            "subgenre": "NBA",
            "segment": "Sports",
            "country_name": "Canada",
            "genre": "Basketball",
            "event_name": "Toronto Raptors vs. New Orleans Pelicans",
            "state": "ON",
            "state_code": "Toronto",
            "local_date": "2022-01-09"
        },
        "..."
```

### Route /compareUSCA
***
In this route, a comparison is made between the number of events that will take place in the United States and those in Canada.\
A comparison will also be made between the genres of events that will take place in the two states.
This route displays the total, maximum and minimum of events that will take place in US and Canada.

### Route /getStats
***
In this route you can enter 1 or 2 state code and 1 or 2 genres. \
Parameters to be entered: 
> state_code = param \
> state_code2 = param \
> genre = paramn \
> genre2 = param \
> seeEvents = if value is "Yes" shows all the events and not only the stats.

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

#### Example
This is an example with the default parameters (genre = Hockey, state_code = CA) \
Response:
```json
{
    "The total of events of Hockey in CA is": 6,
    "The month with the fewest events of Hockey in CA is": "February",
    "The month with the most events of Hockey in CA is": "January",
    "The average monthly events of Hockey in CA is": 0.5
}
```

### Route /getStatsforState
***
This route shows some statistics for the chosen State like, the total of events, the average monthly events, the month with the most events and the month with the fewest events.
It also allows to see the number of events for a specific genre. \
Parameter to be entered:

> state_code

#### Example
This is an example with the default parameter (state_code = CA )

```json
{
    "The month with the most events in CA is": "January",
    "The month with the fewest events in CA is": "May",
    "The total of events in CA is": 60,
    "The average monthly events in CA is": 5.0,
    "Events grouped by genre": [
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {
            "Events number of Basketball ": 30
        },
        {},
        {
            "Events number of Hockey ": 6
        },
        {},
        {
            "Events number of Rock ": 6
        },
        {},
        {}
    ]
}
```

<div id='id-section5'/>

## Authors
This project was developed during the Object Oriented Programming course (2021/2022) by:

-[Davide Olivieri](https://github.com/DavideOlivieri) \
-[Jacopo Coloccioni](https://github.com/JacopoColoccioni)
