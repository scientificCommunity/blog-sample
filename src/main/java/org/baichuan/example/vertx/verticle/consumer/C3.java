package org.baichuan.example.vertx.verticle.consumer;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import org.baichuan.example.utils.TransmissibleIdUtils;
import org.baichuan.example.vertx.Utils;

/**
 * @author: tk (rivers.boat.snow at gmail dot com)
 * @date: 2021/3/30
 */
@Slf4j
public class C3 implements Handler<Message<Object>> {
    public static final String NAME = "consumer3";
    public static final String REPLY_MSG = "C3_REPLY";

    private final Vertx vertx;

    public C3(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(Message<Object> event) {
        log.info("{}global id in handler {} is {}", NAME, Utils.SYMBOL, TransmissibleIdUtils.getAndClear());
        log.info("consumer 【{}】received msg:{}", NAME, event.body());
        event.reply(REPLY_MSG);
    }
}
