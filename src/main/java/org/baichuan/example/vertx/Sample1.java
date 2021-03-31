package org.baichuan.example.vertx;

import io.vertx.core.Vertx;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.baichuan.example.vertx.verticle.DeployHandler;
import org.baichuan.example.vertx.verticle.V1;
import org.baichuan.example.vertx.verticle.V2;
import org.baichuan.example.vertx.verticle.V3;
import org.baichuan.example.vertx.verticle.consumer.C1;
import org.baichuan.example.vertx.verticle.consumer.C2;

import java.util.concurrent.Semaphore;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/30
 */
@Slf4j
public class Sample1 {

    static final Semaphore SEMAPHORE = new Semaphore(1);

    static final String MESSAGE_1 = "message1";

    static int id = 0;

    public static void main(String[] args) {
        //test1();
        test2();
    }

    @SneakyThrows
    public static void sendMsgFromMain() {
        Vertx vertx = Utils.initVertx();

        GlobalIdUtils.put(id++);
        SEMAPHORE.acquire();

        vertx.deployVerticle(new V1(), event -> {
            log.info("global id in deploy is {}", GlobalIdUtils.getAndClear());
            log.info("deploy completed");
            SEMAPHORE.release();
        });
        Utils.startTimer();

        //等待vertx.deployVerticle完成
        SEMAPHORE.acquire();

        //设置一个新的值
        GlobalIdUtils.put(id++);
        vertx.eventBus().request(C1.NAME, MESSAGE_1, event -> {
            //由于是在main里发送消息，不属于eventLoop，回调时会从event loop group里拿一个线程出来执行
            log.info("global id in callback is {}", GlobalIdUtils.get());
            log.info("received reply msg 【{}】 from {}", event.result().body(), C1.NAME);
        });
    }

    public static void sendMsgFromConsumer() {
        Utils.initSemaphore(2);
        Vertx vertx = Utils.initVertx();
        vertx.deployVerticle(new V2(), new DeployHandler());
        vertx.deployVerticle(new V3(), new DeployHandler());

        Utils.acquire();
        GlobalIdUtils.put(id++);
        vertx.eventBus().request(C2.NAME, MESSAGE_1, event -> {
            log.info("global id in callback is {}", GlobalIdUtils.get());
            log.info("received reply msg 【{}】 from {}", event.result().body(), C2.NAME);
        });
    }
}
