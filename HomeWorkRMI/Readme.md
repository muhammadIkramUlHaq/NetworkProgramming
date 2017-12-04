   Network Programming 

   Home Work 3 

  RMI and Database 


### Software Needed ###
 <b> InteliJ IDE <b>
 
 <b> JAVA JDK <b>
 
 <b> MySql  <b>
 
 

### Steps to Setup the Project ###

1)-  This Project is created using Intellij but could be used in any editor or IDE.

2)- Clone this Project in your local system and create the project using existing source.

3)- Select the Directory HomeWorkRMI once chooese source directory for creating or importing project.

4)- Project will be created and ready to use.

5)- Include the mysql driver which is already inside the src/ folder.

### Create Database and Tables ###

1)- Create Database "file_catalog_rmi" in MySql.

2)- Create Two Tables inside it "user" and "file".

3)- Create Table file using "create table File (fileName varchar(255), fileOwner varchar(255), filePrivacy varchar(255))".

4)- Create Table user using "create table User (userName varchar(255), userPassword varchar(255))".



### How to Run the Project

1)- In order to start the server, Go to server package and navigate to startup directory. Run StartServer.java to start server instance.

2)- In order to start the Client, Go to client package and navigate to startup directory, Run StartClient.java to start the client instance.

3)- Many instances of Client could be run at one time.


