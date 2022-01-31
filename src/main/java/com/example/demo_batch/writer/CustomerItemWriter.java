package com.example.demo_batch.writer;

import com.example.demo_batch.model.Customer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class CustomerItemWriter {

    public FlatFileItemWriter<Customer> getWriter(){
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<Customer>();
        writer.setHeaderCallback(header -> header.write("nr_documento|nome|nascimento"));
        writer.setResource(new FileSystemResource(getExportFileSrcPath()));
        writer.setLineAggregator(getDelimitedLineAggregator());
        return writer;
    }

    private DelimitedLineAggregator<Customer> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<Customer> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Customer>();
        beanWrapperFieldExtractor.setNames(new String[]{"nr_documento", "nome", "nascimento"});
        DelimitedLineAggregator<Customer> delimitedLineAggregator = new DelimitedLineAggregator<Customer>();
        delimitedLineAggregator.setDelimiter("|");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;
    }

    private String getExportFileSrcPath(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-'T'HHmm");
        String TIMESTAMP = LocalDateTime.now().format(formatter);
        final String FILE_EXTENSION = ".dat";
        final String FILE_NAME = "EXPORT_CUSTOMER_";
        final String SRC_PATH = "G:/demo_batch_storage/";
        return SRC_PATH + FILE_NAME + TIMESTAMP + FILE_EXTENSION;
    }
}
