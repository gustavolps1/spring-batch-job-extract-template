package com.example.demo_batch.config;


import com.example.demo_batch.model.Customer;
import com.example.demo_batch.reader.CustomerItemReader;
import com.example.demo_batch.writer.CustomerItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig{

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CustomerItemWriter customerItemWriter;

    @Autowired
    private CustomerItemReader customerItemReader;

    @Bean
    public Step createExportCustomerStep() {
        return stepBuilderFactory.get("createExportCustomerStep").<Customer, Customer>chunk(1)
                .reader(customerItemReader.getReader())
                .writer(customerItemWriter.getWriter())
                .build();
    }

    @Bean
    public Job createExportCustomerJob() {
        return jobBuilderFactory.get("createExportCustomerJob")
                .incrementer(new RunIdIncrementer())
                .flow(createExportCustomerStep())
                .end()
                .build();
    }
}

