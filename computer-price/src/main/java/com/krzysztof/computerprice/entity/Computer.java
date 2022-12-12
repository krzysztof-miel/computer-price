package com.krzysztof.computerprice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "computers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    private LocalDate postingDate;
    @Min(value = 0)
    private double price_USD;
    private double price_PLN;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private Invoice invoice;

    public Computer(String name, double price_USD) {
        this.name = name;
        this.price_USD = price_USD;
    }

    public Computer(String name, double price_USD, Invoice invoice) {
        this.name = name;
        this.price_USD = price_USD;
        this.invoice = invoice;
    }

    public Computer(String name, LocalDate postingDate, double price_USD, Invoice invoice) {
        this.name = name;
        this.postingDate = postingDate;
        this.price_USD = price_USD;
        this.invoice = invoice;
    }

    public Computer(String name, LocalDate postingDate, double price_USD) {
        this.name = name;
        this.postingDate = postingDate;
        this.price_USD = price_USD;
    }
}
