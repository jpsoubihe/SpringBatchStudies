# Spring Batch Studies

## First Job

This first job implemented was planned with the goal to setup project and understand a bit on how a job is configured 
and executed using Spring Batch module.

Spring Boot was used to start the application servlet. Also, we instantiated necessary Beans `Step` and `Job`.

For the Tasklet in specific, determining the task to be executed in the respective Step, we created a Spring Component implementing `Tasklet` interface.

In this new version of Spring Batch, a JobRepository and TransactionManager are required to populate object instances. 
For that we integrated a H2 repository, in-memory, to persist Job metadata.

This first job have only one step in which we log `BasicTasklet` as a proof of Job execution.