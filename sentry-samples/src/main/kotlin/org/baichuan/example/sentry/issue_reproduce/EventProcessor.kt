package org.baichuan.example.sentry.issue_reproduce

import io.sentry.EventProcessor
import io.sentry.SentryEvent

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/11/18
 */
class EventProcessor : EventProcessor {
    override fun process(event: SentryEvent, hint: Any?): SentryEvent? {
        println("===========================================${event.eventId}===========================================")
        return super.process(event, hint)
    }
}