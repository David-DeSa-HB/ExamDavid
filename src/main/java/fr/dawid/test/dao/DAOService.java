package fr.dawid.test.dao;

import java.util.List;
import java.util.Map;

public interface DAOService<T> {
    List<T> findAll();

    List<T> findBy(Map<String, Object> fields, Integer limit, Map<String, String> orders);

    T findOneBy(String field, Object value);

    T save(T o);
}
