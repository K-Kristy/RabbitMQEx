package kuplkris.rabbit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private RabbitExampleManager rabbitExampleManager;

    @RequestMapping("/direct")
    @ResponseBody
    String directOption() {
        LOGGER.info("Direct option");
        rabbitExampleManager.directOptionExample();
        return "Putting message to queue was successfully";
    }


    @RequestMapping("/directForTwoListeners")
    @ResponseBody
    String directOptionForTwoListeners() {
        LOGGER.info("Direct option");
        rabbitExampleManager.directOptionTwoListenersExample();
        return "Putting message to queue was successfully";
    }


    @RequestMapping("/directForCertainExchanger")
    @ResponseBody
    String directOptionForCertainExchanger() {
        LOGGER.info("Direct option");
        rabbitExampleManager.directOptionTwoListenersExample();
        return "Putting message to queue was successfully";
    }


    @RequestMapping("/directUsingRoutingKey")
    @ResponseBody
    String directOptionUsingRoutingKey() {
        LOGGER.info("Direct with RoutingKey option");
        rabbitExampleManager.directOptionUsingRoutingKeyExample();
        return "Putting message to queue was successfully";
    }


    @RequestMapping("/fanout")
    @ResponseBody
    String fanoutOption() {
        LOGGER.info("Fanout option");
        rabbitExampleManager.fanoutOptionExample();
        return "Putting message to queue was successfully";
    }

    @RequestMapping("/topic")
    @ResponseBody
    String topicOption() {
        LOGGER.info("Topic option");
        rabbitExampleManager.topicOptionExample();
        return "Putting message to queue was successfully";
    }

    @RequestMapping("/callback")
    @ResponseBody
    String callbackOption() {
        LOGGER.info("Callback option");
        rabbitExampleManager.callbackExample();
        return "Putting message to queue was successfully";
    }
}
