//package hanzy.stake.limbo.hanzimbo.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import hanzy.stake.limbo.hanzimbo.entity.GenerationSeedsEntity;
//import org.hibernate.cfg.AvailableSettings;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Objects;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "hanzy.stake.limbo.hanzimbo.repository.oracle",
//        entityManagerFactoryRef = "oracleEntityManagerFactory",
//        transactionManagerRef = "oracleTransactionManager")
//public class OracleDataSourceConfiguration {
//    @Value("${app.datasource.oracle.hibernate.dialect}")
//    private String hibernateDialect;
//    @Value("${app.datasource.oracle.hibernate.hbm2ddl.auto}")
//    private String hbm2ddlAuto;
//
//    @Bean
//    @ConfigurationProperties("app.datasource.oracle")
//    public DataSourceProperties oracleDatasourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @ConfigurationProperties("app.datasource.oracle.configuration")
//    public DataSource oracleDataSource() {
//        return oracleDatasourceProperties().initializeDataSourceBuilder()
//                .type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "oracleEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(AvailableSettings.DIALECT, hibernateDialect);
//        props.put(AvailableSettings.HBM2DDL_AUTO, hbm2ddlAuto);
//        return builder
//                .dataSource(oracleDataSource())
//                .properties(props)
//                .packages(GenerationSeedsEntity.class)
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager oracleTransactionManager(
//            final @Qualifier("oracleEntityManagerFactory") AbstractEntityManagerFactoryBean oracleEntityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(oracleEntityManagerFactory.getObject()));
//    }
//}
