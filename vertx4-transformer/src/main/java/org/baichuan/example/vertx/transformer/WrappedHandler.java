package org.baichuan.example.vertx.transformer;

import io.vertx.core.Handler;

import java.util.HashMap;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/26
 * @see io.vertx.core.Handler
 */
public class WrappedHandler<T, V> implements Handler<T> {
    private final Handler<T> originalHandler;
    private final HashMap<ThreadLocal<Object>, Object> threadLocalValues = new HashMap<>();

    public WrappedHandler(Handler<T> originalHandler) {

        System.out.println("==========start to construct wrapped Handler==============");
        for (ThreadLocal<?> threadLocal : ThreadLocalHolder.get()) {
            threadLocalValues.put((ThreadLocal<Object>) threadLocal, threadLocal.get());
        }
        this.originalHandler = originalHandler;
    }

    @Override
    public void handle(T event) {
        threadLocalValues.forEach(ThreadLocal::set);
        originalHandler.handle(event);
        threadLocalValues.forEach((threadLocal, o) -> threadLocal.remove());
    }
}
