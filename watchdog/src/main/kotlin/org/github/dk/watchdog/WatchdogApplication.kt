package org.github.dk.watchdog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class WatchdogApplication

fun main(args: Array<String>) {
    runApplication<WatchdogApplication>(*args)
}
