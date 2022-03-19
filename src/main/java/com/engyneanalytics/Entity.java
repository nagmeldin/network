package com.engyneanalytics;

public sealed interface Entity<ID> permits Maker, Device {
    ID id();
}
