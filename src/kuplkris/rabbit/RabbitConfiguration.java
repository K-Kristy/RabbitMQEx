package kuplkris.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static kuplkris.rabbit.ConstantsUtils.CALLBACK_QUEUE;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_EXCHANGE;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_1;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_2;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_FOR_TWO_LISTENERS;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_NAME;
import static kuplkris.rabbit.ConstantsUtils.FANOUT_EXCHANGE;
import static kuplkris.rabbit.ConstantsUtils.FANOUT_QUEUE_1;
import static kuplkris.rabbit.ConstantsUtils.FANOUT_QUEUE_2;
import static kuplkris.rabbit.ConstantsUtils.TOPIC_EXCHANGE;
import static kuplkris.rabbit.ConstantsUtils.TOPIC_QUEUE_1;
import static kuplkris.rabbit.ConstantsUtils.TOPIC_QUEUE_2;

@Configuration
public class RabbitConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE_NAME);
    }

    @Bean
    public Queue directQueueForTwoListeners() {
        return new Queue(DIRECT_QUEUE_FOR_TWO_LISTENERS);
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE_1);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE_2);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding errorBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("error");
    }

    @Bean
    public Binding errorBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("error");
    }

    @Bean
    public Binding infoBinding() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("info");
    }

    @Bean
    public Binding warningBinding() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("warning");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2);
    }


    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("*.orange.*");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("*.*.rabbit");
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("lazy.#");
    }

    @Bean
    public Queue callbackQueue() {
        return new Queue(CALLBACK_QUEUE);
    }
}
