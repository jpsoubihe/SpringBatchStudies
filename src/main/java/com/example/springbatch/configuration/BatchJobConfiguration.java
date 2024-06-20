package com.example.springbatch.configuration;

import com.example.springbatch.BasicTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
//@EnableBatchProcessing(dataSourceRef = "springDataSource", transactionManagerRef = "batchTransactionManager")
public class BatchJobConfiguration {

    @Bean
    public JdbcTransactionManager batchTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

    @Bean
    public Step firstStep(JobRepository jobRepository, JdbcTransactionManager batchTransactionManager) {
        return new StepBuilder("firstStep", jobRepository)
                .tasklet(new BasicTasklet(), batchTransactionManager)
                .build();
    }

    @Bean
    public Job myJob(JobRepository jobRepository, Step firstStep) {
        return new JobBuilder("myFirstJob", jobRepository)
                .start(firstStep)
                .next(firstStep)
                .build();
    }
}
