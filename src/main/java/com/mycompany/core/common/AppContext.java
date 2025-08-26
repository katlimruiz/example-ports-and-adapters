package com.mycompany.core.common;

import com.mycompany.core.ports.outbound.*;

public class AppContext {
    private ForLoggingFactory loggingFactory;

    public AppContext(ForLoggingFactory loggingFactory) {
        this.loggingFactory = loggingFactory;
    }

    public ForLogging getLogger(Object loggerKey) {
        return this.loggingFactory.create(loggerKey);
    }
}
