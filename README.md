# Library Management System 

## Idea
TBU


## Develop Environment
- JDK8
- Maven 3.3.9
- Mysql 5.7


## Code Structure

source tree
```
.
├── LMSApplication.java
├── controllers
│   ├── BookController.java
│   ├── RecordController.java
│   └── UserController.java
├── entity
│   ├── Book.java
│   ├── Record.java
│   └── User.java
├── repository
│   ├── BookRepository.java
│   ├── RecordRepository.java
│   └── UserRepository.java
├── service
│   ├── BookService.java
│   ├── BookServiceImpl.java
│   ├── RecordService.java
│   ├── RecordServiceImpl.java
│   ├── UserService.java
│   └── UserServiceImpl.java
└── utils
    └── StringUtils.java
```


## Limitations & Future Work
TBU


## Usages
You can start to try by running 
- `mvn spring-boot:run` to start the spring boot application
- change application.properties to your account/password
- use schema.sql to create the database and tables
- use postman to try below restful api and need to choose `application/json` for request

```
POST
localhost:8080/api/addBook
{
	"id": 1,
	"isbn": "abcdefg",
	"bookName": "good",
	"author": "master",
	"publishDate": "20201024",
	"summary": "cool",
	"available": 1
}

POST
localhost:8080/api/addUser
{
	"id": 12345,
	"userName": "test",
	"count": 5
}

POST
localhost:8080/api/borrow/123456/1

POST
localhost:8080/api/return/123456/1
``` 



