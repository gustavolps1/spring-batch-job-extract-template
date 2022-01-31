package com.example.demo_batch.mapper;

import com.example.demo_batch.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setNr_documento(rs.getString("nr_documento"));
        customer.setNome(rs.getString("nome"));
        customer.setNascimento(rs.getDate("nascimento"));
        return customer;
    }
}
