package org.baichuan.example.vertx.test;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import org.baichuan.example.utils.TransmissibleIdUtils;
import org.baichuan.example.vertx.Utils;
import org.baichuan.example.vertx.transformer.Transformer;
import org.baichuan.example.vertx.transformer.TransmissibleThreadLocal;
import org.baichuan.example.vertx.verticle.DeployHandler;
import org.baichuan.example.vertx.verticle.V3;
import org.baichuan.example.vertx.verticle.consumer.C2;
import org.baichuan.example.vertx.verticle.consumer.C3;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/30
 */
@Slf4j
public class TestAsyncIoCallback {
    static final String MESSAGE_1 = "message1";
    static final String transmittedData = "transmitted_data";
    static final String inheritedData = "inherited_data";

    @Test
    public void test1() {
        Utils.initSemaphore(1);
        Vertx vertx = Utils.initVertx();
        vertx.deployVerticle(new V3(), new DeployHandler());

        TransmissibleIdUtils.put(transmittedData);

        Utils.acquire();
        //here will create a thread for C3
        //so InheritableThreadLocal can work
        vertx.eventBus().request(C3.NAME, MESSAGE_1, event -> {
            log.info("global id in callback is {}", TransmissibleIdUtils.getAndClear());
            log.info("received reply msg 【{}】 from {}", event.result().body(), C2.NAME);
        });

        vertx.eventBus().request(C3.NAME, MESSAGE_1, event -> {
            log.info("global id in callback is {}", TransmissibleIdUtils.getAndClear());
            log.info("received reply msg 【{}】 from {}", event.result().body(), C2.NAME);
        });
        Utils.acquire();
        Utils.acquire();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new TestAsyncIoCallback().testIoCallback();
        new TestAsyncIoCallback().test1();
    }

    @Test
    public void testIoCallback() throws ExecutionException, InterruptedException {
        final TransmissibleThreadLocal<String> transmittableThreadLocal = new TransmissibleThreadLocal<>();
        final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        final Vertx vertx = Vertx.vertx();
        //here will bind eventLoop to client and create a new Thread for eventLoop
        final WebClient client = WebClient.create(vertx);

        //set value after eventLoop thread was created
        transmittableThreadLocal.set(transmittedData);
        inheritableThreadLocal.set(inheritedData);

        final Future<HttpResponse<Buffer>> future = client.get(80, "baidu.com", "/")
                .send()
                .onSuccess(response -> {
                    log.info("===================callback=====================");

                    if (Transformer.isTransformed()) {
                        log.info("Test **WITH** Transformed");
                        assertEquals(transmittedData, transmittableThreadLocal.get());
                    } else {
                        log.info("Test WITHOUT Transformed");
                        assertNull(transmittableThreadLocal.get());
                    }

                    log.info("===================callback=====================");
                });

        // block and wait to finish
        future.toCompletionStage().toCompletableFuture().get();
    }
}
