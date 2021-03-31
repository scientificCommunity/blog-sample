package org.baichuan.example.vertx.transformer;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/3/31
 */
public abstract class AbstractTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!toBeTransformedClassNames().contains(toClassName(className))) {
            return null;
        }

        try {
            CtClass ctClass = getCtClass(loader, classfileBuffer);
            for (CtMethod method : ctClass.getDeclaredMethods()) {
                if (!toBeTransformedMethodNames().isEmpty() && !toBeTransformedMethodNames().contains(method.getName())) {
                    continue;
                }
                final int modifiers = method.getModifiers();
                if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                    continue;
                }

                CtClass[] parameterTypes = method.getParameterTypes();
                StringBuilder insertBeforeCode = new StringBuilder(insertBefore());
                StringBuilder insertAfterCode = new StringBuilder();
                System.out.println("=================start to transform====================");
                for (int i = 0; i < parameterTypes.length; i++) {
                    final String paramTypeName = parameterTypes[i].getName();
                    if (toBeWrappedParam().containsKey(paramTypeName)) {
                        insertBeforeCode.append(transformSpecificParamCodes(i + 1, paramTypeName));
                        insertAfterCode.append(insertAfter(i + 1));
                    }
                }
                if (insertBeforeCode.length() > 0) {
                    try {
                        method.insertBefore(insertBeforeCode.toString());
                        method.insertAfter(insertAfterCode.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            addMethodToCtClass(ctClass);

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


    /**
     * implementation by subclass
     *
     * @return toBeTransformedClassNames
     */
    protected abstract HashSet<String> toBeTransformedClassNames();


    /**
     * implementation by subclass
     *
     * @return toBeTransformedMethodNames
     */
    protected HashSet<String> toBeTransformedMethodNames() {
        return new HashSet<>();
    }

    /**
     * a
     *
     * @return insertBeforeCodes
     */
    protected String insertBefore() {
        return "";
    }

    /**
     * a
     *
     * @param paramIndex the param which was passed to this method
     * @return insertBeforeCodes
     */
    protected String insertAfter(int paramIndex) {
        return "";
    }

    /**
     * a
     *
     * @param paramIndex the index of param in method
     * @return transformed code
     */
    protected String transformSpecificParamCodes(int paramIndex, String paramTypeName) {
        return "";
    }

    /**
     * add some field to the specific class
     *
     * @param className the class that need to be added field
     */
    protected void addFieldToSpecificClass(String className) {
        // For subclasses: do nothing by default.
    }

    /**
     * add some field to the specific class
     *
     * @param ctClass the Class are represented by CtClass objects.
     */
    protected void addMethodToCtClass(CtClass ctClass) {
        // For subclasses: do nothing by default.
    }

    /**
     * @return wrapped param type
     */
    protected Map<String, String> toBeWrappedParam() {
        // For subclasses: do nothing by default.
        return new HashMap<>(0);
    }
}
