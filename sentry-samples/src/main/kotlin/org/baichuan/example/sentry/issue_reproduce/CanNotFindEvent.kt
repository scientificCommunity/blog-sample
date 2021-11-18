package org.baichuan.example.sentry.issue_reproduce

import io.sentry.Sentry
import io.sentry.SentryOptions


/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/11/18
 */
class CanNotFindEvent {
}

fun main() {
    Sentry.init { options: SentryOptions ->
        options.dsn = "your dsn"
        options.tracesSampleRate = 1.0
        options.setDebug(true)
        options.environment = "local"
        options.addEventProcessor(EventProcessor())
    }
    try {
        /**
         * Get the eventId from the console,
         * then go to the sentry sass and search for its corresponding event by this eventId,
         * you will find that you can't search for the corresponding event,
         * but the corresponding event really exists in the sentry sass
         */
        throw Exception("This is a test for reproduce that event can not be found by event id")
    } catch (e: Exception) {
        Sentry.captureException(e)
    }
}