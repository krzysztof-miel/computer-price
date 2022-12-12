package com.krzysztof.computerprice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "faktura")
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDTO {

    private List<ComputerDTO> computersList = new LinkedList<>();

    public void addToList(ComputerDTO computer) {
        computersList.add(computer);
    }

    @XmlElement(name = "komputer")
    public List<ComputerDTO> getComputersList() {
        return computersList;
    }
}
