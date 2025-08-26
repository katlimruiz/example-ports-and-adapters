package com.mycompany.app.mocks;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.core.Order;
import com.mycompany.core.ports.outbound.ForRepository;
import com.mycompany.core.ports.outbound.ForRepositoryException;

public class OrderRepositoryMock implements ForRepository<Order, String> {
    public List<Order> array = new ArrayList<Order>();

    @Override
    public List<Order> list() throws ForRepositoryException {
        return array;
    }

    @Override
    public Order create(Order item) throws ForRepositoryException {
        array.add(item);
        return item;
    }

    @Override
    public Order get(String id) throws ForRepositoryException {
        for (Order item : array) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    @Override
    public void update(Order item) throws ForRepositoryException {
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
