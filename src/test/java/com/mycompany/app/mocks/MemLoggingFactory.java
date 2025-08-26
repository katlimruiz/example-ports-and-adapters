package com.mycompany.app.mocks;

import com.mycompany.core.ports.outbound.ForLogging;
import com.mycompany.core.ports.outbound.ForLoggingFactory;

public class MemLoggingFactory implements ForLoggingFactory {

    @Override
    public ForLogging create(Object loggerKey) {
        return new MemLogger();
    }

}
