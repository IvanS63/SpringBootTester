package com.myapp.bookstore.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;

/**
 * Starts Kafka Container for integration tests (overrides and adds some properties for integration-test profile).
 *
 * @author Ivan_Semenov
 */
@Slf4j
@ContextConfiguration(initializers = KafkaAbstractIntegrationTest.Initializer.class)
@RunWith(SpringRunner.class)
public abstract class KafkaAbstractIntegrationTest {

    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer("4.1.2");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("kafka.enabled=true")
                    .and("kafka.bootstrap-servers=" + kafka.getBootstrapServers())
                    .and("kafka-service-app.bootstrap-servers=" + kafka.getBootstrapServers())
                    .and("kafka-service-app.topic=author-request-dto")
                    .applyTo(applicationContext);}
    }
}
