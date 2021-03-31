package org.baichuan.example.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.baichuan.example.vertx.verticle.consumer.C1;
import org.baichuan.example.vertx.verticle.consumer.C3;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/30
 */
public class V3 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().consumer(C3.NAME, new C3(vertx));

        startPromise.complete();
    }
}
