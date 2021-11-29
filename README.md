# Asset Manager project

#### Prerequisites
install Node.js: https://nodejs.org/en/download/
Angular Framework: npm i @angular/cli
PostgreSQL: https://www.postgresql.org/download/
Scala sbt: https://www.scala-sbt.org/download.html

The repository includes two projects and a database: 
personapp is a Frontend application that is run with -ng serve. The app is run on port 4200 by default. Open application in a browser developer mode.
personlist is a Backend application that is run with -sbt run. The app is run on port 9000 by default.
The database is on port 5432 by default.

Database creation: In PostgreSQL tool pgAdmin create a database with any name you want.

In tables section create a table and name it tpersons. It should have 4 columns: id (type: serial), vname (type: character varying, length: 50), 
ndob (type: numeric, length: 4), vhobby (type: character varying, length: 50).

In tables section create another table and name it tassets. It should have 4 columns: id (type: serial), npersonid (type: numeric, length: 10),
vname (type: character varying, length: 50), namount (type: numeric, length: 20)

In personlist/conf/application.conf file in lines 368-371 set up your database parameters.
