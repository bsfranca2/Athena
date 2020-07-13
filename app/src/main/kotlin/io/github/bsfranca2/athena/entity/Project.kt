package io.github.bsfranca2.athena.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_project")
data class Project(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name: String,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        val createdBy: User,
        @Column(name = "created_at", nullable = false)
        val createdAt: LocalDateTime
) {
        @OneToMany(mappedBy = "project", cascade = [CascadeType.DETACH])
        val items: MutableList<ProjectItem> = mutableListOf()
}