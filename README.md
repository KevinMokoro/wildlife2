# WILDLIFE ANIMALS TRACKER

## Project Author:
KEVIN MOKORO

## Project Description
It's An Application that allows rangers to sight animals, record their location and condition if endangered.

## Technologies Used
* Java
* POSTGRES
* Spark
* Gradle
* JUnit

### SetUp link:
Deployed to: [Heroku](enigmatic-ravine-48176.herokuapp.com/)
Also you can,
* Clone repository
* Locally in PSQL setup the database as follows;

* CREATE DATABASE wildlife_tracker;
* Connect to the table : \c wildlife_tracker;
* Create the following tables in wildlife_tracker
* CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);
* CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar);
* CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;


* In your terminal type "gradle run" inside project directory
* Open a tab in your computer and launch 'http://localhost:4567'


### Usage
To monitor Wildlife.

### BDD
* Get user input depending on choice of form.
* Use submitted data to record details of various wild animals for better organization of these animals situations.
* Enlist the data in well layed easy follow up way.

#### Support and Contact Details
My Email [Kevin](klosvoke1@gmail.com).

#### Contributing
Reach out for any addition to the project.

### Copyright and License
Copyright <2018>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.