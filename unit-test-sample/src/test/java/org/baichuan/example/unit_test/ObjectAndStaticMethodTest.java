package org.baichuan.example.unit_test;

import org.baichuan.example.unit_test.issue_log.ObjectMethod;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/12
 */
public class ObjectAndStaticMethodTest {
    @Test
    public void test() {
        ObjectMethod spy = Mockito.spy(ObjectMethod.INSTANCE);

        Mockito.doAnswer(invocation -> {
            System.out.println(123);
            return "";
        }).when(spy).doSomething();
        spy.doSomething();
    }
}
