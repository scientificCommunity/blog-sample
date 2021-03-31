package org.baichuan.example.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.baichuan.example.vertx.verticle.consumer.C1;
import org.baichuan.example.vertx.verticle.consumer.C2;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/30
 */
public class V2 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.eventBus().consumer(C2.NAME, new C2(vertx));

        startPromise.complete();
    }
}
