package com.zaz.servicevmoffers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@Configuration
@EnableRabbit
public class RabbitMQ_SERVICE_VM_OFFERS_TO_VM {

    public static final String SERVICE_VM_OFFERS_TO_VM_EXCHANGE_NAME = "serviceVmOffersToVmExchange";

    public static final String SERVICE_VM_OFFERS_TO_VM_QUEUE_NAME = "serviceVmOffersToVmQueue";

    
    @Bean
    public Queue vm_offers_queue() {
        return new Queue(SERVICE_VM_OFFERS_TO_VM_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange vm_offers_exchange() {
        return new FanoutExchange(SERVICE_VM_OFFERS_TO_VM_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(vm_offers_queue()).to(vm_offers_exchange());
    }
}
