package org.github.dk.usmp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/**
 *统一安全管理平台启动入口
 *
 * @author D.K
 * @version V1.0.0
 * @since Graalvm 21
 */
@SpringBootApplication
@EnableDiscoveryClient
class UsmpApplication

fun main(args: Array<String>) {
    runApplication<UsmpApplication>(*args)
}