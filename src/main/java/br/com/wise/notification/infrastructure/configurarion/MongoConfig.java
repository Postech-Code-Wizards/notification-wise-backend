package br.com.wise.notification.infrastructure.configurarion;

import br.com.wise.notification.domain.converter.ZonedDateTimeReadConverter;
import br.com.wise.notification.domain.converter.ZonedDateTimeWriteConverter;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.username}")
    private String user;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.auto-index-creation}")
    private boolean autoIndexCreation;

    @Override
    @NonNull
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(
                String.format("mongodb://%s:%s@%s:%d/%s",
                        user,
                        password,
                        host,
                        port,
                        database)
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    @Override
    @NonNull
    protected String getDatabaseName() {
        return database;
    }

    @Override
    @NonNull
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()
        ));
    }

    @Override
    public boolean autoIndexCreation() {
        return autoIndexCreation;
    }

}