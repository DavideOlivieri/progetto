# Project Ticketmaster
## Java project made with Spring
## Summary:
- [Introduction](#id-section1)
- [API Ticketmaster](#id-section2)
- [Application usage](#id-section3)
- [Routes](#id-section4)
- [Exception](#id-section5)
- [JUnit Test](#id-section6)
- [Authors](#id-section7)

<div id='id-section1'/>

## Introduction
***
This project is a Spring application that allows you to filter the data of Ticketmaster site, and make statistics of the data.\
Ticketmaster is a well-known software dedicated to the management and booking of sports, musical, theatrical, film and artistic events, available at https://www.ticketmaster.it/. \
Through the APIs derived from the TM Developer page, accessible through the Discovery API address, we get the metadata.\
The program allows you to view the statistics of events located in the USA and Canada. \
They are available through our application:

- Filtering events for segment and view events grouped by States and genres.
- Filtering events for genre and view events grouped by States.
- Filtering events for Country code and view events grouped by genre and States.
- Statistics relating to the United States and Canada, so a sort of comparison between the two states.
- Statistics relating to 1/2 chosen State/s and 1/2 genre/s.
- Statistics for a chosen state, such as the total of events, the month with the most and fewest events, the monthly average of events, and the grouping of events by genre.

<div id='id-section2'/>

## API Ticketmaster
***
To get access to the Ticketmaster API, we made an account on the TM Developer website \
( https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/#search-events-v2 ) \
and then we were given the route to be used together with a personal apikey.
> apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC

#### Problems with API
When we make a call to the API, the response we are given is made up of a maximum of 60 events. \
So on some routes such as those for searching by country, the respons contains a maximum of 60 events and so the statistics are not very accurate. \
But for example, for the search by state the statistics are almost perfect because the events in many states are less than 60. \
In addition, the API if no data such as a Country code and a genre are entered, it will default to United States and Basketball.

<div id='id-section3'/>

## Application usage
***
After the application has been started, routes can be called from any browser or from any application such as postman. \
Example of usecase
`localhost:8080/<route_name>`

In this case 8080 is the standard port but can be changed.

<div id='id-section4'/>

## Routes
***
### All the routes
| Routes  | Desciption | Type |
| :--- | :---: | ---:  |
| `/getEvents` | Displays the data provided to us as a response in JSON format | Get |
| `/getEventsForSegment` | Displays all events of that kind of segment and some statistics | Get |
| `/getEventsForGenre` | Displays all events of that kind of genre and some statistics | Get |
| `/getEventsForCountryCode` | Displays the events that will take place in the chosen state and some statistics | Get |
| `/compareUSCA` | Compare the statistics between US and CA | Get |
| `/getStats` | Displays the statistics chosen a state and a genre | Get |
| `/getStatsforState` | Displays the statistics for a specific State | Get |

### Route /getEvents
***
This route allows you to view the metadata, that is a description of all the attributes and related data types. This output is shown in JSON format.
#### Example
If for example in postman we insert this route : `localhost:8080/getEvents`

Response:
```json
{
    "city": "city name",
    "min_events": "month with the fewest events",
    "avg_events": "average of events of all months",
    "tot_events": "total of events",
    "max_events": "month with the most events",
    "local_date": "date(year/month/day)",
    "country_code": "country code",
    "event_id": "event id",
    "local_time": "time",
    "subgenre": "subgenre",
    "segment": "grouping of genres ",
    "country_name": "country name",
    "genre": "genre",
    "event_name": "event name",
    "state": "state name",
    "state_code": "state code"
}
```
### Route /getEventsForSegment
***
This route allows you to see the events filtered by segment. Segment groups specific genres. \
In addition, the events grouped by States and by genre will also be shown.

In this application we can search for these segments:

> Sports \
> Music \
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

>Sports | Baseball, Basketball, Boxing, Hockey, Football. \
>Music | Classical, Hip-Hop/Rap, Blues, Dance/Electronic, Other. \
>Art & Theatre | Comedy. \
>Miscellaneous | Hobby/Special Interest Expos, Ice Shows, Fairs & Festivals.

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
This route displays all events filtered by State code. \
For the United States and Canada, events grouped by state and genre are displayed. \
Here are some examples of Country codes to choose:

| Country | countryCode |
| :--- | :---: | 
| United State | US |
| Canada | CA |
| Poland | PL |
| Australia | AU |

Optional parameter to be entered:

> countryCode = param, if you want to search for another Country code (default value = CA). \
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
A comparison will also be made between the genres of events that will take place in the two states. \
This route displays the total of events, the month/s with the maximun and minimum events that will take place in US and Canada. \
The comparison can be made by choosing the genre in fact the optional parameter to be entered is:

> genre = param, (default value = Rock) 

#### Example
Example of a route for /compareUSCA \
In this example we will use the following parameters:

| KEY | VALUE |
| :--- | :---: | 
| genre | Hockey |

The entered route will then be of this type:
`localhost:8080/compareUSCA?genre=Hockey`

```json
{
    "US": [
        {
            "The month with the most events for Hockey in US is": "January",
            "The total of events in US for Hockey is": 60,
            "The month with the fewest events for Hockey in US is": "April, May, June, July, August, September, October, November, December",
            "Events grouped by state": [
                {
                    "Events number in Michigan ": 6,
                    "Events number in Washington ": 6,
                    "Events number in Nevada ": 3,
                    "Events number in Colorado ": 3,
                    "Events number in Massachusetts ": 12,
                    "Events number in Minnesota ": 3,
                    "Events number in Ohio ": 3,
                    "Events number in Tennessee ": 3,
                    "Events number in Missouri ": 3,
                    "Events number in Pennsylvania ": 9,
                    "Events number in North Carolina ": 3,
                    "Events number in Illinois ": 3,
                    "Events number in California ": 3
                }
            ],
            "The average monthly events for Hockey in the US": 5.0
        }
    ],
    "CA": [
        {
            "The month with the most events for Hockey in Canada is": "March",
            "The month with the fewest events for Hockey in Canada is": "April, May, June, July, August, September, October, November, December",
            "The average monthly events for Hockey in Canada": 3.0,
            "Events grouped by state": [
                {
                    "Events number in Ontario ": 9,
                    "Events number in British Columbia ": 9,
                    "Events number in Manitoba ": 3,
                    "Events number in Alberta ": 9,
                    "Events number in Quebec ": 6
                }
            ],
            "The total of events in Canada for Hockey is": 36
        }
    ]
}
```
### Route /getStats
***
In this route you can enter 1 or 2 state code/s and 1 or 2 genre/s. \
The response will contain statistics for 1/2 state/s and 1/2 genre/s.

Optional parameter to be entered:
> state_code = param, if you want to search for another State code (defaul value = CA ) \
> state_code2 = param, if you want to search for two States (defaul value = null )\
> genre = param, if you want to search for another genre (defaul value = Hockey )\
> genre2 = param, if you want to search for two genres (defaul value = null )\
> seeEvents = yes/no, yes if you want to see the events, no if not (default value = no).

You can choose from many State codes, these are some of the well known in the United States and Canada:

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
Example of a route for /getStats \
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

Example of a route for /getStatsforState 

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

There are 4 types of exceptions that check the entered parameters, and they are: 

1) [countryParamException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/countryParamException.java)
2) [segmentParamException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/segmentParamException.java)
3) [genreParamException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/genreParamException.java)
4) [stateParamException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/stateParamException.java)

With these exceptions, the parameters written for countrycode,segment, genre and State are checked and if they are not present in our list an error message is sent with some indications to resolve the error.

#### Example:

For example, if in the route /getEventsForGenre as parameter for genre is inserted "Basket" instead of "Basketball" the program will return this type of error.


>Error: The genre searched for does not exist or is not present in our databases. \
>The first letter must be uppercase for every genre. \
>These are some examples of the most popular genres: \
>Baseball, Basketball, Boxing, Hockey; Classical, Hip-Hop/Rap, Blues, Comedy, Hobby/Special Interest Expos.

[InvalidInputException](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/main/java/it/univpm/exam_project/exception/InvalidInputException.java)
This exception checks the parameter entered for seeEvents and if it is not valid an error message such as the following is sent:

>Error: Input invalid for seeEvents \
>The allowed inputs are: \
>not to see the events: no, No, NO, false. \
>to see the events: si, Si, SI, yes, true.

<div id='id-section6'/>

## JUnit Test
****

3 JUnit tests developed in JUnit 5 have been implemented:
1) [dateConverterTest](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/test/java/it/univpm/exam_project/dateConverterTest.java):Test to verify the correct operation of the dateConverter method. \
-dateConverterTest(): Method to test the convertMonth() method, relative to the control of the inserted string, provided a string representing a date, and an object of the LocalDate class representing the same date, checks that the result of the conversion is equal to the LocalDate object.

2) [monthConverterTest](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/test/java/it/univpm/exam_project/monthConverterTest.java):Test to verify the correct functioning of the monthConverter method. \
-monthConverterTest(): Method to test the convertMonth () method, which allows you to get a string month given the respective number.
3) [InvalidInputExceptionTest](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/test/java/it/univpm/exam_project/InvalidInputExceptionTest.java):Test to verify the correct operation of the exception related to the control of the input in seeEvents parameter. 
4) [genreControllerExceptionTest](https://github.com/DavideOlivieri/progetto/blob/main/exam_project/src/test/java/it/univpm/exam_project/genreControllerExceptionTest.java):Test to verify the correct operation of the exception related to the control of the input in genre parameter. 


<div id='id-section7'/>


## Authors
****

This project was developed during the Object Oriented Programming course (2021/2022) by:

-[Davide Olivieri](https://github.com/DavideOlivieri) \
-[Jacopo Coloccioni](https://github.com/JacopoColoccioni)

### Work division:
Initially, both of us having minimal basics we decided to work together, so we helped each other while we wrote the code so we could learn together and help each other. 

Later we figured out how to work and we started to divide the tasks in order to proceed faster.

We made the controller, model, parser and some method of EventServiceImpl together. \
Jacopo Coloccioni made the exceptions, tests, some methods of EventServiceImpl and has implemented most of the routes. \
Davide Olivieri made the filters, some methods of EventServiceImpl, Javadoc and Readme.

For the testing part, which we did at the end, we worked together.


Developers: \
*Davide Olivieri* \
*Jacopo Coloccioni*

