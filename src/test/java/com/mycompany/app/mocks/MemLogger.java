package com.mycompany.app.mocks;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.core.ports.outbound.ForLogging;

public class MemLogger implements ForLogging {

    private List<Object[]> items = new ArrayList<Object[]>();

    private void add(String level, String message, Object... args) {
        Object[] entry = new Object[args.length + 2];
        entry[0] = level;
        entry[1] = message;
        System.arraycopy(args, 0, entry, 2, args.length);
        items.add(entry);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public void debug(String message, Object... args) {
        this.add("debug", message, args);
    }

    @Override
    public void info(String message, Object... args) {
        this.add("info", message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        this.add("warn", message, args);
    }

    @Override
    public void error(Exception error) {
        this.add("error", error.getMessage());
    }

    @Override
    public void error(String message, Object... args) {
        this.add("error", message, args);
    }

}
