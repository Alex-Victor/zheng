package com.cache.config;

public interface DbCallback<T> {
    T doInDb();
}
