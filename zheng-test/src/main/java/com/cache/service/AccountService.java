package com.cache.service;

import com.cache.config.CacheExpire;
import com.cache.config.DataSource;
import com.cache.config.DataSourceEnum;
import com.cache.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Cacheable(value = "redis", key = "'test:'.concat(#accountName)")
    @CacheExpire(20)
    @DataSource(DataSourceEnum.READ)
    public Account getAccountByName(String accountName) {
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }
        return accountOptional.get();
    }

    @CacheEvict(value = "redis", key = "'test:'.concat(#accountName)")
    @DataSource
    public int update(String accountName){//update db, evict cache
        this.logger.info("update or delete account from DB, evict from cache");
        return 1;
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("=====================real querying db... {}", accountName);
        return Optional.of(new Account(accountName));
        //throw new UnsupportedOperationException();//抛异常，事务执行失败，外层的Cache也会失败
    }
}