package org.github.dk.usmp.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 用户数据库操作
 *
 * @author D.K
 * @version V1.0.0
 * @since Graalvm 21
 */
@Repository
interface UserRepository: JpaRepository<User, String>{
}