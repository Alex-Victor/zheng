package com.cache.dao;

import com.cache.dao.CacheDao;
import com.cache.entity.Account;

import java.util.Collections;
import java.util.List;

public class MockDao implements CacheDao<Account> {
    @Override
    public Account get(Long id) {
        return new Account("test " + id);
    }

    @Override
    public List<Account> list() {
        return Collections.emptyList();
    }
}
