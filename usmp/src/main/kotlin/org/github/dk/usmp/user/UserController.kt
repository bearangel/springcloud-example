package org.github.dk.usmp.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * 用户控制器
 *
 * @author D.K
 * @version V1.0.0
 * @since Graalvm 21
 */
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userRepository: UserRepository;

    @PostMapping("/save")
    fun save(@RequestBody user: User): User {
        if (user.gid == null) {
            user.gid = UUID.randomUUID().toString()
            user.createTime = System.currentTimeMillis()
            user.updateTime = user.createTime
        }
        return userRepository.save(user)
    }

    @GetMapping("/findByGid/{gid}")
    fun findByGid(@PathVariable gid: String): User {
        return userRepository.findById(gid).orElse(User())
    }
}