package com.prototype.order.config;

import com.prototype.order.entity.Order;
import com.prototype.order.service.OrderManageService;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;


@Configuration
public class KafkaConfig {

    @Autowired
    OrderManageService orderManageService;

    private static final Logger LOGGER = LoggerFactory.getLogger( KafkaConfig.class.getName() );

//    @Bean
//    public KTable<String, Order> table() {
//        KeyValueBytesStoreSupplier store =
//                Stores.persistentKeyValueStore("orders");
//        JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);
//        KStream<String, Order> stream = this.getBuilder()
//                .stream("orders", Consumed.with(Serdes.String(), orderSerde));
//        return stream.toTable(Materialized.<String, Order>as(store)
//                .withKeySerde(Serdes.String())
//                .withValueSerde(orderSerde));
//    }
//
//    @Bean
//    public KStream<String, Order> stream() {
//        JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);
//        KStream<String, Order> stream = this.getBuilder()
//                .stream("payment-orders", Consumed.with(Serdes.String(), orderSerde));
//
//        stream.join(
//                        this.getBuilder().stream("stock-orders"),
//                        orderManageService::confirm,
//                        JoinWindows.of(Duration.ofSeconds(10)),
//                        StreamJoined.with(Serdes.String(), orderSerde, orderSerde))
//                .peek((k, o) -> LOGGER.info("Output: {}", o))
//                .to("orders");
//
//        return stream;
//    }
//    @Bean
//    public NewTopic orders() {
//        return TopicBuilder.name("orders")
//                .partitions(3)
//                .compact()
//                .build();
//    }

//    @Bean
//    public StreamsBuilder getBuilder(){
//        return new StreamsBuilder();
//    }

//    @Bean
//    public NewTopic paymentTopic() {
//        return TopicBuilder.name("payment-orders")
//                .partitions(3)
//                .compact()
//                .build();
//    }
//
//    @Bean
//    public NewTopic stockTopic() {
//        return TopicBuilder.name("stock-orders")
//                .partitions(3)
//                .compact()
//                .build();
//    }
}
