package com.mycompany.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.mycompany.app.mocks.*;
import com.mycompany.core.*;
import com.mycompany.core.common.AppContext;

@TestInstance(Lifecycle.PER_CLASS)
public class OrderControllerTest {
    private AppContext ctx;
    private OrderController oc;
    private OrderRepositoryMock or;

    @BeforeAll
    public void beforeAll() {
        ctx = new AppContext(new MemLoggingFactory());
    }

    @BeforeEach
    public void beforeEach() {
        or = new OrderRepositoryMock();
        oc = new OrderController(ctx);
        oc.setRepository(or);
    }

    @Test
    public void testCreate() {
        var p = new Product();
        p.setId("A");
        p.setName("A");
        p.setPrice(10);

        try {
            oc.createOrder(p, 2);
            Assertions.assertEquals(1, or.array.size());
            Assertions.assertEquals(20, or.array.get(0).getAmount());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
