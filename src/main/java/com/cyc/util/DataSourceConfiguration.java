package com.cyc.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gaosg
 */
@Component
@ConfigurationProperties(prefix = "dataway")
public class DataSourceConfiguration {
    private List<DataSourceConfig> databases;

    public List<DataSourceConfig> getDatabases() {
        return databases;
    }

    public void setDatabases(List<DataSourceConfig> databases) {
        this.databases = databases;
    }
}
