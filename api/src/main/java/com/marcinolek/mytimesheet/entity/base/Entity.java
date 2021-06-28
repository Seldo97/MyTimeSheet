package com.marcinolek.mytimesheet.entity.base;

public interface Entity<T> {
    T getId();
    void setId(T id);
}
