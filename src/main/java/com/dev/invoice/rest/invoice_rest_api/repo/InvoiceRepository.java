package com.dev.invoice.rest.invoice_rest_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.invoice.rest.invoice_rest_api.entity.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
