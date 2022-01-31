package com.example.demo_batch.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Customer {

    private String nr_documento;
    private String nome;
    private Date nascimento;
}
