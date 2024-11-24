package org.github.dk.usmp.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "T_USER")
open class User {
    @Id
    @Column(name = "gid", nullable = false, length = Integer.MAX_VALUE)
    open var gid: String? = null

    @Column(name = "user_name", length = Integer.MAX_VALUE)
    open var userName: String? = null

    @Column(name = "display_name", length = Integer.MAX_VALUE)
    open var displayName: String? = null

    @Column(name = "password", length = Integer.MAX_VALUE)
    open var password: String? = null

    @Column(name = "create_time")
    open var createTime: Long? = null

    @Column(name = "update_time")
    open var updateTime: Long? = null
}