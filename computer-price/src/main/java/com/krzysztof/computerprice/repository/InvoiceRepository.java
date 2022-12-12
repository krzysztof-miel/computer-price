package com.krzysztof.computerprice.repository;

import com.krzysztof.computerprice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
