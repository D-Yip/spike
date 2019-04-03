package com.ysj.spike.rabbitmq.sender;

import com.ysj.spike.rabbitmq.config.MQConfig;
import com.ysj.spike.rabbitmq.message.SpikeMessage;
import com.ysj.spike.utils.TransformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpikeSender {

    private static final Logger log = LoggerFactory.getLogger(SpikeSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendSpikeMessage(SpikeMessage spikeMessage) {
        String msg = TransformUtil.beanToString(spikeMessage);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.SPIKE_QUEUE,msg);
    }
}
