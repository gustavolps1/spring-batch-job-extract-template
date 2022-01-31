package com.example.demo_batch.reader;
import javax.sql.DataSource;
import com.example.demo_batch.config.DataSourceConfig;
import com.example.demo_batch.mapper.CustomerRowMapper;
import com.example.demo_batch.model.Customer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.stereotype.Component;

@Component
public class CustomerItemReader {

    public JdbcCursorItemReader<Customer> getReader(){
        JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<Customer>();
        reader.setDataSource(getDataSource());
        reader.setSql("SELECT nr_documento, nome, nascimento FROM monitoring.customer");
        reader.setRowMapper(new CustomerRowMapper());
        return reader;
    }

    private DataSource getDataSource(){
        return new DataSourceConfig().getDataSource();
    }
}


