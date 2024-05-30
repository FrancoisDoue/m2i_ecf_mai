package org.ecf_mai.repository.impl;

import org.ecf_mai.constant.StatusType;
import org.ecf_mai.entity.Customer;
import org.ecf_mai.entity.Invoice;
import org.ecf_mai.entity.InvoiceProduct;
import org.ecf_mai.entity.Product;
import org.ecf_mai.repository.BaseRepository;

import java.util.List;

public class InvoiceRepository extends BaseRepository<Invoice> {

    public boolean create(List<InvoiceProduct> invoiceProducts, Customer customer) {
        Invoice invoice = Invoice.builder().customer(customer).invoiceProducts(invoiceProducts).build();
        session = factory.openSession();
        session.beginTransaction();
        session.save(invoice);
        invoice.getInvoiceProducts().forEach(i -> {
            i.setInvoice(invoice);
            session.save(i);
        });
        session.getTransaction().commit();
        session.close();
        return invoice.getId() > 0;
    }

    @Override
    public Invoice get(int id) {
        return null;
    }

    public List<Invoice> getInvoiceByCustomer(Customer customer) {
        session = factory.openSession();
        List<Invoice> invoices = session.createQuery("FROM Invoice i where i.customer = :customer", Invoice.class)
                .setParameter("customer", customer)
                .getResultList();
        session.close();
        return invoices;
    }

    public List<Invoice> getInvoiceByStatus(StatusType status) {
        session = factory.openSession();
        List<Invoice> invoices = session.createQuery("from Invoice i where i.status = :status", Invoice.class)
                .setParameter("status", status)
                .getResultList();
        session.close();
        return invoices;
    }

    public double getTotalPrice(Invoice invoice) {
        session = factory.openSession();
        double result = session.createQuery("""
                select sum(p.price * ip.quantity) from InvoiceProduct ip
                    join Invoice i on i = ip.invoice
                    join Product p on p = ip.product
                    where i = :invoice
                """, Double.class)
                .setParameter("invoice", invoice)
                .getSingleResult();
        session.close();
        return result;
    }

    public Invoice cancel(int id) {
        Invoice invoice = get(id);
        invoice.setStatus(StatusType.CANCELED);
        update(invoice);
        return invoice;
    }

    public Invoice confirm(int id) {
        Invoice invoice = get(id);
        invoice.setStatus(StatusType.COMPLETED);
        update(invoice);
        session = factory.openSession();
        session.createQuery("""
                select p from Product p
                join InvoiceProduct ip on ip.product = p
                join Invoice i on ip.invoice = i
                where i = :invoice
                """, Product.class)
                .setParameter("invoice", invoice)
                .list();
//        System.out.println(products);
        return invoice;
    }

    @Override
    public List<Invoice> getAll() {
        return List.of();
    }
}
