package com.dev.invoice.rest.invoice_rest_api.service;

import java.util.List;

import com.dev.invoice.rest.invoice_rest_api.entity.Invoice;

public interface IInvoiceService {

    Long saveInvoice(Invoice inv);
    Invoice updateInvoice(Invoice e);
    void deleteInvoice(Long id);	
    Invoice getOneInvoice(Long id);
    List<Invoice> getAllInvoices();
   // boolean isInvoiceExist(Long id);


}
