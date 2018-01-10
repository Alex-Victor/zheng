package com.cache.config;

import javax.print.DocFlavor;

public enum DataSourceEnum {
    WRITER("write"),READ("read");

    private String value;
    DataSourceEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
