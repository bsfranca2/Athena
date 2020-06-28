package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_privilege")
data class Privilege(
        @Column(unique = true, nullable = false)
        val name: String = "",
        @ManyToMany(mappedBy = "privileges", cascade = [CascadeType.DETACH])
        val roles: MutableList<Role> = mutableListOf()
): BaseEntity()
