package org.ecf_mai.repository.impl;

import org.ecf_mai.entity.Customer;
import org.ecf_mai.repository.BaseRepository;

import java.util.List;

public class CustomerRepository extends BaseRepository<Customer> {
    @Override
    public Customer get(int id) {
        session = factory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        session = factory.openSession();
        List<Customer> customers = session.createQuery("FROM Customer ",Customer.class).list();
        session.close();
        return customers;
    }
}
