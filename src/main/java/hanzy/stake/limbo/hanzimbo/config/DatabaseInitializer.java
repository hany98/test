//package hanzy.stake.limbo.hanzimbo.config;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.init.DataSourceInitializer;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//
//import javax.sql.DataSource;
//
//@Slf4j
////@Profile("local")
//@Configuration
//@NoArgsConstructor
//public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {
//
//    private static void initDatabase(DataSource oracleDataSource) {
//        ResourceDatabasePopulator oracleDatabasePopulator = new ResourceDatabasePopulator();
//        oracleDatabasePopulator.addScript(new ClassPathResource("/databases/ddl/schema-oracle.sql"));
//        oracleDatabasePopulator.addScript(new ClassPathResource("/databases/dml/data-oracle.sql"));
//        DataSourceInitializer oracleDataSourceInitializer = new DataSourceInitializer();
//        oracleDataSourceInitializer.setDataSource(oracleDataSource);
//        oracleDataSourceInitializer.setDatabasePopulator(oracleDatabasePopulator);
//        try {
//            oracleDataSourceInitializer.afterPropertiesSet();
//        } catch (Exception e) {
//            log.warn(e.getMessage());
//            log.warn("Oracle Database already initialized");
//        }
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        DataSource oracleDataSource = event.getApplicationContext().getBean("oracleDataSource", DataSource.class);
////        Environment environment = event.getApplicationContext().getBean("environment", Environment.class);
////        if ("local".equals(environment.getActiveProfiles()[0])) {
//            initDatabase(oracleDataSource);
////        }
//    }
//
//}
