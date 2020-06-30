package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = -1,
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
) {
        @OneToMany(mappedBy = "createdBy", cascade = [CascadeType.DETACH], orphanRemoval = true)
        val myTasks: MutableList<Task> = mutableListOf()
        @ManyToMany(mappedBy = "assignedTo", cascade = [CascadeType.DETACH])
        val tasksAssingedToMe: MutableList<Task> = mutableListOf()
}