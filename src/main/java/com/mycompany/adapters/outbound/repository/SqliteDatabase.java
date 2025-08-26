package com.mycompany.adapters.outbound.repository;

import java.sql.*;

public class SqliteDatabase {
    private static final String[] DatabaseCreationScripts = new String[] { """
            create table if not exists product(
                id string,
                name string,
                price double
            );
            """, """
            create table if not exists orders(
                id string,
                order_number string,
                product_id string,
                product_name string,
                quantity int,
                price double,
                amount double
            );
                """
    };

    private static SqliteDatabase singleton;

    private SqliteConfiguration configuration;

    public static SqliteDatabase getInstance() {
        if (singleton == null) {
            throw new Error("Init method was not called yet");
        }

        return singleton;
    }

    public static void init(SqliteConfiguration configuration) throws SQLException {
        if (singleton != null) {
            throw new Error("Init method can only be called once");
        }

        singleton = new SqliteDatabase();
        singleton.configuration = configuration;
        try (var connection = singleton.newConnection();
                var stmt = connection.createStatement()) {
            for (String sql : DatabaseCreationScripts) {
                stmt.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private Connection newConnection() throws SQLException {
        var connection = DriverManager.getConnection(configuration.getConnectionString());
        connection.setAutoCommit(true);

        // var meta = connection.getMetaData();
        // System.out.println("The driver name is " + meta.getDriverName());

        return connection;
    }

    private PreparedStatement newPreparedStmt(Connection cnx, String sql) throws SQLException {
        var statement = cnx.prepareStatement(sql);
        statement.setQueryTimeout(this.configuration.getCommandTimeout());
        return statement;
    }

    public int executePlainUpdate(String sql) throws SQLException {
        try (var connection = this.newConnection();
                var statement = connection.createStatement()) {

            var result = statement.executeUpdate(sql);
            return result;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int executParameterizedUpdate(String sql, String[] values) throws SQLException {
        try (var connection = this.newConnection();
                var statement = this.newPreparedStmt(connection, sql)) {

            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]); // JDBC uses 1-based indexing
            }

            var result = statement.executeUpdate();
            return result;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void executParameterizedQuery(String sql, String[] values, ResultSetHandler handler)
            throws SQLException {
        try (var connection = this.newConnection();
                var statement = this.newPreparedStmt(connection, sql)) {

            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]); // JDBC uses 1-based indexing
            }

            try (var rs = statement.executeQuery()) {
                handler.handle(rs);
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
