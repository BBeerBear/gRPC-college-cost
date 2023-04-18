# Analyzing Cost of Undergrad College in US
[DataSet](https://www.kaggle.com/datasets/kfoster150/avg-cost-of-undergrad-college-by-state/versions/10?resource=download)
## Tech
Spring Boot + gRPC + REST API + MongoDB + DTO + RabbitMQ + Oracle Cloud

- Using Springboot as framework.
- Using gRPC and DTO for the microservices communication.
- Using REST API for the clients' HTTP requests.
- Using RabbitMQ server for routing the publishing messages(datasets from relative collections) to specific consumer using Topic Exchange.
- Deploying services on Oracle Cloud.

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
- Cannot use services on other ports expect for 8080: 
  - Open instance's firewall ports, refer to: 
    https://docs.fedoraproject.org/en-US/quick-docs/firewalld/#opening-ports-firewalld-fedora
   
    and

    https://www.thegeekdiary.com/how-to-open-a-ports-in-centos-rhel-7/
    ```bash
    sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent
    sudo firewall-cmd --reload
    ```
    Check if it opened:
    ```bash
    sudo firewall-cmd --list-ports
    ```
