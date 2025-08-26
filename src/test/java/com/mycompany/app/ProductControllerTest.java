package com.mycompany.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.mycompany.app.mocks.*;
import com.mycompany.core.*;
import com.mycompany.core.common.AppContext;

@TestInstance(Lifecycle.PER_CLASS)
public class ProductControllerTest {
    private AppContext ctx;
    private ProductController pc;
    private ProductRepositoryMock pr;

    @BeforeAll
    public void beforeAll() {
        ctx = new AppContext(new MemLoggingFactory());
    }

    @BeforeEach
    public void beforeEach() {
        pr = new ProductRepositoryMock();
        pc = new ProductController(ctx);
        pc.setRepository(pr);
    }

    @Test
    public void testCreate() {
        var p = new Product();
        p.setId("A");
        p.setName("A");
        p.setPrice(1);

        try {
            pc.createProduct(p);
            Assertions.assertEquals(1, pr.array.size());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void testList() {
        var p = new Product();
        p.setId("A");
        p.setName("A");
        p.setPrice(1);

        try {
            pc.createProduct(p);
            var all = pc.list();
            Assertions.assertEquals(1, all.size());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

}
