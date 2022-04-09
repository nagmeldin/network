package com.engyneanalytics;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

/**
 * @author: Nagm Eldin
 */

@JdbcRepository(dialect = Dialect.H2)
public interface DeviceRepository extends CrudRepository<Device, Long> {

    @Override
    public List<Device> findAll();

}
