package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_workspace")
data class Workspace(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name: String,
        @Column(unique = true, nullable = false)
        var slug: String
) {
    @ManyToMany
    @JoinTable(name = "ath_workspaces_managers",
            joinColumns = [JoinColumn(name = "workspace_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
    val managers: MutableList<User> = mutableListOf()

    fun isManager(user: User): Boolean {
        return this.managers.find { it.id == user.id } != null
    }
}