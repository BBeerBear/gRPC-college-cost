package com.bbeerbear.grpccollegecost.aggregator.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("Topic-Exchange");
    }
    @Bean
    public Queue topic1() {
        return new Queue("Cost-[Year]-[State]-[Type]-[Length]");
    }
    @Bean
    public Queue topic2() {
        return new Queue("Top5-Expensive-[Year]-[Type]-[Length]");
    }
    @Bean
    public Queue topic3() {
        return new Queue("Top5-Economic-[Year]-[Type]-[Length]");
    }
    @Bean
    public Queue topic4() {
        return new Queue("Top5-HighestGrow-[Years]");
    }
    @Bean
    public Queue topic5() {
        return new Queue("AverageExpense-[Year]-[Type]-[Length]");
    }

    @Bean
    public Binding binding1(TopicExchange topicExchange,
                            Queue topic1) {
        return BindingBuilder.bind(topic1)
                .to(topicExchange)
                .with("cost.*.*.*.*");
    }
    @Bean
    public Binding binding2(TopicExchange topicExchange,
                            Queue topic2) {
        return BindingBuilder.bind(topic2)
                .to(topicExchange)
                .with("top5.expensive.*.*.*");
    }
    @Bean
    public Binding binding3(TopicExchange topicExchange,
                            Queue topic3) {
        return BindingBuilder.bind(topic3)
                .to(topicExchange)
                .with("top5.economic.*.*.*");
    }
    @Bean
    public Binding binding4(TopicExchange topicExchange,
                            Queue topic4) {
        return BindingBuilder.bind(topic4)
                .to(topicExchange)
                .with("top5.highestGrow.*.*.*");
    }

    @Bean
    public Binding binding5(TopicExchange topicExchange,
                            Queue topic5) {
        return BindingBuilder.bind(topic5)
                .to(topicExchange)
                .with("averageExpense.*.*.*");
    }
}
