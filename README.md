# Query Avg Cost of Undergrad College by State in 4 Ways
[DataSet](https://www.kaggle.com/datasets/kfoster150/avg-cost-of-undergrad-college-by-state/versions/10?resource=download)
## Tech
Spring Boot + gRPC + REST API + MongoDB + DTO

- Using Springboot as framework
- Integrate gRPC with Spring Boot using `grpc-spring-boot-starter`
- Using REST API For the client side while gRPC to communicate with the server side

## Funtion
1. Query the cost given specific year, state, type, length, expense; and save the query as a document in a collection named EduCostStatQueryOne.
1. Query the top 5 most expensive states (with overall expense) given a year, type, length; and save the query as a document in a collection named EduCostStatQueryTwo.
1. Query the top 5 most economic states (with overall expense) given a year, type, length; and save the query as a document in a collection named EduCostStatQueryThree.
1. Query the top 5 states of the highest growth rate of overall expense given a range of past years, one year, three years and five years (using the latest year as the base) , type and length; and save the query as a document in a collection named EduCostStatQueryFour
1. Aggregate region‘s average overall expense for a given year, type and length; and save the query as a document in a collection named EduCostStatQueryFive.

## Difficulties
1. MongoDB Projection and Aggregation using Spring Data MongoDB

## Encounted Bugs and Errors
- `grpc-spring-boot-starter` not supporting Spring Boot 3
