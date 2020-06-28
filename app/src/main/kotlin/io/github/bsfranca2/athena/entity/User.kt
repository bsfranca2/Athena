package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_user")
data class User(
        @Column(unique = true, nullable = false)
        val email: String = "",
        @Column(nullable = false)
        val password: String = "",
        @Column(nullable = false)
        val active: Boolean = false,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "ath_users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
        val roles: MutableList<Role> = mutableListOf()
): BaseEntity()