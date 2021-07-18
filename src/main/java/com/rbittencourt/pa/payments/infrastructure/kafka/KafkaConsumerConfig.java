package com.rbittencourt.pa.payments.infrastructure.kafka;

import com.rbittencourt.pa.payments.infrastructure.ecommerceorder.EcommerceOrder;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EcommerceOrder> paymentKafkaListenerContainerFactory() {
        var consumerFactory = new DefaultKafkaConsumerFactory<>(
            props(), new StringDeserializer(), new JsonDeserializer<>(EcommerceOrder.class, false)
        );
        var factory = new ConcurrentKafkaListenerContainerFactory<String, EcommerceOrder>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(GROUP_ID_CONFIG, "group-payments");

        return props;
    }

}
