package io.github.bsfranca2.athena.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*

@Entity
@Table(name = "ath_user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        @Column(unique = true, nullable = false)
        val email: String = "",
        @Column(nullable = false)
        val password: String = "",
        @Column(nullable = false)
        val active: Boolean = false,
        @ManyToMany(cascade = [CascadeType.DETACH])
        @LazyCollection(LazyCollectionOption.FALSE)
        @JoinTable(name = "ath_users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
        val roles: MutableList<Role> = mutableListOf()
)