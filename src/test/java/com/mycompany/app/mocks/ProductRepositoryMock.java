package com.mycompany.app.mocks;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.core.Product;
import com.mycompany.core.ports.outbound.ForRepository;
import com.mycompany.core.ports.outbound.ForRepositoryException;

public class ProductRepositoryMock implements ForRepository<Product, String> {
    public List<Product> array = new ArrayList<Product>();

    @Override
    public List<Product> list() throws ForRepositoryException {
        return array;
    }

    @Override
    public Product create(Product item) throws ForRepositoryException {
        array.add(item);
        return item;
    }

    @Override
    public Product get(String id) throws ForRepositoryException {
        for (Product item : array) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    @Override
    public void update(Product item) throws ForRepositoryException {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == item.getId()) {
                array.set(i, item);
                return;
            }
        }
    }

    @Override
    public void delete(String id) throws ForRepositoryException {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                array.remove(i);
                return;
            }
        }
    }
}
