package com.colini.dao;

import java.util.List;

public interface GenericDAO<T> {
    void insert(T entity);
    void update(T entity);
    void delete(String cpf);
    T findById(String cpf);
    List<T> findAll();
}
