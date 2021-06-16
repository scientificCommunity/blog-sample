package transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * @author: tk (rivers.boat.snow at gmail dot com)
 * @date: 2021/3/26
 */
public class Transformer {
    private static boolean transformed = false;

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new ThreadPoolExecutorTransformer(), true);
        transformed = true;
    }

    private static String toClassName(final String classFile) {
        return classFile.replace('/', '.');
    }

    private static CtClass getCtClass(ClassLoader loader, byte[] classfileBuffer) throws IOException {
        final ClassPool classPool = new ClassPool(true);
        if (loader == null) {
            classPool.appendClassPath(new LoaderClassPath(ClassLoader.getSystemClassLoader()));
        } else {
            classPool.appendClassPath(new LoaderClassPath(loader));
        }

        final CtClass clazz = classPool.makeClass(new ByteArrayInputStream(classfileBuffer), false);
        clazz.defrost();

        return clazz;
    }

    public static boolean isTransformed() {
        return transformed;
    }
}
