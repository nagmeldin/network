package com.engyneanalytics;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.validation.constraints.NotNull;

/**
 * @author: Nagm Eldin
 */

@MappedEntity
public record Maker(
        @GeneratedValue
        @Id
        Long id,
        @NotNull
        String name
) implements Entity<Long> {

        public Maker( String name){
                this(null, name);
        }
}
