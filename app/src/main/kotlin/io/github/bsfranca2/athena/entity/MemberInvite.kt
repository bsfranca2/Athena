package io.github.bsfranca2.athena.entity

import javax.persistence.*

@Entity
@Table(name = "ath_member_invite")
data class MemberInvite(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "project_id")
        val project: Project,
        @Column(nullable = false)
        val email: String,
        @Column(unique = true, nullable = false)
        val token: String,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "role_id")
        val role: Role
)