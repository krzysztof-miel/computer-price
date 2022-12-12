package com.krzysztof.computerprice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    @JsonManagedReference
    private List<Computer> computers = new LinkedList<>();

    public void addComputerToList(Computer computer) {
        computer.setInvoice(this);
        computers.add(computer);
    }

}
