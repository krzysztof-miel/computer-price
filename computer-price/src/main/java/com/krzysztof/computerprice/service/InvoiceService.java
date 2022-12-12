package com.krzysztof.computerprice.service;

import com.krzysztof.computerprice.entity.Invoice;
import com.krzysztof.computerprice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private ComputerService computerService;

    public Invoice createInvoice() {
        return invoiceRepository.save(new Invoice());
    }

    public Optional<Invoice> findById(int id) {
        return invoiceRepository.findById(id);
    }

    /*
    Rzowinięcie aplikacji:
    -dodanie możliwości obsługi faktury, dodawania i uduwanie z niej komputera
     */



}
