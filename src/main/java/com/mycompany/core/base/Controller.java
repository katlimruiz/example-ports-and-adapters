package com.mycompany.core.base;

import com.mycompany.core.common.*;
import com.mycompany.core.ports.outbound.ForLogging;

public abstract class Controller {
    protected AppContext ctx;
    private ForLogging logger;

    public Controller(AppContext ctx) {
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