package transformer;

import java.util.HashMap;

/**
 * @author: tk (rivers.boat.snow at gmail dot com)
 * @date: 2021/3/30
 * @see java.util.concurrent.ThreadPoolExecutor#execute(Runnable)
 * @see Runnable
 */
public class WrappedRunnable implements Runnable {
    private final HashMap<ThreadLocal<Object>, Object> threadLocalValues = new HashMap<>();
    private final Runnable originalRunnable;

    public WrappedRunnable(Runnable runnable) {
        System.out.println("==========start to construct wrapped Runnable==============");
        for (ThreadLocal<?> threadLocal : ThreadLocalHolder.get()) {
            threadLocalValues.put((ThreadLocal<Object>) threadLocal, threadLocal.get());
        }
        originalRunnable = runnable;
    }

    @Override
    public void run() {
        threadLocalValues.forEach(ThreadLocal::set);
        originalRunnable.run();
        threadLocalValues.forEach((threadLocal, o) -> threadLocal.remove());
    }
}
