package com.ysj.spike.rabbitmq;

import com.ysj.spike.rabbitmq.config.MQConfig;
import com.ysj.spike.rabbitmq.message.SpikeMessage;
import com.ysj.spike.utils.TransformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {

    private static final Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message) {

        String msg = TransformUtil.beanToString(message);

        amqpTemplate.convertAndSend(MQConfig.QUEUE,msg);

        log.info("MQ Send Message: " + msg);
    }

    public void sendTopic(Object message) {
        String msg = TransformUtil.beanToString(message);
        log.info("MQ Send Message: " + msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key1",msg+"1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key2",msg+"2");
    }

    public void sendFanout(Object message) {
        String msg = TransformUtil.beanToString(message);
        log.info("MQ Send Message: " + msg);
        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",msg);
    }

    public void sendHeader(Object message) {
        String msg = TransformUtil.beanToString(message);
        log.info("MQ Send Message: " + msg);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1","value1");
        properties.setHeader("header2","value2");
        Message obj = new Message(msg.getBytes(),properties);
        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE,"",obj);
    }
}
