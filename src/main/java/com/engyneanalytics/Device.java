package com.engyneanalytics;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author: Nagm Eldin
 */

@MappedEntity
public record Device(
        @GeneratedValue
        @Id
        Long id,
        @NotNull
        String os,
        @Positive
        int weight,
        @Nullable
        Maker maker
) implements Entity<Long> {
}
