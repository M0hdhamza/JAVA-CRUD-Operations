package com.dev.invoice.rest.invoice_rest_api.exception;

public class InvoiceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvoiceNotFoundException() {
		super();
	}
	
	public InvoiceNotFoundException(String message) {
		super(message);
	}
	
}
