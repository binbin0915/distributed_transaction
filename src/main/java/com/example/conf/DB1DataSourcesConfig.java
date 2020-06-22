package com.example.conf;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @Description TODO
 * @Author bingfeng
 * @Date 2019/11/20 17:09
 */
//@Configuration
//@MapperScan(basePackages = "com.example.mapper.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
//public class DB1DataSourcesConfig {
//
//    @Primary
//    @Bean(name = "db1DataSource")
//    public DataSource dataSource(DB1Config DB1Config) {
//        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
//        mysqlXADataSource.setUrl(DB1Config.getUrl_jdbc());
//        mysqlXADataSource.setUser(DB1Config.getUsername());
//        mysqlXADataSource.setPassword(DB1Config.getPassword());
//        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
//
//        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
//        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
//        atomikosDataSourceBean.setUniqueResourceName("db1DataSource");
//
//        return atomikosDataSourceBean;
//    }
//
//    @Primary
//    @Bean(name = "db1SqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/db1/*.xml"));
//
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//
//        sessionFactoryBean.setConfiguration(configuration);
//        return sessionFactoryBean.getObject();
//    }
//
//    @Primary
//    @Bean(name = "db1SqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
/******************************************************************************/
//	更新履歴
//
//	更新日付		更新者				対象メソッド			更新内容
//	2017/10/19	汪　洋									新規作成
//
/******************************************************************************/


import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@MapperScan(basePackages = DB1DataSourcesConfig.PACKAGE, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1DataSourcesConfig {

    public static final String PACKAGE = "com.example.mapper.db1";

    public static final String MAPPER_LOCATION = "classpath:com/example/mapper/db1/*.xml";

    @Value("${spring.datasource.db1.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.db1.url}")
    private String url;
    @Value("${spring.datasource.db1.username}")
    private String username;
    @Value("${spring.datasource.db1.password}")
    private String password;
    @Value("${spring.datasource.db1.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.db1.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.db1.maxIdle}")
    private int maxIdle;
    @Value("${spring.datasource.db1.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.db1.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.db1.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.db1.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.db1.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.db1.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.db1.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.db1.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.db1.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.db1.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.db1.filters}")
    private String filters;
    @Value("${spring.datasource.db1.maxpoolsize}")
    private int maxpoolsize;
    /**
     *
     * @return DataSource
     */
    @Bean(name = "db1DataSource",initMethod="init",destroyMethod="close")
    public DataSource dataSource() {
        DruidXADataSource dataSource = new DruidXADataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            //dataSource.setFilters(filters);
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setMaxPoolSize(maxpoolsize);
        xaDataSource.setUniqueResourceName("db1DataSource");

        return xaDataSource;
    }

    /**
     *
     * @return SqlSessionFactory
     */// 创建Session
    @Bean(name = "db1SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1DataSource);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(DB1DataSourcesConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "db1SqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionFactory(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

	/*private Properties build(Environment env, String prefix) {

        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));

        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));

        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis",
                env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));

        return prop;
    }*/
}
