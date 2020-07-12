# Customer Service
Customer Service with Spring Boot and Docker

Set up MySQL database
mysql -u root -p
(Enter password)
create database customerdb;
quit

Test with curl:

curl -X POST -d @testdata/register1.json http://localhost:8090/customerservice/customer/add --header "Content-Type:application/json"

curl -X GET http://localhost:8090/customerservice/customers --header "Content-Type:application/json"
