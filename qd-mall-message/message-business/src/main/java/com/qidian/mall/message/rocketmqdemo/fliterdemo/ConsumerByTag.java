package com.qidian.mall.message.rocketmqdemo.fliterdemo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者 : 过滤消费消息 ---- 通过 Tag 来获取不同的消息
 * @author bin
 */
public class ConsumerByTag {


    public static void main(String[] args) throws Exception {
        // 1、创建消费者Consumer，制定消费者组名（push broker 去推给消费者，还有拉的消费者pull）
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        // 2、指定nameserver地址
        consumer.setNamesrvAddr("192.168.0.110:9876;192.168.0.111:9876");
        // 3、订阅主题Topic和tag  消费多个tag：  Tag1 ｜｜ Tag2 ｜｜ Tag3  直接写 * 消费所有的tag
        consumer.subscribe("FilterTopic","Tag1 || Tag2");

        // 4、设置回调函数监听器，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            // 接受消息内容,返回结果
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt m:list) {
                    System.out.println("消费消息："+new String(m.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 5、启动消费者consumer
        consumer.start();
        System.out.println("消费者启动");
    }
}
