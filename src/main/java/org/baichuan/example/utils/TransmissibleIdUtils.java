package org.baichuan.example.utils;

import org.baichuan.example.vertx.transformer.TransmissibleThreadLocal;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/30
 */
public class TransmissibleIdUtils {
    private static final ThreadLocal<String> GLOBAL_ID_HOLDER = new TransmissibleThreadLocal<>();

    public static void put(String globalId) {
        GLOBAL_ID_HOLDER.set(globalId);
    }

    public static void put(Integer globalId) {
        GLOBAL_ID_HOLDER.set(String.valueOf(globalId));
    }

    public static String get() {
        return GLOBAL_ID_HOLDER.get();
    }

    public static String getAndClear() {
        String s = GLOBAL_ID_HOLDER.get();
        clear();
        return s;
    }

    public static void clear() {
        GLOBAL_ID_HOLDER.remove();
    }
}
