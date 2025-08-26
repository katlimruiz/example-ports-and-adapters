package com.mycompany.adapters.outbound.logging;

import com.mycompany.core.ports.outbound.*;
import org.tinylog.Logger;

public class TinyLoggingFactory implements ForLoggingFactory {
    public ForLogging create(Object loggerKey) {
        var logger = new TinyLogger(Logger.tag(loggerKey.toString()));
        return logger;
    }
}
