package com.test.quarkus

import io.quarkus.runtime.StartupEvent
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject

@ApplicationScoped
open class RabbitConfiguration {
    private val logger = LoggerFactory.getLogger(RabbitConfiguration::class.java)

    @Inject
    lateinit var rabbitStringProcessor: RabbitStringProcessor

    open fun configureConsumer(@Observes ev: StartupEvent) {
        logger.info("Configuring RabbitMQ consuming...")

        val queue = Queue("string-input")

        val connectionFactory = CachingConnectionFactory("localhost")
        connectionFactory.username = "guest"
        connectionFactory.setPassword("guest")

        val admin = RabbitAdmin(connectionFactory)
        admin.declareQueue(queue)

        val container = SimpleMessageListenerContainer(connectionFactory)
        container.addQueues(queue)
        container.setMessageListener(rabbitStringProcessor)
        container.afterPropertiesSet()
        container.start()

        logger.info("Finished RabbitMQ consuming configuration")
    }
}