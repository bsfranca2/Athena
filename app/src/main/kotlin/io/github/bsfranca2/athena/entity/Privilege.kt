package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_privilege")
data class Privilege(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "INTEGER", nullable = false, unique = true)
        val id: Long = -1,
        @Column(unique = true, nullable = false)
        val name: String = "",
        @ManyToMany(mappedBy = "privileges", cascade = [CascadeType.DETACH])
        val roles: MutableList<Role> = mutableListOf()
)
