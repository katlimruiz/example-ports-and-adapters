package com.mycompany.app;

import java.sql.SQLException;

import com.mycompany.adapters.outbound.logging.*;
import com.mycompany.adapters.outbound.repository.*;
import com.mycompany.core.*;
import com.mycompany.core.common.*;

public class Configurator {
    static Store start() {
        var sqlConfig = new SqliteConfiguration();
        try {
            SqliteDatabase.init(sqlConfig);
        } catch (SQLException ex) {
            System.out.println(String.format("Error while initializing database %s", ex.getMessage()));
            System.exit(1);
        }

        var ctx = new AppContext(new Log4jLoggingFactory());
        // var ctx = new AppContext(new TinyLoggingFactory());

        var or = new OrderRepository(ctx);
        var oc = new OrderController(ctx);
        oc.setRepository(or);

        var pr = new ProductRepository(ctx);
        var pc = new ProductController(ctx);
        pc.setRepository(pr);

        var store = new Store();
        store.setOrderController(oc);
        store.setProductController(pc);

        return store;
    }
}
