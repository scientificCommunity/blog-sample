package org.baichuan.example.vertx.verticle;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.baichuan.example.vertx.Utils;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/30
 */
public class DeployHandler implements Handler<AsyncResult<String>> {

    @Override
    public void handle(AsyncResult<String> event) {
        Utils.release();
    }
}
