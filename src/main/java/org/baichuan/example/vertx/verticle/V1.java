package org.baichuan.example.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.baichuan.example.vertx.verticle.consumer.C1;

/**
 * @author: tk (rivers.boat.snow at gmail dot com)
 * @date: 2021/3/30
 */
public class V1 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        //这里会为C1绑定一个thread
        vertx.eventBus().consumer(C1.NAME, new C1(vertx));

        startPromise.complete();
    }
}
