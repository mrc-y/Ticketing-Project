# Ticketing-Project

This project is a spring boot based RESTful web application which creates a ticketing platform for the airlines. 
It uses hibernate+H2 in memory DB and embedded Tomcat server. Therefore, it does not need any DB or server configuration.
A simple "mvn spring-boot:run" command will be enough to run.

APIs that are available in this project:

**Airline API**: POST, GET and Search Airlines

**Airport API**: POST, GET and Search Airports

**Route API**: POST, GET and Search Routes

**Flight API**: POST, GET and Search Flights

**Ticket API**: Post Ticket Purchase, Get and Search Tickets, Cancellation of ticket with PATCH request

_TODO: Unit-tests should be added._

You can use following link to see API documentation and example requests.  
**Postman Link** [Link](https://documenter.getpostman.com/view/2839193/T17Nc5YT)