package transformer;

/**
 * @author: tk (rivers.boat.snow at gmail dot com)
 * @date: 2021/3/26
 */
public class TransmissibleThreadLocal<T> extends InheritableThreadLocal<T> {

    @Override
    public void set(T value) {
        super.set(value);
        ThreadLocalHolder.put(this);
    }
}
