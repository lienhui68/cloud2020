package com.eh.cloud.seata.config;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@MapperScan(basePackages = "com.eh.cloud.seata.dao.storage", sqlSessionTemplateRef = "storageSqlSessionTemplate")
@Configuration
public class StorageDataSourceConfig {

    // 数据源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.storage")
    public DataSource storageDataSource() {
        return DataSourceBuilder.create().build();
    }

    // sqlSessionFactory
    @Bean
    @SneakyThrows
    public SqlSessionFactory storageSqlSessionFactory(@Qualifier("storageDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 如果使用了maaper文件还要加上下面这行
        // sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/example/datasources/mapper/xxx/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    // sqlSessionTemplate
    @Bean
    public SqlSessionTemplate storageSqlSessionTemplate(@Qualifier("storageSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // 事务管理器，seata演示中不需要，因为是单条sql操作
    @Bean
    public DataSourceTransactionManager storageTransactionManager(@Qualifier("storageDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
