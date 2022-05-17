package com.proj.flight.service;

import javax.transaction.Transactional;

public interface CRUD<D> {

    @Transactional
    void create(D entity);

    @Transactional
    D findById(Long id);

    @Transactional
    D update(D dto);

    @Transactional
    void delete(Long id);
}
