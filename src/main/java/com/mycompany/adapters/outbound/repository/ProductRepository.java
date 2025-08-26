package com.mycompany.adapters.outbound.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import com.mycompany.core.common.*;
import com.mycompany.core.Product;
import com.mycompany.core.ports.outbound.ForRepository;
import com.mycompany.core.ports.outbound.ForRepositoryException;

public class ProductRepository extends Repository implements ForRepository<Product, String> {

    public ProductRepository(AppContext ctx) {
        super(ctx);
    }

    private Product load(ResultSet rs, Product order) throws SQLException {
        order.setId(rs.getString("id"));
        order.setName(rs.getString("name"));
        order.setPrice(rs.getDouble("price"));
        return order;
    }

    public List<Product> list() throws ForRepositoryException {
        var results = new ArrayList<Product>();
        try {
            SqliteDatabase.getInstance().executParameterizedQuery("select * from product", new String[] {},
                    rs -> {
                        while (rs.next()) {
                            // TODO: this can be turned into an Adapter, or using a SQL wrapper would work
                            // better
                            results.add(load(rs, new Product()));
                        }
                    });
            return results;
        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }
    }

    public Product get(String key) throws ForRepositoryException {
        var result = new AtomicReference<Product>();
        try {
            SqliteDatabase.getInstance().executParameterizedQuery("select * from product where id = ?",
                    new String[] { key },
                    rs -> {
                        if (rs.next()) {
                            // TODO: this can be turned into an Adapter, or using a SQL wrapper would work
                            // better
                            result.set(load(rs, new Product()));
                        }
                    });

        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }

        return result.get();
    }

    public Product create(Product item) throws ForRepositoryException {
        UUID uuid = UUID.randomUUID();
        item.setId(uuid.toString());
        try {
            int affected = SqliteDatabase.getInstance().executParameterizedUpdate("""
                    insert into product (id, name, price)
                    values (?, ?, ?);
                    """,
                    new String[] { item.getId(), item.getName(), String.valueOf(item.getPrice()) });
            this.logger().info("{} product was added", affected);
            return item;
        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }
    }

    public void update(Product item) throws ForRepositoryException {
        try {
            SqliteDatabase.getInstance().executParameterizedUpdate("""
                    update product set name = ?, price = ?
                    where id = ?
                    """,
                    new String[] { item.getName(), String.valueOf(item.getPrice()), item.getId() });
        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }
    }

    public void delete(String key) throws ForRepositoryException {
        try {
            SqliteDatabase.getInstance().executParameterizedUpdate("""
                    delete from product where id = ?
                    """,
                    new String[] { key });
        } catch (SQLException ex) {
            throw new ForRepositoryException(ex);
        }
    }
}
