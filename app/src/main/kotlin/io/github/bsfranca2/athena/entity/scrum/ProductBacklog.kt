package io.github.bsfranca2.athena.entity.scrum

import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectItem
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.enum.ProjectItemType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ath_product_backlog")
class ProductBacklog(
        id: Long,
        name: String,
        project: Project,
        @OneToMany(mappedBy = "productBacklog", cascade = [CascadeType.DETACH])
        val items: MutableList<ProductBacklogItem>,
        createdBy: User,
        createdAt: LocalDateTime
): ProjectItem(id, name, project, createdBy, createdAt) {
        override fun getType(): ProjectItemType {
                return ProjectItemType.PRODUCT_BACKLOG
        }
}