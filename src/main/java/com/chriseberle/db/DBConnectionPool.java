package com.chriseberle.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class provides a connection pool for a database.
 */
public class DBConnectionPool {
    String JDBC_URL;
    String DB_USERNAME;
    String DB_PASSWORD;
    private BlockingQueue<Connection> connectionPool;

    /**
     * Create a new database connection pool.
     * @param JDBC_URL the JDBC URL
     * @param DB_USERNAME the database username
     * @param DB_PASSWORD the database password
     * @param poolSize the pool size
     */
    public DBConnectionPool(String JDBC_URL, String DB_USERNAME, String DB_PASSWORD, int poolSize) {
        this.JDBC_URL = JDBC_URL;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
        this.connectionPool = new ArrayBlockingQueue<>(poolSize);
        createConnectionPool(poolSize);
    }

    /**
     * Create a connection pool.
     * @param poolSize the pool size
     */
    private void createConnectionPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
                connectionPool.add(connection);
            } catch (SQLException e) {
                System.err.println("[SQL ERROR] occurred while attempting to create a connection pool. [MSG] -> " + e);
            }
        }
    }

    /**
     * Get a connection from the pool.
     * @return the connection
     */
    public Connection getConnection() {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            System.err.println("[ERROR] Interrupted while waiting for a connection from the pool.");
            return null;
        }
    }

    /**
     * Return a connection to the pool.
     * @param connection the connection
     */
    public void returnConnection(Connection connection) {
        if (connection != null) {
            connectionPool.offer(connection);
        }
    }

    /**
     * Shutdown the connection pool and close all connections.
     */
    public void shutdown() {
        while (!connectionPool.isEmpty()) {
            try {
                Connection connection = connectionPool.poll();
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("[SQL ERROR] occurred while attempting to close a connection. [MSG] -> " + e);
            }
        }
    }
}
