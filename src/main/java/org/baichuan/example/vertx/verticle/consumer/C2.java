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
public class C2 implements Handler<Message<Object>> {
    public static final String NAME = "consumer2";
    public static final String REPLY_MSG = "C2_REPLY";

    static final String DELIVERY_MSG = "msg c2 delivered";

    private final Vertx vertx;

    public C2(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(Message<Object> event) {
        log.info("{} global id in handler is {}", Utils.SYMBOL, TransmissibleIdUtils.get());

        log.info("consumer 【{}】received msg:{}", NAME, event.body());
        vertx.eventBus().request(C3.NAME, DELIVERY_MSG, reply -> {
            log.info("{}global id in callback is {}", Utils.SYMBOL, TransmissibleIdUtils.get());
            log.info("received reply msg 【{}】 in {}", reply.result().body(), C1.NAME);
        });
        event.reply(REPLY_MSG);
    }
}
