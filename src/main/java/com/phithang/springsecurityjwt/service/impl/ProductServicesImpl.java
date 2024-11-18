package com.phithang.springsecurityjwt.service.impl;
import com.phithang.springsecurityjwt.entity.Product;
import com.phithang.springsecurityjwt.repository.ProductRepository;
import com.phithang.springsecurityjwt.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices {
    @Autowired
    private ProductRepository repo;

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> listAll() {
        return repo.findAll();
    }
}
