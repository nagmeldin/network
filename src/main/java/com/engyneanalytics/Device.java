package com.engyneanalytics;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@MappedEntity
public record Device(
        @GeneratedValue
        @Id
        Long id,
        @NotBlank
        String os,
        @Positive
        int weight,
        @NotNull
        Maker maker
) implements Entity<Long> {
}
