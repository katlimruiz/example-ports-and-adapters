package com.mycompany.core.ports.outbound;

import java.util.List;

public interface ForRepository<T, K> {
    List<T> list() throws ForRepositoryException;

    T create(T item) throws ForRepositoryException;

    T get(K id) throws ForRepositoryException;

    void update(T item) throws ForRepositoryException;

    void delete(K id) throws ForRepositoryException;
}
