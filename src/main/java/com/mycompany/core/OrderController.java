package com.mycompany.core;

import com.mycompany.core.common.*;
import com.mycompany.core.base.*;
import java.time.Instant;
import java.util.List;

import com.mycompany.core.ports.outbound.ForLogging;
import com.mycompany.core.ports.outbound.ForRepository;

public class OrderController extends Controller {
    private ForRepository<Order, String> repository;
    private ForLogging logger;

    public OrderController(AppContext ctx) {
        super(ctx);
        this.logger = ctx.getLogger(OrderController.class);
    }

    public void setRepository(ForRepository<Order, String> repository) {
        this.repository = repository;
    }

    public Order createOrder(Product product, int quantity) throws Exception {
        this.logger.debug("creating order with product {} and quantity {}", product.getId(), quantity);
        var order = new Order();
        order.setOrderNumber(String.valueOf(Instant.now().toEpochMilli()));
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setPrice(product.getPrice());
        order.setQuantity(quantity);

        var result = this.repository.create(order);
        this.logger.info("created order {} {}", order.getId(), order.getOrderNumber());
        return result;
    }

    public Order getOrder(String id) throws Exception {
        return this.repository.get(id);
    }

    public List<Order> list() throws Exception {
        return this.repository.list();
    }
}
