package com.mycompany.adapters.outbound.repository;

import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import com.mycompany.core.*;
import com.mycompany.core.common.*;
import com.mycompany.core.ports.outbound.*;

public class OrderRepository extends Repository implements ForRepository<Order, String> {
    public OrderRepository(AppContext ctx) {
        super(ctx);
    }

    private Order load(ResultSet rs, Order order) throws SQLException {
        order.setId(rs.getString("id"));
        order.setOrderNumber(rs.getString("order_number"));
        order.setProductId(rs.getString("product_id"));
        order.setProductName(rs.getString("product_name"));
        order.setQuantity(rs.getInt("quantity"));
        order.setPrice(rs.getDouble("price"));
        order.setAmount(rs.getDouble("amount"));
        return order;
    }

    public List<Order> list() throws ForRepositoryException {
        var results = new ArrayList<Order>();
        try {
            SqliteDatabase.getInstance().executParameterizedQuery("select * from orders", new String[] {},
                    rs -> {
                        while (rs.next()) {
                            // TODO: this can be turned into an Adapter, or using a SQL wrapper would work
                            // better
                            results.add(load(rs, new Order()));
                        }
                    });
            return results;
        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }
    }

    public Order get(String key) throws ForRepositoryException {
        var result = new AtomicReference<Order>();
        try {
            SqliteDatabase.getInstance().executParameterizedQuery("select * from orders where id = ?",
                    new String[] { key },
                    rs -> {
                        if (rs.first()) {
                            // TODO: this can be turned into an Adapter, or using a SQL wrapper would work
                            // better
                            result.set(load(rs, new Order()));
                        }
                    });

        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }

        return result.get();
    }

    public Order create(Order item) throws ForRepositoryException {
        UUID uuid = UUID.randomUUID();
        item.setId(uuid.toString());
        try {
            SqliteDatabase.getInstance().executParameterizedUpdate("""
                    insert into orders (id, order_number, product_id, product_name, quantity, price, amount)
                    values (?, ?, ?, ?, ?, ?, ?)
                    """,
                    new String[] { item.getId(), item.getOrderNumber(), item.getProductId(),
                            item.getProductName(), String.valueOf(item.getQuantity()),
                            String.valueOf(item.getPrice()),
                            String.valueOf(item.getAmount()) });
            return item;
        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }
    }

    public void update(Order item) throws ForRepositoryException {
        throw new Error("Order is forbidden to be updated");
    }

    public void delete(String key) throws ForRepositoryException {
        throw new Error("Order is forbidden to be deleted");
    }
}
