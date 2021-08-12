package com.mycompany.invoise.core.service.prefix;

import com.mycompany.invoise.core.entity.Invoice;
import com.mycompany.invoise.core.repository.InvoiceRepositoryInterface;
import com.mycompany.invoise.core.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class InvoiceServicePrefix implements InvoiceServiceInterface {


    @Value("${invoice.lastNumber}")
    private long lastNumber;
    @Value("${invoice.prefix}")
    private String prefix;
    private File fichier;

    public long getLastNumber() {
        return lastNumber;
    }

    @Override
    public List<Invoice> getInvoiceList() {
        return invoiceRepository.list();
    }

//public void setLastNumber(long lastNumber) {
    //    InvoiceServicePrefix.lastNumber = lastNumber;
    //}

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Autowired
    private InvoiceRepositoryInterface invoiceRepository;

    public InvoiceRepositoryInterface getInvoiceRepository() {
        return invoiceRepository;
    }

    public void setInvoiceRepository(InvoiceRepositoryInterface invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void createInvoice(Invoice invoice) {
        invoice.setNumber(String.valueOf(prefix+(++lastNumber)));
        invoiceRepository.create(invoice);
    }
}

