package com.krzysztof.computerprice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlType(propOrder = {"name", "date", "price_USD", "price_PLN"})
public class ComputerDTO {
    private String name;
    private String date;
    private double price_USD;
    private double price_PLN;

    public ComputerDTO(Computer computer) {
        this.name = computer.getName();
        this.date = computer.getPostingDate().toString();
        this.price_USD = computer.getPrice_USD();
        this.price_PLN = computer.getPrice_PLN();
    }

    @XmlElement(name = "nazwa")

    public String getName() {
        return name;
    }

    @XmlElement(name = "data_ksiegowania")
    public String getDate() {
        return date;
    }

    @XmlElement(name = "koszt_USD")
    public double getPrice_USD() {
        return price_USD;
    }

    @XmlElement(name = "koszt_PLN")
    public double getPrice_PLN() {
        return price_PLN;
    }
}
