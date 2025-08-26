package com.mycompany.adapters.outbound.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
interface ResultSetHandler {
    void handle(ResultSet rs) throws SQLException;
}