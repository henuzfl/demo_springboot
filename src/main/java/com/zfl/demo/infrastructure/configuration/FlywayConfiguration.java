package com.zfl.demo.infrastructure.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    final DataSource dataSource;

    public FlywayConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Value("${spring.flyway.locations}")
    private String[] locations;

    @PostConstruct
    public void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(locations)
                .baselineOnMigrate(true)
                .load();
        flyway.repair();
        flyway.migrate();
    }
}


