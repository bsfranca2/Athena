package io.github.bsfranca2.athena.entity

import io.github.bsfranca2.athena.enum.ProjectItemType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ath_project_item")
open class ProjectItem(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open var name: String,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "project_id")
        open val project: Project,
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "created_by", nullable = false)
        open val createdBy: User,
        @Column(name = "created_at", nullable = false)
        open val createdAt: LocalDateTime
) {
        open fun getType(): ProjectItemType {
                return ProjectItemType.DEFAULT
        }
}