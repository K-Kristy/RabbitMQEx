package kuplkris.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kuplkris.rabbit.ConstantsUtils.CALLBACK_QUEUE;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_EXCHANGE;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_FOR_TWO_LISTENERS;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_NAME;
import static kuplkris.rabbit.ConstantsUtils.FANOUT_EXCHANGE;
import static kuplkris.rabbit.ConstantsUtils.TOPIC_EXCHANGE;

@Component
public class RabbitExampleManager {

    private static final Logger LOGGER = Logger.getLogger(RabbitExampleManager.class);


    @Autowired
    private AmqpTemplate template;

    @Autowired
    private RabbitTemplate fanoutRabbitTemplate;

    @Autowired
    private RabbitTemplate directRabbitTemplate;

    @Autowired
    private RabbitTemplate topicRabbitTemplate;


    public void directOptionExample() {
        LOGGER.info("Put message to " + DIRECT_QUEUE_NAME + " queue");
        template.convertAndSend(DIRECT_QUEUE_NAME, "Direct message");
    }


    public void directOptionTwoListenersExample() {
        LOGGER.info("Put messages for two listeners");
        for (int i = 0; i < 10; i++) {
            template.convertAndSend(DIRECT_QUEUE_FOR_TWO_LISTENERS, "Message " + i);
        }
    }

    //for all registered to exchange queues
    public void fanoutOptionExample() {
        LOGGER.info("Put message to fanoutExchange");
        fanoutRabbitTemplate.setExchange(FANOUT_EXCHANGE);
        fanoutRabbitTemplate.convertAndSend("Fanout message");
    }

    public void directOptionUsingRoutingKeyExample() {
        LOGGER.info("Put message with Info RoutingKey");
        directRabbitTemplate.setExchange(DIRECT_EXCHANGE);
        directRabbitTemplate.convertAndSend("info", "Info");
        directRabbitTemplate.convertAndSend("warning", "Warning");
        directRabbitTemplate.convertAndSend("error", "Error");
    }

    public void topicOptionExample() {
        LOGGER.info("Put message by mask");
        topicRabbitTemplate.setExchange(TOPIC_EXCHANGE);
        topicRabbitTemplate.convertAndSend("sweet.orange.v", "orange");
        topicRabbitTemplate.convertAndSend("one.1.rabbit", "rabbit");
        topicRabbitTemplate.convertAndSend("lazy", "lazy");
        topicRabbitTemplate.convertAndSend("111lazy333", "111lazy333");
    }

    public void callbackExample() {
        LOGGER.info("Put message for callback");
        String response = (String) directRabbitTemplate.convertSendAndReceive(CALLBACK_QUEUE, "callback");
        LOGGER.info("RESPONSE " + response);
    }
}
