package org.baichuan.example.vertx.transformer;

import java.util.HashSet;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/26
 */
public class ThreadLocalHolder {
    private static final HashSet<ThreadLocal<?>> THREAD_LOCAL_HOLDER = new HashSet<>();

    public static void put(ThreadLocal<?> threadLocal) {
        THREAD_LOCAL_HOLDER.add(threadLocal);
    }

    public static HashSet<ThreadLocal<?>> get() {
        return THREAD_LOCAL_HOLDER;
    }
}
