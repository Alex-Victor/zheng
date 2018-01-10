package com.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

public class MultiDataSource extends AbstractRoutingDataSource {

    private Logger logger = LoggerFactory.getLogger(MultiDataSource.class);

    @Override
    protected String determineCurrentLookupKey() {
       String lookupKey =  ThreadLocalUtils.getDataSourceHolder().get().getValue();
       this.logger.info("current database:{}", lookupKey);
       return lookupKey;
    }

}
