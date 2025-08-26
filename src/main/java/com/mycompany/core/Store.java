package com.mycompany.core;

import java.util.List;

public class Store {
    private ProductController productController;
    private OrderController orderController;

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public List<Product> listProducts() throws Exception {
        return this.productController.list();
    }

    public Product createProduct(Product product) throws Exception {
        return this.productController.createProduct(product);
    }

    public void updatePrice(String id, double newPrice) throws Exception {
        this.productController.updatePrice(id, newPrice);
    }

    public void deleteProduct(String id) throws Exception {
        this.productController.deleteProduct(id);
    }

    public List<Order> listOrders() throws Exception {
        return this.orderController.list();
    }

    public Order createOrder(String productId, int quantity) throws Exception {
        var p = this.productController.getProduct(productId);
        return this.orderController.createOrder(p, quantity);
    }
}
