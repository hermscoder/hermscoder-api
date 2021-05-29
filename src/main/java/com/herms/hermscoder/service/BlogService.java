package com.herms.hermscoder.service;

import java.util.List;

public interface BlogService<T> {

    List<T> findAll();

    T findById(Long id);

    T save(T dto);

    T update(T dto);

    void delete(Long id);
}
