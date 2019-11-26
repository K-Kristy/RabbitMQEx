package kuplkris.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static kuplkris.rabbit.ConstantsUtils.CALLBACK_QUEUE;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_1;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_2;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_FOR_TWO_LISTENERS;
import static kuplkris.rabbit.ConstantsUtils.DIRECT_QUEUE_NAME;
import static kuplkris.rabbit.ConstantsUtils.FANOUT_QUEUE_1;
import static kuplkris.rabbit.ConstantsUtils.FANOUT_QUEUE_2;
import static kuplkris.rabbit.ConstantsUtils.TOPIC_QUEUE_1;
import static kuplkris.rabbit.ConstantsUtils.TOPIC_QUEUE_2;

@EnableRabbit
@Component
public class RabbitMqListener {

    private static final Logger LOGGER = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = DIRECT_QUEUE_NAME)
    public void listenerForDirectQueue(String message) {
        LOGGER.info("Received from queue " + DIRECT_QUEUE_NAME + " : " + message);
    }


    @RabbitListener(queues = DIRECT_QUEUE_FOR_TWO_LISTENERS)
    public void listenerForDirectQueue1(String message) throws InterruptedException {
        LOGGER.info("listener_1 : " + message);
        Thread.sleep(1000);
    }

    @RabbitListener(queues = DIRECT_QUEUE_FOR_TWO_LISTENERS)
    public void listenerForDirectQueue2(String message) throws InterruptedException {
        LOGGER.info("listener 2" + " : " + message);
        Thread.sleep(1000);
    }

    @RabbitListener(queues = FANOUT_QUEUE_1)
    public void fanoutListener1(String message) {
        LOGGER.info("accepted on listener 1 : " + message);
    }

    @RabbitListener(queues = FANOUT_QUEUE_2)
    public void fanoutListener2(String message) {
        LOGGER.info("accepted on listener 2 : " + message);
    }

    @RabbitListener(queues = DIRECT_QUEUE_1)
    public void routingKeyWorker1(String message) {
        LOGGER.info("accepted on routingKeyWorker1 : " + message);
    }

    @RabbitListener(queues = DIRECT_QUEUE_2)
    public void routingKeyWorker2(String message) {
        LOGGER.info("accepted on routingKeyWorker2 : " + message);
    }

    @RabbitListener(queues = TOPIC_QUEUE_1)
    public void topicWorker1(String message) {
        LOGGER.info("accepted on topic worker 1 : " + message);
    }

    @RabbitListener(queues = TOPIC_QUEUE_2)
    public void topicWorker2(String message) {
        LOGGER.info("accepted on topic worker 2 : " + message);
    }

    @RabbitListener(queues = CALLBACK_QUEUE)
    public String callbackWorker(String message) throws InterruptedException {
        LOGGER.info("received on callbackWorker : " + message);
        Thread.sleep(3000);
        return "response : " + message;
    }

}
