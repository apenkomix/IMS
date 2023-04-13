package System.IMS.repository.invoiceDao;

import System.IMS.entity.Invoice;

import java.util.List;

public interface InvoiceDao {
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);
    void addInvoice(Invoice invoice);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(Invoice invoice);
    List<Invoice> getInvoicesByStatus(String status);
}
