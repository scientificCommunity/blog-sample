package org.baichuan.example.vertx.test;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.baichuan.example.utils.TransmissibleIdUtils;
import org.baichuan.example.vertx.Utils;
import org.baichuan.example.vertx.verticle.DeployHandler;
import org.baichuan.example.vertx.verticle.V2;
import org.baichuan.example.vertx.verticle.V3;
import org.baichuan.example.vertx.verticle.consumer.C2;
import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/30
 */
@Slf4j
public class TestHandlerTransmissibility {
    static final Semaphore SEMAPHORE = new Semaphore(1);

    static final String MESSAGE_1 = "message1";

    static int id = 0;

    @Test
    public void test1() {
        Utils.initSemaphore(2);
        Vertx vertx = Utils.initVertx();
        vertx.deployVerticle(new V2(), new DeployHandler());
        vertx.deployVerticle(new V3(), new DeployHandler());

        Utils.acquire();
        TransmissibleIdUtils.put(id++);
        vertx.eventBus().request(C2.NAME, MESSAGE_1, event -> {
            log.info("global id in callback is {}", TransmissibleIdUtils.get());
            log.info("received reply msg 【{}】 from {}", event.result().body(), C2.NAME);
        });
    }
}
