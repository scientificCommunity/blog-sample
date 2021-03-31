package org.baichuan.example.vertx.transformer;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/26
 */
public class Transformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!"io.netty.util.concurrent.SingleThreadEventExecutor".equals(toClassName(className))) {
            return null;
        }

        try {
            CtClass ctClass = getCtClass(loader, classfileBuffer);
            for (CtMethod method : ctClass.getDeclaredMethods()) {
                if (!"execute".equals(method.getName())) {
                    continue;
                }
                final int modifiers = method.getModifiers();
                if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                    continue;
                }

                CtClass[] parameterTypes = method.getParameterTypes();
                StringBuilder insertCode = new StringBuilder();
                System.out.println("=================start to transform====================");
                for (int i = 0; i < parameterTypes.length; i++) {
                    final String paramTypeName = parameterTypes[i].getName();
                    if ("java.lang.Runnable".equals(paramTypeName)) {
                        String code = String.format(
                                // decorate to TTL wrapper,
                                // and then set AutoWrapper attachment/Tag
                                "$%d=new org.baichuan.example.vertx.transformer.WrappedRunnable($%d);",
                                i + 1, i + 1);
                        insertCode.append(code);
                    }
                }
                if (insertCode.length() > 0) {
                    try {
                        method.insertBefore(insertCode.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return ctClass.toBytecode();
        } catch (IOException | NotFoundException | CannotCompileException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CtClass getCtClass(ClassLoader loader, byte[] classfileBuffer) throws IOException {
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

    private static String toClassName(final String classFile) {
        return classFile.replace('/', '.');
    }
}
