package org.aviation.projects.commons.service;

import javax.transaction.Transactional;

public interface CRUD<D> {

    @Transactional
    void create(D dto) throws Exception;

    @Transactional
    D findByCode(String code) throws Exception;

    @Transactional
    D update(D dto) throws Exception;

    @Transactional
    void delete(String uniqueNumber) throws Exception;
}
