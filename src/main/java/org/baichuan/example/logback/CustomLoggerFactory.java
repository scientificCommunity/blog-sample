package org.baichuan.example.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @see ContextInitializer#autoConfig()
 * @see ContextInitializer#findURLOfDefaultConfigurationFile(boolean)
 * @see ch.qos.logback.classic.PatternLayout
 * @author: tk (soulmate.tangk at gmail dot com)
 * @date: 2021/4/4
 */
public class CustomLoggerFactory {
    public static Logger getLogger(Class<?> clazz){
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String string) {
        LoggerContext lc = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern("%yellow(%d{yyyy-MM-dd HH:mm:ss}) %blue([%t]) - %green(%-15(%M)) - %highlight(%-5level) -%boldMagenta(%c{1}) - %cyan(%msg%n)");

        ple.setContext(lc);
        ple.start();

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setEncoder(ple);
        consoleAppender.setContext(lc);
        consoleAppender.start();

        Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(string);
        logger.setLevel(Level.DEBUG);
        logger.setAdditive(false); /* set to true if root should log too */
        logger.addAppender(consoleAppender);
        logger.setLevel(Level.DEBUG);
        logger.setAdditive(false);
        return logger;
    }
}
