******Account Opening API For Existing Customer******
______________________________________________________

This project contains APIs as per below requirements:

First Endpoint:
• The API will expose an endpoint which accepts the user information (customerID,
initialCredit)

• Once the endpoint is called, a new account will be opened connected to the user whose
ID is customerID.

• Also, if initialCredit is not 0, a transaction will be sent to the new account.

Second Endpoint:
• This Endpoint will output the user information showing Name, Surname, balance,
and transactions of the accounts.

___________________________________________________________________

**Tech Stack:**
1. Application is developed using Spring boot
2. Single very basic UI page to render user inputs is using thymeleaf html template in spring boot.
3. Backend is using In memory H2 database.
4. For enabling CI/CD, used Docker configuration

___________________________________________________________________

**Backend APIs exposed:**

POST /accounts/create
This creates a new account for existing customer whose id and some initial credit value are given in request body


GET /accounts/customer/{customerId}
This fetches the customer details for given customer id. Details include his customerId, First and Last Name and Accounts linked to that customer and all the transactions in those accounts.


___________________________________________________________________

** Instructions to Build and Run the project:**

There are 2 ways to run this project

1. Using Maven
Prerequisites: 
JRE 17 or above should be installed on machine
Apache maven 3 

Download the source code using below steps:
1.1 https://github.com/Kanikav6/account -> Download ZIP -> Extract ZIP

OR

1.1 On Command Prompt: git clone https://github.com/Kanikav6/account.git

1.2 cd account
1.3 mvn clean package
1.4 cd target
1.5 java -jar account-0.0.1-SNAPSHOT.jar



2. Using Docker
Prerequisites:
Docker installed and running

You can check the docker is instaled by running docker -v

2.1 docker run -p 8080:8080 -d vaishali/accountapp:v1
___________________________________________________________________


Run the   project by running localhost:8080/create  on your browser
This will render user inputs customerID and initialCreate to create account for the customer


You can enter h2 console by running   localhost:8080/h2-console in your browser   
JDBC Url: jdbc:h2:mem:accountdb
Username: vaagarwal     
password: password           

and run insert query on customer table to create one sample user
insert into customer values (100, 'TestFN', 'TestLN');          






