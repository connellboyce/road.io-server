# Road.io
IST440W Capstone Project

### Created by
- [Connell Boyce](https://github.com/connellboyce)
- [Kellia Cockerill](https://github.com/kelliacockerill)
- [Caden Martz](https://github.com/CadenRM)

### Table of Contents
1. [Installation and Startup](#1)
2. [Components](#2)
3. [Dependencies](#3)
3. [Features](#4)
4. [Documentation](#5)
5. [References](#6)

### **1**. Installation
Clone the repository.
```bash
git clone https://github.com/connellboyce/road.io
```

### 2. Application Startup
Navigate to the road.io project directory.
Then, proceed by starting the Spring Boot backend. Since it is depended on by the front end React program, this will need to be running before starting the front end.
```bash
mvn spring-boot:run
```
Once the back end is running on port 8787, change your directory to the /frontend folder within the road.io directory. Once you are in this folder, start the React front end.
```bash
npm start
```
It is important to note, the project will not run if the host does not have API keys for NREL or HERE. If these are posessed, they should be saved as environmnet variables on the host's machine under the names: HERE_ACCESS_KEY_ID and NREL_ACCESS_KEY_ID.

### **2**. Components
- Java 8
- Spring Boot 2.4.3
- Maven 3.6.3
- HERE API
- NREL API

### **3**. Dependencies
- Spring Framework
    - Starter Web
    - Starter Test
    
### **4**. Features

### **5**. Documentation
- [Frontend Developer Notes](documentation/FRONTEND.md)
- [Backend Developer Notes](documentation/BACKEND.md)
- [Middleware Developer Notes](documentation/MIDDLEWARE.md)
- [Deployment Developer Notes](documentation/DEPLOYMENT.md)
