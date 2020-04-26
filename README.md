# Customer Management System

This project is a CRUD based application for managing customers and their orders.

Following are frontend & backend components 

* Angular + Bootstrap ( Frontend)
* Spring Boot Service (Backend API)
* MySql (Database)


# Running the Project Locally

## Running UI
1. Install the Angular CLI, below command will install angular globally.

    `npm install -g @angular/cli`

2. Run `npm install` at the root of the UI project folder. All dependent packages are available in packages.json.

3. Run `ng serve -o`, this will compile the application and will be ready for viewing in broswer.

4. Run localhost:4200 on browser.

## Running Service

1. Create MySql Database & Change the configuration in DatabaseConfig.java
2. Use `project_mysql_script.sql` file to create database object and sample records.
3. `build.gradle` file contains package dependencies including mqsql java connector. 
3. Open the project in Eclipse or Spring Tool Suite and run the application as spring boot app.




