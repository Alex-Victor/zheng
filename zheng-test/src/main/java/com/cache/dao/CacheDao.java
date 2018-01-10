package com.cache.dao;

import java.util.Collections;
import java.util.List;

public interface CacheDao<T> {
    default T get(Long id){return null;}

    default List<T> list(){return Collections.emptyList();}
}
