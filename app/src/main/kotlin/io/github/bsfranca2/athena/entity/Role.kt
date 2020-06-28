package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_role")
data class Role(
        @Column(unique = true, nullable = false)
        val name: String,
        @ManyToMany(mappedBy = "roles", cascade = [CascadeType.DETACH])
        val users: MutableList<User> = mutableListOf(),
        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinTable(name = "ath_roles_privileges",
                joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")])
        val privileges: MutableList<Privilege> = mutableListOf()
) : BaseEntity()