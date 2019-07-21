package com.test.quarkus

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import java.nio.charset.Charset
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
open class RabbitStringProcessor: MessageListener {
    private val logger = LoggerFactory.getLogger(RabbitStringProcessor::class.java)

    override fun onMessage(message: Message) {
        val content = message.body.toString(Charset.defaultCharset())
        logger.info("Received message from AMQP queue: $content")
    }

}