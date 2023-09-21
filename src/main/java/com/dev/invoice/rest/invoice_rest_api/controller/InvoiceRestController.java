package com.dev.invoice.rest.invoice_rest_api.controller;


//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dev.invoice.rest.invoice_rest_api.entity.Invoice;
import com.dev.invoice.rest.invoice_rest_api.exception.InvoiceNotFoundException;
import com.dev.invoice.rest.invoice_rest_api.service.IInvoiceService;
//import com.dev.invoice.rest.invoice_rest_api.util.InvoiceUtil;

//import ch.qos.logback.classic.pattern.Util;


@RestController
@RequestMapping("/api")
public class InvoiceRestController {
    @Autowired
    private IInvoiceService service; 
    
    
    @PostMapping("/invoices")
	public ResponseEntity<String> saveInvoice(@RequestBody Invoice inv){
		ResponseEntity<String> resp = null;
		try{
			Long id = service.saveInvoice(inv);
			resp= new ResponseEntity<String>(
					"Invoice '"+id+"' created",HttpStatus.CREATED); 
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"Unable to save Invoice", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}


    @GetMapping("/invoices")
	public ResponseEntity<?> getAllInvoices() {
		ResponseEntity<?> resp=null;
		try {
			List<Invoice> list= service.getAllInvoices();
			resp= new ResponseEntity<List<Invoice>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"Unable to get Invoice", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}


    @GetMapping("/invoices/{id}")
	public ResponseEntity<?> getOneInvoice(@PathVariable Long id){
		ResponseEntity<?> resp= null;
		try {
			Invoice inv= service.getOneInvoice(id);
			resp= new ResponseEntity<Invoice>(inv,HttpStatus.OK);
		}catch (InvoiceNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"Unable to find Invoice", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}


    @DeleteMapping("/invoices/{id}")
	public ResponseEntity<String> deleteInvoice(@PathVariable Long id){
		
		ResponseEntity<String> resp= null;
		try {
			service.deleteInvoice(id);
			resp= new ResponseEntity<String> (
					"Invoice '"+id+"' deleted",HttpStatus.OK);
			
		} catch (InvoiceNotFoundException e) {
			e.printStackTrace();
			resp= new ResponseEntity<String>(
					"Unable to delete Invoice", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return resp;
	}

    public void copyNonNullValues(Invoice req, Invoice db) {
		
        if(req.getId() !=null) {
			db.setId(req.getId());
		}
		
		if(req.getName() !=null) {
			db.setName(req.getName());
		}

        if(req.getLocation() !=null) {
			db.setLocation(req.getLocation());
		}
		
		if(req.getAmount() !=null) {
			db.setAmount(req.getAmount());
		}
    }
	
    

    @PutMapping("/invoices/{id}")
	public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice){
		
		ResponseEntity<String> resp = null;
		try {
			Invoice inv= service.getOneInvoice(id);
           copyNonNullValues(invoice, inv);
           service.updateInvoice(inv);
           resp = new ResponseEntity<String>(
					"Invoice '"+id+"' Updated",
					HttpStatus.OK);
                    
			return resp;
		} catch (InvoiceNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"Unable to Update Invoice", 
					HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return resp;
	}

}
   /*  @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        Map<String, String> response = new HashMap<>();
        try {
            Invoice inv = service.getOneInvoice(id);
            copyNonNullValues(invoice, inv);

            service.updateInvoice(inv);
            response.put("message", "Invoice '" + id + "' Updated");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            response.put("error", "Unable to Update Invoice");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
*/