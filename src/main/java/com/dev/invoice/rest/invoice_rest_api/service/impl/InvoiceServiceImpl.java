package com.dev.invoice.rest.invoice_rest_api.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.invoice.rest.invoice_rest_api.entity.Invoice;
import com.dev.invoice.rest.invoice_rest_api.exception.InvoiceNotFoundException;
import com.dev.invoice.rest.invoice_rest_api.repo.InvoiceRepository;
import com.dev.invoice.rest.invoice_rest_api.service.IInvoiceService;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private InvoiceRepository repo;

    @Override
    public Long saveInvoice(Invoice inv) {
        // TODO Auto-generated method stub
        Long id = repo.save(inv).getId();
		return id;    
    }

    @Override
    public Invoice updateInvoice(Invoice inv) {
        // TODO Auto-generated method stub
       //// System.out.println(pr);
       // Optional<Invoice> a = repo.findById(inv);
       // inv.put("id",pr);
       ///  System.out.println("1 "+a);
       //   System.out.println("2 "+a.getClass().getName());
       //   System.out.println("3 "+inv.getClass().getName());
        return repo.save(inv);

    }

    @Override
    public void deleteInvoice(Long id) {
        // TODO Auto-generated method stub
        Invoice inv= getOneInvoice(id);
		repo.delete(inv);    
    }

    public Optional<Invoice> getSingleInvoice(Long Id) {
		return repo.findById(Id);
    }

    @Override
    public Invoice getOneInvoice(Long id) {
        // TODO Auto-generated method stub
        Invoice inv = repo.findById(id)
				.orElseThrow(()->new InvoiceNotFoundException(
						new StringBuffer().append("Product  '")
						.append(id)
						.append("' not exist")
						.toString())
						);
		return inv;    
    }

    @Override
    public List<Invoice> getAllInvoices() {
        // TODO Auto-generated method stub
        List<Invoice> list = repo.findAll();
				list.sort((ob1,ob2)->ob1.getId().intValue()-ob2.getId().intValue());
				return list;
        }

   /*  @Override
    public boolean isInvoiceExist(Long id) {
        // TODO Auto-generated method stub
        return repo.existsById(id);    }
  */
        
    
}
