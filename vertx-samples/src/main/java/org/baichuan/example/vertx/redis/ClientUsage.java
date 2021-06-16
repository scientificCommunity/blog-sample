package org.baichuan.example.vertx.redis;

import io.vertx.core.Vertx;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.Request;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/6/16
 */
public final class ClientUsage {

    /**
     * 直接使用redisConnection
     */
    public static void redisConnection() {
        Redis.createClient(
                Vertx.vertx(),
                // 格式：redis://[username:password@][host][:port][/database]
                "redis://:localhost:6379/0")
                .connect()
                .onSuccess(conn -> {
                    System.out.println("redis连接建立成功");

                    //构建redis set指令对应的请求
                    Request req = Request.cmd(Command.SET);
                    //参数格式与redis指令对应的参数保持一致
                    req.arg("a");
                    req.arg(123);
                    conn.send(req, event -> {
                        if (event.succeeded()) {
                            //执行成功，拿到执行结果
                            System.out.println("执行成功, result:" + event.result());
                        } else {
                            System.out.println("执行失败, cause:" + event.cause());
                        }
                    });
                });
    }

    public static void redisApi() {
        Redis client = Redis.createClient(
                Vertx.vertx(),
                // 格式：redis://[username:password@][host][:port][/database]
                "redis://:localhost:6379/0")
                .connect(event -> {

                });
        RedisAPI api = RedisAPI.api(client);
        api.get("a").onSuccess(event -> {
            System.out.println("the val of key a is " + event.toString());
        });
    }
}
