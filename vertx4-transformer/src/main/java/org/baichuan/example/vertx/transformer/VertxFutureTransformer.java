package org.baichuan.example.vertx.transformer;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/4/8
 */
public class VertxFutureTransformer extends AbstractTransformer {
    private static final HashSet<String> TO_BE_TRANSFORMED_CLASS_NAMES = new HashSet<>();
    private static final Map<String, String> TO_BE_WRAPPED_PARAM_NAMES = new HashMap<>();

    static {
        TO_BE_TRANSFORMED_CLASS_NAMES.add("io.vertx.core.impl.future.FutureImpl");
        TO_BE_WRAPPED_PARAM_NAMES.put("io.vertx.core.Handler", "org.baichuan.example.vertx.transformer.WrappedHandler");
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!TO_BE_TRANSFORMED_CLASS_NAMES.contains(toClassName(className))) {
            return null;
        }

        try {
            CtClass ctClass = getCtClass(loader, classfileBuffer);
            for (CtMethod method : ctClass.getDeclaredMethods()) {
                final int modifiers = method.getModifiers();
                if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)|| Modifier.isInterface(modifiers)) {
                    continue;
                }

                CtClass[] parameterTypes = method.getParameterTypes();
                StringBuilder insertCode = new StringBuilder();
                System.out.println("=================start to transform====================");
                for (int i = 0; i < parameterTypes.length; i++) {
                    final String paramTypeName = parameterTypes[i].getName();
                    if (TO_BE_WRAPPED_PARAM_NAMES.containsKey(paramTypeName)) {
                        String code = String.format(
                                "$%d=new %s($%d);",
                                i + 1, TO_BE_WRAPPED_PARAM_NAMES.get(paramTypeName), i + 1);
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

    @Override
    protected HashSet<String> toBeTransformedClassNames() {
        return TO_BE_TRANSFORMED_CLASS_NAMES;
    }

    @Override
    protected Map<String, String> toBeWrappedParam() {
        return TO_BE_WRAPPED_PARAM_NAMES;
    }
}
