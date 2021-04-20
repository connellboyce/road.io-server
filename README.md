# Road.io Server
IST440W Capstone Project
- Operates in combination with [Road.io Client](https://github.com/connellboyce/road.io-client)

### Created by
- [Connell Boyce](https://github.com/connellboyce)
- [Kellia Cockerill](https://github.com/kelliacockerill)
- [Caden Martz](https://github.com/CadenRM)

### Table of Contents
1. [Components](#1-components)
2. [Dependencies](#2-dependencies)
3. [Plugins](#3-plugins)
4. [API Endpoints](#4-api-endpoints)
5. [Developer Documentation](#5-developer-documentation)
6. [External Resources](#6-external-resources)

### **1**. Components
- Java 8
- Spring Boot 2.4.3
- Maven 3.6.3

### **2**. Dependencies
- Spring Framework
    - Starter Web
    - Starter Test
    - WebFlux
- JUnit
    
### **3**. Plugins
- Maven
    - Build tool
- Maven Site
    - Create a site to display various pieces of project information and reports
- Maven Project Info Reports
    - Provides pages for Maven Site to display project info
- Maven Javadoc
    - Provides Javadoc to Maven Site
- JaCoCo
    - Provides report on testing coverage for the Maven Site
    
### **4**. API Endpoints
##### Get routing with stations 
```http request
GET /api/stations/along/{origin}/{outletType}/{destination}/{freeFlowSpeedTable}/{initialCharge}/{maxCharge}/{chargingCurve}/{maxChargeAfterChargingStation}
```
##### Get local stations
```http request
GET /api/stations/near/{latitude}/{longitude}/{radius}
```

##### Autocomplete
```http request
GET /api/autocomplete/{partial}/{country}
```

### **5**. Developer Documentation
- [Backend Developer Notes](documentation/BACKEND.md)
- [Deployment Developer Notes](documentation/DEPLOYMENT.md)

### **6**. External Resources
- [HERE API](https://developer.here.com/documentation/geocoding-search-api/api-reference-swagger.html)
- [NREL API](https://developer.nrel.gov/)
- Spring Boot tutorials courtesy of [Baeldung](https://www.baeldung.com/)
