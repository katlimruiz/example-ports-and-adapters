package com.mycompany.core;

import java.util.List;
import com.mycompany.core.common.*;
import com.mycompany.core.base.*;
import com.mycompany.core.ports.outbound.ForRepository;

public class ProductController extends Controller {
    private ForRepository<Product, String> repository;

    public ProductController(AppContext ctx) {
        super(ctx);
    }

    public void setRepository(ForRepository<Product, String> repository) {
        this.repository = repository;
    }

    public List<Product> list() throws Exception {
        return this.repository.list();
    }

    public Product getProduct(String id) throws Exception {
        return this.repository.get(id);
    }

    public Product createProduct(Product product) throws Exception {
        this.logger().debug("creating product {}", product.getName());
        var result = this.repository.create(product);
        this.logger().info("created product {}", product.getId());
        return result;
    }

    public void updateProduct(Product product) throws Exception {
        this.logger().debug("updating product {}", product.getId());
        var p = this.repository.get(product.getId());
        p.setName(product.getName());
        this.repository.update(p);
        this.logger().info("updated product {}", product.getId());
    }

    public void updatePrice(String id, double newPrice) throws Exception {
        this.logger().debug("updating product {} price", id, newPrice);
        var p = this.repository.get(id);
        p.setPrice(newPrice);
        this.repository.update(p);
        this.logger().info("updated product {}", id);
    }

    public void deleteProduct(String id) throws Exception {
        this.logger().debug("deleting product {}", id);
        this.repository.delete(id);
        this.logger().info("deleted product {}", id);
    }
}
