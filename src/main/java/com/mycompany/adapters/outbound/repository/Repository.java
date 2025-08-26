package com.mycompany.adapters.outbound.repository;

import com.mycompany.core.common.*;
import com.mycompany.core.ports.outbound.ForLogging;

public abstract class Repository {
    private AppContext ctx;
    private ForLogging logger;

    public Repository(AppContext ctx) {
        this.ctx = ctx;
        this.logger = ctx.getLogger(this.getClass());
    }

    protected AppContext ctx() {
        return this.ctx;
    }

    protected ForLogging logger() {
        return this.logger;
    }
}
