package org.ecf_mai.repository.impl;

import org.ecf_mai.entity.Product;
import org.ecf_mai.repository.BaseRepository;

import java.util.List;

public class ProductRepository extends BaseRepository<Product> {
    @Override
    public Product get(int id) {
        session = factory.openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public List<Product> getAll() {
        session = factory.openSession();
        List<Product> products = session.createQuery("FROM Product", Product.class).list();
        session.close();
        return products;
    }
}
