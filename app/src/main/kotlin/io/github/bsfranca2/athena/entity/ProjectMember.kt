package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_project_member")
data class ProjectMember(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "workspace_id")
        val workspace: Workspace,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "project_id")
        val project: Project,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "user_id")
        val user: User,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "role_id")
        val role: Role
) {
    fun isProjectManager(): Boolean {
        return this.role.name == "PROJECT_MANAGER"
    }

    fun isDeveloper(): Boolean {
        return this.role.name == "DEVELOPER"
    }

    fun isCustomer(): Boolean {
        return this.role.name == "CUSTOMER"
    }
}