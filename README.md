# GateWait - Backend GCM  Server w/ API Caller + Scheduler / Wait Time Simulator
[![Build Status](https://travis-ci.org/cmboult/GateWait.svg?branch=master)](https://travis-ci.org/cmboult/GateWait)

This is the backend for my 3rd Year Individual Project at the University of Nottingham. This package includes a smack based XMPP Google Cloud Messaging server used to communicate with Android devices, a FlightAware API caller/scheduler to retrieve flight data and insert in the database every 24 hours and wait time simulator which is still being worked on.

</a>
## Running the backend
To run the server and API scheduler with default settings:
``java -jar AppController.java``

## Build Prerequisites
### OracleJDK7
Built and tested on oracle JDK 7, do not expect this to work on any other JDK
### Maven
Maven 3.2 is used for building and testing

## Tools used
- GitHub
	- Source control
- TravisCI 
	- Build and test project
- Eclipse 
	- JUnit
		For unit testing
	- EclEmma
		- Test coverage
- JMetric
	- Software Metrics
