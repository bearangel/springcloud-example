package org.github.dk.usmp.auth

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 *认证模块
 *
 * @author D.K
 * @version V1.0.0
 * @since Graalvm 21
 */
@RequestMapping("/auth")
@RestController
class AuthController {

    @PostMapping("/login")
    fun login(@RequestParam userName: String, @RequestParam password: String): String {
        return ("${userName}登录系统")
    }

}