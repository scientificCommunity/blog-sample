package org.baichuan.example.vertx.verticle.consumer;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: tk (rivers.boat.snow at gmail dot com)
 * @date: 2021/3/30
 */
@Slf4j
public class C1 implements Handler<Message<Object>> {
    public static final String NAME = "consumer1";
    public static final String REPLY_MSG = "C1_REPLY";
    private final Vertx vertx;

    public C1(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(Message<Object> event) {
        log.info("consumer 【{}】received msg:{}", NAME, event.body());
        event.reply(REPLY_MSG);
    }
}
