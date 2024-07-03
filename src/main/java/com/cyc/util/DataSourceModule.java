package com.cyc.util;

import com.alibaba.druid.pool.DruidDataSource;
import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@DimModule
@Component
public class DataSourceModule implements SpringModule {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource = null;

    @Autowired
    private DataSourceConfiguration dataSourceConfiguration = null;

    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        // .DataSource form Spring boot into Hasor
        apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));

        for (DataSourceConfig ds: dataSourceConfiguration.getDatabases()) {
            apiBinder.installModule(new JdbcModule(Level.Full,ds.getName(),getDataSource(ds.getUrl(),ds.getDriverClassName(),ds.getUsername(),ds.getPassword())));
        }
    }

    private DruidDataSource getDataSource(String jdbcUrl, String driver, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(5);
        return dataSource;
    }
}
